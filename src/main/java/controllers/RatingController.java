package controllers;

import io.restassured.response.Response;

import java.util.HashMap;

public class RatingController extends BaseController {

    public Response addRating(String movieId, String body) {
        String path = String.format("/movie/%s/rating", movieId);
        return makePostRequest(path, body);
    }

    public Response getMovieDetails(String movieId) {
        String path = String.format("/movie/%s", movieId);
        return makeGetRequest(path);
    }

    public Response deleteRating(String movieId) {
        String path = String.format("/movie/%s/rating", movieId);
        return makeDeleteRequest(new HashMap<>(), path);
    }
}
