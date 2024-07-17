package controllers;

import io.restassured.response.Response;

import java.util.HashMap;

public class ListController extends BaseController {

    public Response createList(String body) {
        String path = "/list";
        return makePostRequest(path, body);
    }

    public Response addMovieToList(String listId, String body) {
        String path = String.format("/list/%s/add_item", listId);
        return makePostRequest(path, body);
    }

    public Response checkItemsStatus(String listId, HashMap<String, String> queryParams) {
        String path = String.format("/list/%s/item_status", listId);
        return makeGetRequest(queryParams, path);
    }

    public Response clearList(String listId, HashMap<String, String> queryParams) {
        String path = String.format("/list/%s/clear", listId);
        return makePostRequest(queryParams, path);
    }

    public Response getListDetails(String listId) {
        String path = String.format("/list/%s", listId);
        return makeGetRequest(path);
    }

    public Response removeMovieFromList(String listId, String body) {
        String path = String.format("/list/%s/remove_item", listId);
        return makePostRequest(path, body);
    }

    public Response deleteList(String listId) {
        String path = String.format("/list/%s", listId);
        return makeDeleteRequest(new HashMap<>(), path);
    }
}
