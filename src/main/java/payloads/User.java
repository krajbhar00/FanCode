package payloads;

public class User {

    private int id;
    private String name;
    private String username;
    private Address address;

    public static class Address {
        private Geo geo;

        public static class Geo {
            private String lat;
            private String lng;

            public double getLatitude() {
                return Double.parseDouble(lat);
            }

            public double getLongitude() {
                return Double.parseDouble(lng);
            }
        }

        public Geo getGeo() {
            return geo;
        }
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return username;
    }
}
