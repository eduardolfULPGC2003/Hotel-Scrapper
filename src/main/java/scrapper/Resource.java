package scrapper;

public class Resource {
    private String name;
    private String apiUrl;

    public Resource(String name, String apiUrl) {
        this.name = name;
        this.apiUrl = apiUrl;
    }

    public String getName() {
        return name;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
