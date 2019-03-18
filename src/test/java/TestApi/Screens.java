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
    private String authToken = "EAAEysaJM61YBACawFuFqGk7FxOR3wkuWLYNZAsyzkKuv0hdn0yGYcj0qEecSiv7ZBNuEVuBZASdazFgiWuL48hIOYAOkvNBjAIbhlqmHLqkh5wf33NfdS3YCkEdcPw0FjXCt9xODvVvtitwlu26AkFLZBxAqwF3yIwbL8dliRF8MR8GGWvZCpQBCwB5yZBDEghW1bkTJb1ogZDZD";
    private String authTokenInvalid = "EAAHJIGaQDi8BANchVM3HrZAwPzwbf3i7ENb48MZCincKUNtQRV5kQAlCbwreFec1ezxnzIYB1YZB1Y3VTKUvzzxQaMky7rkCAGhZC9YZCu3ZAZAMZC8NfzeJeAe6ugU2fzZAdkisFajX9wDYUXDZBCv3UGgz5WZCEGOImCMom2YOdyCNADZC1hOqTFBuqGsy9KwZAbZCgZD";
    private String postID;
    private String urlTokenAcces = "https://graph.facebook.com/me/?access_token=";
    private String graphUrl = "https://graph.facebook.com/me";


    @Test
    public void verifyAuthenticationInvalid ( ) throws Exception {


        Response resp = RestAssured.get(urlTokenAcces + authTokenInvalid);

        if (resp.getStatusCode() == 400) {

            System.out.println(resp.getStatusCode() + " - Sucesso");
        } else {
            System.out.println(resp.getStatusCode() + "Erro");
        }
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test
    public void verifyAuthentication ( ) throws Exception {

        Response resp = RestAssured.get(urlTokenAcces + authToken);

        if (resp.getStatusCode() == 200) {

            System.out.println(resp.getStatusCode() + " Sucesso");
        } else {
            System.out.println(resp.getStatusCode() + "Erro");
        }
        Assert.assertEquals(200, resp.getStatusCode());
    }


    @Test
    public void postTimeLine ( ) {


        Response postReq = given()
                .contentType(ContentType.JSON)
                .body("{\"message\":\"Desafio Sensedia 2019\"}")
                .when()
                .post(graphUrl + authToken);

        if (postReq.getStatusCode() == 200) {

            System.out.println(postReq.getStatusCode() + "Secesso");
            this.postID = postReq.
                    then().
                    contentType(ContentType.JSON).extract().path("id");
            System.out.println(this.postID + "- id salvo");
        } else {
            System.out.println(postReq.getStatusCode() + "Erro");
        }
    }

    @Test
    public void changePostTimeLine ( ) {

        Response changeReq = given()
                .contentType(ContentType.JSON)
                .body("{\"message\":\"esafio Sensedia 2019 Eduardo\"}")
                .when()
                .put(graphUrl + postID + authToken);

        if (changeReq.getStatusCode() == 200) {
            System.out.println(changeReq.getStatusCode() + "Sucesso");
        } else {
            System.out.println(changeReq.getStatusCode() + "Erro");
        }
    }

    @Test
    public void deletePostTimeLine ( ) {

        ResponsepostTimeLine responsePageTimeLine = postTimeLine();

        String updatedUrl = responsePageTimeLine.postID + authToken;
        Response res =
                given()
                        .when()
                        .contentType(ContentType.JSON)
                        .delete(graphUrl + updatedUrl);
        System.out.println(res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
