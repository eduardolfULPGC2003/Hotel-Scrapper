package scrapper;

import java.util.List;
import java.util.Map;

public class Hotel {
    private static Integer nextId = 1;
    private final Integer id;
    private String bookingName;
    private HotelInfo hotelInfo;
    private Map<String, String> categories;
    private Map<String, String[]> services;
    private List<Review> reviews;

    public Hotel(String bookingName, String bookingUrl, String name, String location, String mark, String accommodation, Map<String, String> categories, Map<String, String[]> services, List<Review> reviews) {
        this.bookingName = bookingName;
        this.id = nextId;
        this.hotelInfo = new HotelInfo(bookingUrl, name, location, mark, accommodation);
        this.categories = categories;
        this.services = services;
        this.reviews = reviews;
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public Map<String, String[]> getServices() {
        return services;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public String getBookingName() {
        return bookingName;
    }

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public static void setNextId(Integer nextId) {
        Hotel.nextId = nextId;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public void setCategories(Map<String, String> categories) {
        this.categories = categories;
    }

    public void setServices(Map<String, String[]> services) {
        this.services = services;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
