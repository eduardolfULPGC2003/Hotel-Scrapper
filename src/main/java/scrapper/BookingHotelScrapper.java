package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingHotelScrapper implements HotelScrapper{
    private final Document mainPage;
    private final Document reviewsPage;

    public BookingHotelScrapper(String name) throws IOException {
        this.mainPage = Jsoup.connect("https://www.booking.com/hotel/es/" + name + ".es.html").get();
        this.reviewsPage = Jsoup.connect("https://www.booking.com/reviewlist.es.html?cc1=es;dist=1;pagename=" + name + ";type=total&&offset=0;rows=10").get();
    }

    @Override
    public String scrapName() {
        return mainPage.select("h2.d2fee87262.pp-header__title").text();
    }

    @Override
    public String scrapLocation() {
        return mainPage.select("span.hp_address_subtitle.js-hp_address_subtitle.jq_tooltip").text();
    }

    @Override
    public String scrapMark() {
        return mainPage.select("div.daaa8ff09f.f62fdb6838").select("div.b5cd09854e.d10a6220b4").text();
    }

    @Override
    public String scrapAccommodation() {
        return mainPage.select("span.accommodation-type-badge-mfe-wrapper").select("span.e2f34d59b1").text();
    }

    @Override
    public String[] scrapCategories() {
        return splitByUppercase(mainPage.select("div.page-section.js-k2-hp--block.k2-hp--featured_reviews").select("div.d46673fe81").select("span.d6d4671780").text());
    }

    @Override
    public String[] scrapCategoriesMark() {
        return mainPage.select("div.page-section.js-k2-hp--block.k2-hp--featured_reviews").select("div.d46673fe81").select("div.ee746850b6.b8eef6afe1").text().split(" ");
    }

    @Override
    public Map<String, String[]> scrapServices() {
        String text = mainPage.select("div.hotel-facilities__list").select("div.bui-spacer--large").select("div.bui-title__text.hotel-facilities-group__title-text").text();
        String[] mainServices = splitByUppercase(text);
        Map<String, String[]> services = new HashMap<>();
        for (int i = 0; i < mainServices.length ; i++) {
            String text2 = mainPage.select("div.hotel-facilities__list").select("div.bui-spacer--large").get(i).select("div.bui-list__description").text();
            String[] subServices = splitByUppercase(text2);
            services.put(mainServices[i], subServices);
        }
        return services;
    }

    @Override
    public List<Review> scrapReviews() {
        List<Review> reviewArrayList = new ArrayList<>();
        String positiveComment = "";
        String negativeComment = "";

        for (int i = 0; i < 10; i++) {
            String reviewMark = reviewsPage.select("div.bui-review-score__badge").get(i).text();
            Elements review = reviewsPage.select("li.review_list_new_item_block").get(i).select("div.c-review");
            if (review.size() != 2){
                if (review.select("span.c-review__body").contains("No")){
                    positiveComment = null;
                    negativeComment = review.select("span.c-review__body").text();
                } else {
                    positiveComment = review.select("span.c-review__body").text();
                    negativeComment = null;
                }
            } else {
                positiveComment = review.select("span.c-review__body").get(0).text();
                negativeComment = review.select("span.c-review__body").get(1).text();
            }
            reviewArrayList.add(new Review(reviewMark, positiveComment, negativeComment));
        }
        return reviewArrayList;
    }

    private static String[] splitByUppercase(String string){
        return string.split("\\s(?=[A-Z])");
    }
}
