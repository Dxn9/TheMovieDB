package controllers;

import io.restassured.response.Response;

import java.util.HashMap;

public class AccountController extends BaseController {

    public Response getAccountDetails() {
        String path = "/account";
        return makeGetRequest(path);
    }

    public Response addFavorite(String accountId, String body) {
        String path = String.format("/account/%s/favorite", accountId);
        return makePostRequest(path, body);
    }

    public Response addToWatchlist(String accountId, String body) {
        String path = String.format("/account/%s/watchlist", accountId);
        return makePostRequest(path, body);
    }

    public Response getFavoriteMovies(String accountId, HashMap<String, String> queryParams) {
        String path = String.format("/account/%s/favorite/movies", accountId);
        return makeGetRequest(queryParams, path);
    }

    public Response getWatchlistMovies(String accountId, HashMap<String, String> queryParams) {
        String path = String.format("/account/%s/watchlist/movies", accountId);
        return makeGetRequest(queryParams, path);
    }
}
