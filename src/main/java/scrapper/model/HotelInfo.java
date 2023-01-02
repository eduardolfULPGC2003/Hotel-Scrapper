package scrapper.model;

public class HotelInfo {
    private String bookingUrl;
    private String name;
    private String location;
    private String mark;
    private String accommodation;

    public HotelInfo(String bookingUrl, String name, String location, String mark, String accommodation) {
        this.bookingUrl = bookingUrl;
        this.name = name;
        this.location = location;
        this.mark = mark;
        this.accommodation = accommodation;
    }
}
