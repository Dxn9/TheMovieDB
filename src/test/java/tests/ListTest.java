package tests;

import controllers.ListController;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ListTest {

    private ListController listController;
    private String listId;

    @BeforeClass
    public void setup() {
        listController = new ListController();
    }


    @Test
    public void testCreateList() {
        String body = "{\"name\":\"This is my awesome test list.\",\"description\":\"Just an awesome list.\",\"language\":\"en\"}";
        Response response = listController.createList(body);
        Assert.assertEquals(response.getStatusCode(), 201);
        listId = response.jsonPath().getString("list_id");
        Assert.assertEquals(response.jsonPath().getString("status_message"), "Success.");
    }

    @Test(dependsOnMethods = "testCreateList")
    public void testAddMovieToList() {
        String body = "{\"media_id\":18}";
        Response response = listController.addMovieToList(listId, body);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(dependsOnMethods = "testCreateList")
    public void testCheckItemStatus() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("media_id", "550");
        Response response = listController.checkItemsStatus(listId, queryParams);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "testCreateList")
    public void testGetListDetails() {
        Response response = listController.getListDetails(listId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("name"));
        Assert.assertNotNull(response.jsonPath().getString("description"));
    }

    @Test(dependsOnMethods = "testCreateList")
    public void testRemoveMovieFromList() {
        String body = "{ \"media_id\": 550 }";
        Response response = listController.removeMovieFromList(listId, body);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status_message"), "The item/record was updated successfully.");
    }

    @Test(dependsOnMethods = "testCreateList")
    public void testClearList() {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("confirm", "true");
        Response response = listController.clearList(listId, queryParams);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("status_message"), "The item/record was updated successfully.");
    }

    @Test(dependsOnMethods = "testCreateList")
    public void testDeleteList() {
        Response response = listController.deleteList(listId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("status_message"), "The item/record was deleted successfully.");
    }
}
