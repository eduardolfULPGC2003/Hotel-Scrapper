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
    private Controller controller;

    public SparkWebService(Controller controller) {
        this.controller = controller;
    }

    public void start(){
        Spark.port(4567);
        get("/v1/hotels", (req, res) -> getResources(req, res));
        get("/v1/hotels/:name", (req, res) -> getHotelInfo(req, res));
        get("/v1/hotels/:name/services", (req, res) -> getHotelServices(req, res));
        get("/v1/hotels/:name/comments", (req, res) -> getHotelComments(req, res));
        get("/v1/hotels/:name/ratings", (req, res) -> getHotelRatings(req, res));
    }

    private String getHotelRatings(Request req, Response res) {
        String name = req.params("name");
        try {
            Map<String, String> ratings = controller.getHotelRatings(name);
            return toJson(ratings);
        } catch (IOException e) {
            halt(400, "Hotel not found");
        }
        return "";
    }

    private String getHotelComments(Request req, Response res) {
        String name = req.params("name");
        try {
            List<Review> comments = controller.getHotelComments(name);
            return toJson(comments);
        } catch (IOException e) {
            halt(400, "Hotel not found");
        }
        return "";
    }

    private String getHotelServices(Request req, Response res) {
        String name = req.params("name");
        try {
            Map<String, String[]> services = controller.getHotelServices(name);
            return toJson(services);
        } catch (IOException e) {
            halt(400, "Hotel not found");
        }
        return "";
    }

    private String getHotelInfo(Request req, Response res) {
        String name = req.params("name");
        try {
            HotelInfo info = controller.getHotelInfo(name);
            return toJson(info);
        } catch (IOException e) {
            halt(400, "Hotel not found");
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
