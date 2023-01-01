package scrapper;


import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;

public class SparkWebService {
    private final Controller controller;

    public SparkWebService(Controller controller) {
        this.controller = controller;
    }

    public void start(){
        Spark.port(4567);
        get("/v1/hotels", this::getResources);
        get("/v1/hotels/:name", this::getHotelInfo);
        get("/v1/hotels/:name/services", this::getHotelServices);
        get("/v1/hotels/:name/comments", this::getHotelComments);
        get("/v1/hotels/:name/ratings", this::getHotelRatings);
    }

    private String getHotelRatings(Request req, Response res) {
        String name = req.params("name");
        try {
            Map<String, String> ratings = controller.getHotelRatings(name);
            return toJson(ratings);
        } catch (IOException | HotelNotFoundException e) {
            halt(400, String.valueOf(e));
        }
        return "";
    }

    private String getHotelComments(Request req, Response res) {
        String name = req.params("name");
        try {
            List<Review> comments = controller.getHotelComments(name);
            return toJson(comments);
        } catch (IOException | HotelNotFoundException e) {
            halt(400, String.valueOf(e));
        }
        return "";
    }

    private String getHotelServices(Request req, Response res) {
        String name = req.params("name");
        try {
            Map<String, String[]> services = controller.getHotelServices(name);
            return toJson(services);
        } catch (IOException | HotelNotFoundException e) {
            halt(400, String.valueOf(e));
        }
        return "";
    }

    private String getHotelInfo(Request req, Response res) {
        String name = req.params("name");
        try {
            HotelInfo info = controller.getHotelInfo(name);
            return toJson(info);
        } catch (IOException | HotelNotFoundException e) {
            halt(400, String.valueOf(e));
        }
        return "";
    }

    private String getResources(Request req, Response res) {
        List<Resource> resources = controller.getResources();
        return toJson(resources);
    }

    private String toJson(Object o){
        return new Gson().toJson(o);
    }


}
