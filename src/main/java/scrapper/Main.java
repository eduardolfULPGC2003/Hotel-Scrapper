package scrapper;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        SparkWebService sparkWebService = new SparkWebService(controller);
        sparkWebService.start();
    }
}
