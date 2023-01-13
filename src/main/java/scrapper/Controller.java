package scrapper;

import scrapper.model.Hotel;
import scrapper.model.HotelInfo;
import scrapper.model.Resource;
import scrapper.model.Review;

import java.io.IOException;
import java.util.*;

public class Controller {
    private List<Hotel> hotels;
    private List<Resource> resources;

    public Controller() {
        this.hotels = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public void start() {
        // TODO
    }

    private Hotel scrap(String bookingName) throws IOException, HotelNotFoundException {
        HotelScrapper scrapper = new BookingHotelScrapper(bookingName);
        String url = "https://www.booking.com/hotel/es/" + bookingName + ".es.html";
        String hotelName = scrapper.scrapName();
        if (hotelName.length()==0) throw new HotelNotFoundException("Hotel not found");
        String mark = scrapper.scrapMark();
        String accommodation = scrapper.scrapAccommodation();
        String location = scrapper.scrapLocation();
        String[] categories = scrapper.scrapCategories();
        String[] categoriesMark = scrapper.scrapCategoriesMark();
        Map<String, String[]> services = scrapper.scrapServices();
        List<Review> reviews = scrapper.scrapReviews();
        Map<String, String> allCategories = new HashMap<>();
        for (int i = 0; i < categories.length; i++) allCategories.put(categories[i], categoriesMark[i]);
        Hotel hotel = new Hotel(bookingName, url, hotelName, location, mark, accommodation, allCategories, services, reviews);
        hotels.add(hotel);
        resources.add(new Resource(hotelName, bookingName));
        return hotel;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public HotelInfo getHotelInfo(String name) throws IOException, HotelNotFoundException {
        for (Hotel hotel: hotels){if (hotel.getBookingName().equals(name)) return hotel.getHotelInfo();}
        return scrap(name).getHotelInfo();
    }

    public Map<String, String[]> getHotelServices(String name) throws IOException, HotelNotFoundException {
        for (Hotel hotel: hotels){if (hotel.getBookingName().equals(name)) return hotel.getServices();}
        return scrap(name).getServices();
    }

    public List<Review> getHotelComments(String name) throws IOException, HotelNotFoundException {
        for (Hotel hotel: hotels){if (hotel.getBookingName().equals(name)) return hotel.getReviews();}
        return scrap(name).getReviews();
    }

    public Map<String, String> getHotelRatings(String name) throws IOException, HotelNotFoundException {
        for (Hotel hotel: hotels){if (hotel.getBookingName().equals(name)) return hotel.getCategories();}
        return scrap(name).getCategories();
    }
}
