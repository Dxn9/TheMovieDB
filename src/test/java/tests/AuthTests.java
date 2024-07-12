package tests;

import controllers.AuthController;
import io.restassured.response.Response;
import models.GuestSession;
import models.RequestToken;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static data.Credentials.*;

public class AuthTests {

    private AuthController authController;

    @BeforeClass
    public void setup() {
        authController = new AuthController();
    }

    @Test
    public void testCreateGuestSession() {
        Response response = authController.createGuestSession();
        GuestSession guestSession = response.getBody().as(GuestSession.class);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        Assert.assertTrue(guestSession.isSuccess(), "Guest session creation is failed");
    }

    @Test
    public void testCreateRequestToken() {
        Response response = authController.getCreateRequestToken();
        RequestToken requestToken = response.getBody().as(RequestToken.class);
        Assert.assertTrue(requestToken.isSuccess(), "Request token creation failed");
    }

    @Test
    public void testCreateSessionWithLogin() {
        Response tokenResponse = authController.getCreateRequestToken();
        String requestToken = tokenResponse.jsonPath().getString("request_token");
        String body = "{\n" +
                "  \"username\": \"" + USER_USERNAME + "\",\n" +
                "  \"password\": \"" + USER_PASSWORD + "\",\n" +
                "  \"request_token\": \"" + requestToken + "\"\n" +
                "}";
        Response response = authController.postCreateSessionWithLogin(body);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.jsonPath().getBoolean("success"), "Session with login creation failed");
    }
}
