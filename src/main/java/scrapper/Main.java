package scrapper;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        SparkWebService sparkWebService = new SparkWebService(controller);
        sparkWebService.start();
    }
}
