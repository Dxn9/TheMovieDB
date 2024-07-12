package controllers;

import io.restassured.response.Response;

import java.util.HashMap;

public class MovieController extends BaseController {

    private final String resource = "/movie/";

    public Response getMovieDetails(int movieId) {
        String path = resource + movieId;
        return makeGetRequest(path);
    }

    public Response postRateMovie(int movieId, String body) {
        HashMap<String, Object> queryParams = new HashMap<>();
        String path = resource + movieId + "/rating";
        return makePostRequest(path, body);
    }
}
