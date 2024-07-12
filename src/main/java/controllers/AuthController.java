package controllers;

import io.restassured.response.Response;

public class AuthController extends BaseController {

    public Response createGuestSession() {
        String pathCreateGuestSession = "/authentication/guest_session/new";
        return makeGetRequest(pathCreateGuestSession);
    }

    public Response getCreateRequestToken() {
        String pathAuthenticationTokenNew = "/authentication/token/new";
        return makeGetRequest(pathAuthenticationTokenNew);
    }

    public Response postCreateSessionWithLogin(String body) {
        String pathAuthenticationTokenValidateLogin = "/authentication/token/validate_with_login";
        return makePostRequest(pathAuthenticationTokenValidateLogin, body);
    }
}

