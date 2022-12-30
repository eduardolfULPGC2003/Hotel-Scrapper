package scrapper;

import java.util.List;
import java.util.Map;

public class Hotel {
    private static Integer nextID = 1;
    private final Integer id;
    private HotelInfo hotelInfo;
    private Map<String, String> categories;
    private Map<String, String[]> services;
    private List<Review> reviews;

    public Hotel(Integer id,String bookingUrl, String name, String location, String mark, String accommodation, Map<String, String> categories, Map<String, String[]> services, List<Review> reviews) {
        this.id = id;
        this.hotelInfo = new HotelInfo(bookingUrl, name, location, mark, accommodation);
        this.categories = categories;
        this.services = services;
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
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
}
