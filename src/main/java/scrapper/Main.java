package scrapper;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Map<String, String> prueba = new HashMap<>();
        prueba.put("Hola", "");
        System.out.println(new Gson().toJson(prueba));
        prueba.replace("Hola", "Hola");
        System.out.println(new Gson().toJson(prueba));
    }
}
