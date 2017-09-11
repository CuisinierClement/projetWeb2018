/// *
// * Creation : 6 juil. 2016
// */
package com.inetpsa.pi201.manageprojects.rest.project;

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

public class ProjectResourceIT extends AbstractSeedWebIT {

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

    @RunAsClient
    @Test
    public void testCreateProject() throws JSONException {

        // Create project Representation
        ProjectRepresentation lProjectRepresentation = new ProjectRepresentation();

        lProjectRepresentation.setname("NOUVEAU PROJET");
        lProjectRepresentation.setResponsable("U484014");

        lProjectRepresentation.setname(lProjectRepresentation.getname().replaceAll("\\s", ""));

        // Initialize JSON file
        JSONObject obj = new JSONObject();
        obj.put("name", lProjectRepresentation.getname());
        obj.put("responsable", lProjectRepresentation.getResponsable());

        // Get response
        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .body(obj.toString()).post(baseURL.toString() + "projects/").asString();

        JSONAssert.assertEquals(obj, new JSONObject(response), false);

    }

    @RunAsClient
    @Test
    public void testCreateProjectWithoutName() throws JSONException {

        // Create project Representation
        ProjectRepresentation lProjectRepresentation = new ProjectRepresentation();

        // enter only responsable of project
        lProjectRepresentation.setResponsable("u484014");

        // Initialize JSON file
        JSONObject obj = new JSONObject();
        obj.put("responsable", lProjectRepresentation.getResponsable());

        // response has to be an error 400 because User with the login u484014 already existed
        String response = expect().statusCode(409).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .body(obj.toString()).post(baseURL.toString() + "projects/").asString();

        // JSONAssert.assertEquals(obj, new JSONObject(response), false);
    }

    @RunAsClient
    @Test
    public void testDeleteProject() throws JSONException {

        // Create one project
        ProjectRepresentation lProjectRepresentation = new ProjectRepresentation();

        lProjectRepresentation.setname("IT PROJECT");
        lProjectRepresentation.setResponsable("C038964");

        lProjectRepresentation.setname(lProjectRepresentation.getname().replaceAll("\\s", ""));

        JSONObject obj = new JSONObject();
        obj.put("name", lProjectRepresentation.getname());
        obj.put("responsable", lProjectRepresentation.getResponsable());

        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .body(obj.toString()).post(baseURL.toString() + "projects/").asString();

        JSONAssert.assertEquals(obj, new JSONObject(response), false);

        // Delete the project
        expect().statusCode(200).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .delete(baseURL.toString() + "projects/{projectId}", lProjectRepresentation.getname()).asString();

    }

    @RunAsClient
    @Test
    public void testGetProject() throws JSONException {

        // Create one project
        ProjectRepresentation lProjectRepresentation = new ProjectRepresentation();

        lProjectRepresentation.setname("INTERFACE CADDIE - PLM");
        lProjectRepresentation.setResponsable("U111111");

        lProjectRepresentation.setname(lProjectRepresentation.getname().replaceAll("\\s", ""));

        JSONObject obj = new JSONObject();
        obj.put("name", lProjectRepresentation.getname());
        obj.put("responsable", lProjectRepresentation.getResponsable());

        String response = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .body(obj.toString()).post(baseURL.toString() + "projects/").asString();

        JSONAssert.assertEquals(obj, new JSONObject(response), false);

        // get the project
        String responseProject = expect().statusCode(200).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .get(baseURL.toString() + "projects/{projectId}", lProjectRepresentation.getname()).asString();

        JSONAssert.assertEquals(obj, new JSONObject(responseProject), false);
    }

    @RunAsClient
    @Test
    public void testFindProject() throws JSONException {

        // ///////////////////////
        // Create the projects

        // Project 1
        ProjectRepresentation lProjectRepresentation1 = new ProjectRepresentation();

        lProjectRepresentation1.setname("OUTILS EN REACTIVITE ( DIAGALYSER SUITE)");
        lProjectRepresentation1.setResponsable("U484032");

        lProjectRepresentation1.setname(lProjectRepresentation1.getname().replaceAll("\\s", ""));

        JSONObject obj1 = new JSONObject();
        obj1.put("name", lProjectRepresentation1.getname());
        obj1.put("responsable", lProjectRepresentation1.getResponsable());

        String response1 = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .body(obj1.toString()).post(baseURL.toString() + "projects/").asString();

        JSONAssert.assertEquals(obj1, new JSONObject(response1), false);

        // Project 2
        ProjectRepresentation lProjectRepresentation2 = new ProjectRepresentation();

        lProjectRepresentation2.setname("BOUCLE DYNAMIQUE FAISCEAU");
        lProjectRepresentation2.setResponsable("U256913");

        lProjectRepresentation2.setname(lProjectRepresentation2.getname().replaceAll("\\s", ""));

        JSONObject obj2 = new JSONObject();
        obj2.put("name", lProjectRepresentation2.getname());
        obj2.put("responsable", lProjectRepresentation2.getResponsable());

        String response2 = expect().statusCode(201).given().header("Accept", "application/json").header("Content-Type", "application/json")
                .body(obj2.toString()).post(baseURL.toString() + "projects/").asString();

        JSONAssert.assertEquals(obj2, new JSONObject(response2), false);

    }

}
