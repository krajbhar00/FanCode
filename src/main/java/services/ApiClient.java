package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiClient {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    public Response getUsers() {
        logger.info("Fetching users from: {}/users", BASE_URL);
        Response response = RestAssured.get(BASE_URL + "/users");
        logger.info("Received response: {}", response.getStatusLine());
        return response;
    }

    public Response getTodosForUser(int userId) {
        logger.info("Fetching todos for userId: {} from: {}/todos", userId, BASE_URL);
        Response response = RestAssured.get(BASE_URL + "/todos?userId=" + userId);
        logger.info("Received response: {}", response.getStatusLine());
        return response;
    }
}
