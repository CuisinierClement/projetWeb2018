/*
 * Creation : 9 mai 2016
 */
package com.inetpsa.pi201.manageprojects.rest.user;

import static com.jayway.restassured.RestAssured.expect;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.seedstack.seed.it.AbstractSeedWebIT;
import org.skyscreamer.jsonassert.JSONAssert;

public class UserResourceIT extends AbstractSeedWebIT {

    @ArquillianResource
    private URL baseURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class);
    }

    @RunAsClient
    @Test
    public void my_rest_resource_is_working(@ArquillianResource URL baseURL) {
        String l_baseURLToString = baseURL.toString();

        // expect().statusCode(200).when().get(baseURL.toString() + "/users");
    }

//    @RunAsClient
//    @Test
//    public void testCreateUser() throws JSONException {
//
//        // Create user Representation
//        UserRepresentation l_UserRepresentation = new UserRepresentation();
//
//        l_UserRepresentation.setFirstName("THIPHAINE");
//        l_UserRepresentation.setName("PEREZ");
//        l_UserRepresentation.setLogin("1000000");
//
//        // Initialize JSON file
//        JSONObject obj = new JSONObject();
//        obj.put("firstName", l_UserRepresentation.getFirstName());
//        obj.put("name", l_UserRepresentation.getName());
//        obj.put("login", l_UserRepresentation.getLogin());
//
//        // Get response
//        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj.toString()).post(baseURL.toString() + "users/").asString();
//
//        JSONAssert.assertEquals(obj, new JSONObject(response), false);
//
//    }
//
//    @RunAsClient
//    @Test
//    public void testCreateUserWithAnExistingLogin() throws JSONException {
//
//        // Create user Representation
//        UserRepresentation l_UserRepresentation = new UserRepresentation();
//
//        l_UserRepresentation.setFirstName("FAROUK");
//        l_UserRepresentation.setName("SENDAL");
//        l_UserRepresentation.setLogin("1495796");
//
//        // Initialize JSON file
//        JSONObject obj = new JSONObject();
//        obj.put("firstName", l_UserRepresentation.getFirstName());
//        obj.put("name", l_UserRepresentation.getName());
//        obj.put("login", l_UserRepresentation.getLogin());
//
//        // response has to be an error 400 because User with the login u484014 already existed
//        String response = expect().statusCode(409).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj.toString()).post(baseURL.toString() + "users/").asString();
//
//        // JSONAssert.assertEquals(obj, new JSONObject(response), false);
//    }
//
//    @RunAsClient
//    @Test
//    public void testCreateUserWithoutLogin() throws JSONException {
//
//        // Create user Representation
//        UserRepresentation l_UserRepresentation = new UserRepresentation();
//
//        // enter only fisrtName and name of user
//        l_UserRepresentation.setFirstName("AHMED SALEM");
//        l_UserRepresentation.setName("SOUMARE");
//
//        // Initialize JSON file
//        JSONObject obj = new JSONObject();
//        obj.put("firstName", l_UserRepresentation.getFirstName());
//        obj.put("name", l_UserRepresentation.getName());
//
//        // response has to be an error 400 because User with the login u484014 already existed
//        String response = expect().statusCode(409).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj.toString()).post(baseURL.toString() + "users/").asString();
//
//        // JSONAssert.assertEquals(obj, new JSONObject(response), false);
//    }
//
//    @RunAsClient
//    @Test
//    public void testUpdateUser() throws JSONException {
//
//        // Create one user
//        UserRepresentation l_UserRepresentation = new UserRepresentation();
//
//        l_UserRepresentation.setFirstName("AHMED");
//        l_UserRepresentation.setName("SALEM SOUMARE");
//        l_UserRepresentation.setLogin("1333333");
//
//        JSONObject obj = new JSONObject();
//        obj.put("firstName", l_UserRepresentation.getFirstName());
//        obj.put("name", l_UserRepresentation.getName());
//        obj.put("login", l_UserRepresentation.getLogin());
//
//        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj.toString()).post(baseURL.toString() + "users/").asString();
//
//        JSONAssert.assertEquals(obj, new JSONObject(response), false);
//
//        // Update the user
//        l_UserRepresentation.setFirstName("AHMED SALEM");
//        l_UserRepresentation.setName("SOUMARE");
//
//        JSONObject obj_update = new JSONObject();
//        obj_update.put("firstName", l_UserRepresentation.getFirstName());
//        obj_update.put("name", l_UserRepresentation.getName());
//        obj_update.put("login", l_UserRepresentation.getLogin());
//
//        String responseUpdate = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj_update.toString()).put(baseURL.toString() + "users/{userId}", l_UserRepresentation.getLogin()).asString();
//
//        JSONAssert.assertEquals(obj_update, new JSONObject(responseUpdate), false);
//
//    }
//
//    @RunAsClient
//    @Test
//    public void testDeleteUser() throws JSONException {
//
//        // Create one user
//        UserRepresentation l_UserRepresentation = new UserRepresentation();
//
//        l_UserRepresentation.setFirstName("FRANK");
//        l_UserRepresentation.setName("VINCENTINE");
//        l_UserRepresentation.setLogin("1038964");
//
//        JSONObject obj = new JSONObject();
//        obj.put("firstName", l_UserRepresentation.getFirstName());
//        obj.put("name", l_UserRepresentation.getName());
//        obj.put("login", l_UserRepresentation.getLogin());
//
//        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj.toString()).post(baseURL.toString() + "users/").asString();
//
//        JSONAssert.assertEquals(obj, new JSONObject(response), false);
//
//        // Delete the user
//        expect().statusCode(200).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .delete(baseURL.toString() + "users/{userId}", l_UserRepresentation.getLogin()).asString();
//
//    }
//
//    @RunAsClient
//    @Test
//    public void testGetUser() throws JSONException {
//
//        // Create one user
//        UserRepresentation l_UserRepresentation = new UserRepresentation();
//
//        l_UserRepresentation.setFirstName("ANDREA");
//        l_UserRepresentation.setName("LOURENCONI");
//        l_UserRepresentation.setLogin("1111111");
//
//        JSONObject obj = new JSONObject();
//        obj.put("firstName", l_UserRepresentation.getFirstName());
//        obj.put("name", l_UserRepresentation.getName());
//        obj.put("login", l_UserRepresentation.getLogin());
//
//        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj.toString()).post(baseURL.toString() + "users/").asString();
//
//        JSONAssert.assertEquals(obj, new JSONObject(response), false);
//
//        // get the user
//        String responseUser = expect().statusCode(200).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .get(baseURL.toString() + "users/{userId}", l_UserRepresentation.getLogin()).asString();
//
//        JSONAssert.assertEquals(obj, new JSONObject(responseUser), false);
//    }
//
//    @RunAsClient
//    @Test
//    public void testFindUser() throws JSONException {
//
//        // ///////////////////////
//        // Create the users
//
//        // User 1
//        UserRepresentation l_UserRepresentation1 = new UserRepresentation();
//
//        l_UserRepresentation1.setFirstName("ERIC");
//        l_UserRepresentation1.setName("NAVIGNON");
//        l_UserRepresentation1.setLogin("1484032");
//
//        JSONObject obj1 = new JSONObject();
//        obj1.put("firstName", l_UserRepresentation1.getFirstName());
//        obj1.put("name", l_UserRepresentation1.getName());
//        obj1.put("login", l_UserRepresentation1.getLogin());
//
//        String response1 = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj1.toString()).post(baseURL.toString() + "users/").asString();
//
//        JSONAssert.assertEquals(obj1, new JSONObject(response1), false);
//
//        // User 2
//        UserRepresentation l_UserRepresentation2 = new UserRepresentation();
//
//        l_UserRepresentation2.setFirstName("FABIO JULIANO");
//        l_UserRepresentation2.setName("DE HOLANDA BARROS");
//        l_UserRepresentation2.setLogin("1256913");
//
//        JSONObject obj2 = new JSONObject();
//        obj2.put("firstName", l_UserRepresentation2.getFirstName());
//        obj2.put("name", l_UserRepresentation2.getName());
//        obj2.put("login", l_UserRepresentation2.getLogin());
//
//        String response2 = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
//                .body(obj2.toString()).post(baseURL.toString() + "users/").asString();
//
//        JSONAssert.assertEquals(obj2, new JSONObject(response2), false);
//
//        // TODO find the user: Eric
//        // String responseUser1 = expect().statusCode(200).given().header("Accept", "application/json").header("Content-Type", "application/json")
//        // .queryParameters("searchString", "eric", "pageIndex", 1, "pageSize", 5).get(baseURL.toString() + "users/").asString();
//        //
//        // JSONAssert.assertEquals(obj1, new JSONObject(responseUser1), false);
//    }
}
