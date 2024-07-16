package tests;

import controllers.AccountController;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AccountTests {

    private AccountController accountController;
    private String accountId;


    @BeforeClass
    public void setup() {
        accountController = new AccountController();
        accountId = "21236289";
    }

    @Test
    public void testGetAccountDetails() {
        Response response = accountController.getAccountDetails();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testAddFavorite() {
        String body = "{ \"media_type\": \"movie\", \"media_id\": 550, \"favorite\": true }";
        Response response = accountController.addFavorite(accountId, body);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.jsonPath().getBoolean("success"), "Session with login creation failed");
    }

    @Test
    public void testAddToWatchlist() {
        String body = "{ \"media_type\": \"movie\", \"media_id\": 11, \"watchlist\": true }";
        Response response = accountController.addToWatchlist(accountId, body);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.jsonPath().getBoolean("success"), "Session with login creation failed");
    }

    @Test
    public void testGetFavoriteMovies() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("language", "en-US");
        Response response = accountController.getFavoriteMovies(accountId, queryParams);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("results"));  // Дополнительная проверка
    }

    @Test
    public void testGetWatchlistMovies() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("language", "en-US");
        Response response = accountController.getWatchlistMovies(accountId, queryParams);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("results"));  // Дополнительная проверка
    }
}
