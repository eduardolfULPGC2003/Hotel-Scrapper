package scrapper.model;

public class Resource {
    private String name;
    private String apiUrl;

    public Resource(String hotelName, String bookingName) {
        this.name = hotelName;
        this.apiUrl = "v1/hotels/" + bookingName;
    }

    public String getBookingName() {
        return name;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
