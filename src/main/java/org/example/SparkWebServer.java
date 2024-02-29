package org.example;

import spark.Spark;

import static spark.Spark.port;
import static spark.Spark.get;


public class SparkWebServer {
    public static void main(String... args){
        Spark.staticFiles.location("/public");
        port(getPort());
        get("hello", (req,res) -> "Hello Docker! :3 ");

        get("/sin/:value", (req, res) -> {
            double value = Double.parseDouble(req.params(":value"));
            return Math.sin(value);
        });

        get("/cos/:value", (req, res) -> {
            double value = Double.parseDouble(req.params(":value"));
            return Math.cos(value);
        });

        get("/isPalindromo/:word", (req, res) -> {
            String word = req.params(":word");
            return isPalindrome(word);
        });

        get("/magvector/:x/:y", (req, res) -> {
            double x = Double.parseDouble(req.params(":x"));
            double y = Double.parseDouble(req.params(":y"));
            return Math.sqrt(x * x + y * y);
        });
    }
    private static boolean isPalindrome(String word) {
        String cleanWord = word.replaceAll("\\s+", "").toLowerCase();
        int length = cleanWord.length();
        for (int i = 0; i < length / 2; i++) {
            if (cleanWord.charAt(i) != cleanWord.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}