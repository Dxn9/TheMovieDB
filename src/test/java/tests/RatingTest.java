package tests;

import controllers.RatingController;
import io.restassured.response.Response;
import models.RatingRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RatingTest {

    private RatingController ratingController;
    private String movieId = "550";


    @BeforeClass
    public void setup() {
        ratingController = new RatingController();
    }

    @Test
    public void testAddRating() {
        RatingRequest ratingRequest = new RatingRequest(8.5);
        String body = "{ \"value\": " + ratingRequest.getValue() + " }";
        Response response = ratingController.addRating(movieId, body);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }

    @Test(dependsOnMethods = "testAddRating")
    public void testGetMovieDetails() {
        Response response = ratingController.getMovieDetails(movieId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("title"));
        Assert.assertTrue(response.jsonPath().getFloat("vote_average") >= 0);
    }

    @Test(dependsOnMethods = "testAddRating")
    public void testDeleteRating() {
        Response response = ratingController.deleteRating(movieId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
    }
}
