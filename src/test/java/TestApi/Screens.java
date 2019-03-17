package TestApi;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class Screens {
    private String authToken = "EAAEysaJM61YBAGVVBqevVKkbVf2CW9DRETU5bJV6pDhGwGVKEifJiS5dZAafLWt3VkRb7u1LHuZBvDDIZAEAUREfOE1EYF4lY0MLO66HoFAZAbauT3CtXst1GVqGlAAUds6tpvvPgZA8RYMfDgwVIyMigYER6l0ZAPzXcozmMnZCFwVY0jZAHxAkwSpX0bG1beFo0NWv8kvJuwZDZD";
    private String authTokenInvalid = "EAAHJIGaQDi8BANchVM3HrZAwPzwbf3i7ENb48MZCincKUNtQRV5kQAlCbwreFec1ezxnzIYB1YZB1Y3VTKUvzzxQaMky7rkCAGhZC9YZCu3ZAZAMZC8NfzeJeAe6ugU2fzZAdkisFajX9wDYUXDZBCv3UGgz5WZCEGOImCMom2YOdyCNADZC1hOqTFBuqGsy9KwZAbZCgZD";
    private String postID;
    private String urlTokenAcces = "https://graph.facebook.com/me/?access_token=";
    private String graphUrl = "https://graph.facebook.com/";


    @Test
    public void verifyAuthentication() throws Exception {

        Response resp = RestAssured.get(urlTokenAcces + authToken);

        if (resp.getStatusCode() == 200) {

            System.out.println(resp.getStatusCode() + " - connect");
        } else {
            System.out.println(resp.getStatusCode() + "fail - check your accessToken");
        }
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test
    public void verifyAuthenticationInvalid() throws Exception {


        Response resp = RestAssured.get(authTokenInvalid + authToken);

        if (resp.getStatusCode() == 400) {

            System.out.println(resp.getStatusCode() + " - connect");
        } else {
            System.out.println(resp.getStatusCode() + "fail");
        }
        Assert.assertEquals(400, resp.getStatusCode());
    }


    @Test
    public void postTimeLine() {


        Response postReq = given()
                .contentType(ContentType.JSON)
                .body("{\"message\":\"TESTEAPI123\"}")
                .when()
                .post(urlTokenAcces + authToken);

        if (postReq.getStatusCode() == 200) {

            System.out.println(postReq.getStatusCode() + "sucessful");
            this.postID = postReq.
                    then().
                    contentType(ContentType.JSON).extract().path("id");
            System.out.println(this.postID + "- id.post saved");
        } else {
            System.out.println(postReq.getStatusCode() + " - verify your request");
        }
    }

    @Test
    public void changePostTimeLine() {

        Response changeReq = given()
                .contentType(ContentType.JSON)
                .body("{\"message\":\"123APITEST\"}")
                .when()
                .put(graphUrl + postID + "/?access_token=" + authToken);

        if (changeReq.getStatusCode() == 200) {
            System.out.println(changeReq.getStatusCode() + "post change sucessful ");
        } else {
            System.out.println(changeReq.getStatusCode() + " - fail");
        }
    }
    @Test
    public void deletePostTimeLine() {

            Response postReq = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(urlTokenAcces + authToken);

            if (postReq.getStatusCode() == 200) {

                System.out.println(postReq.getStatusCode() + "sucessful");
                this.postID = postReq.
                        then().
                        contentType(ContentType.JSON).extract().path("id");
                System.out.println(this.postID + "- id.saved");
            } else {
                System.out.println(postReq.getStatusCode() + " - verify your request");
            }
        }

    }
