import services.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import payloads.TodoTask;
import payloads.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FanCodeCityTest {

    private final ApiClient apiClient = new ApiClient();
    private final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(FanCodeCityTest.class);


    @Test
    public void testFancodeUsersHaveMoreThan50PercentTodosCompleted() {
        // Step 1: Fetch all users
        logger.info("Starting test to validate FanCode city users' todo completion.");
        Response userResponse = apiClient.getUsers();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> users = gson.fromJson(userResponse.asString(), userListType);
        logger.info("Fetched {} users from API.", users.size());

        // Step 2: Filter users by latitude and longitude (FanCode city)
        List<User> fancodeUsers = users.stream()
                .filter(user -> {
                    double lat = user.getAddress().getGeo().getLatitude();
                    double lng = user.getAddress().getGeo().getLongitude();
                    return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
                })
                .toList();

        logger.info("Found {} users from FanCode city."+"\n", fancodeUsers.size());

        // Step 3: Validate that each user from FanCode has more than 50% completed todos
        for (User user : fancodeUsers) {
            logger.info("Checking todos for userId: {}", user.getId() +", name :"+user.getName()+", userName :"+user.getUserName());
           // logger.info("Checking todos for name: {}", user.getName());
            //logger.info("Checking todos for userName: {}", user.getUserName());

            Response todosResponse = apiClient.getTodosForUser(user.getId());
            Type todoListType = new TypeToken<List<TodoTask>>() {}.getType();
            List<TodoTask> todos = gson.fromJson(todosResponse.asString(), todoListType);

            long completedCount = todos.stream().filter(TodoTask::isCompleted).count();
            double completionPercentage = (double) completedCount / todos.size() * 100;

            logger.info("UserId: {} has {}% completed todos."+"\n", user.getId(), completionPercentage);
            assertTrue(completionPercentage > 50,
                    "User " + user.getId() + " from FanCode city has less than 50% completed todos.");

        }
        logger.info("Test completed successfully.");
    }
}
