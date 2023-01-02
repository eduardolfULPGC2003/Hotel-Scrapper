package scrapper;

import scrapper.model.Review;

import java.util.List;
import java.util.Map;

public interface HotelScrapper {
    String scrapName();
    String scrapLocation();
    String scrapMark();
    String scrapAccommodation();
    String[] scrapCategories();
    String[] scrapCategoriesMark();
    Map<String, String[]> scrapServices();
    List<Review> scrapReviews();
}
