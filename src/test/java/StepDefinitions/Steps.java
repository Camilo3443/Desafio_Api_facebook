package StepDefinitions;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static javafx.scene.input.DataFormat.URL;

public class Steps {

    private String authToken;
    private String useURL;
    Response reqResp;


    @RunWith(Cucumber.class)
    public class MyStepDefinitions {

        private String authToken;
        private String useURL;

        //passo 1
        @Given("^O usuario possui um token invalido$")
        public void o_usuario_possui_um_token_invalido ( ) throws Throwable {
            this.authToken = "EAADFsoUp0ckBAMPEA92wZAicyMWbeZCKyEJ1g5gNdp2X6GquL6e4taJtCo9sjv3xvyvhhZAV2BZC7D1a5x1AKjdZA71fU5b5gbiqpvHYrleFl2tObwoZBEvxvcPAD3QraCyVhTGJjrQdfxe3ZAedHbzecICar76NuZCo0iLbCnbuhQ6mcPJpNbe7fsE36ZC6xHN314ZCF7NFR5fgZDZD";
            throw new PendingException();
        }

        @When("^Enviar uma requisicao para \"([^\"]*)\"$")
        public void enviar_uma_requisicao_para_something (String strArg1) throws Throwable {
            this.useURL = URL + "/?access_token=" + authToken;
            reqResp = RestAssured.get(this.useURL);
            throw new PendingException();
        }

        @Then("^A resposta do sistema deve ser 400$")
        public void a_resposta_do_sistema_deve_ser_40 ( ) throws Throwable {
            if (reqResp.getStatusCode() == 400) {
                System.out.println(reqResp.getStatusCode() + " - sucessful to connect");
            } else {
                System.out.println(reqResp.getStatusCode() + " - fail - check your credentials");
            }
            Assert.assertEquals(400, reqResp.getStatusCode());
            throw new PendingException();

        }
        //passo 2
        @Given("^O usuario possui um token valido$")
        public void o_usuario_possui_um_token_valido ( ) throws Throwable {
            this.authToken = "EAADFsoUp0ckBAMPEA92wZAicyMWbeZCKyEJ1g5gNdp2X6GquL6e4taJtCo9sjv3xvyvhhZAV2BZC7D1a5x1AKjdZA71fU5b5gbiqpvHYrleFl2tObwoZBEvxvcPAD3QraCyVhTGJjrQdfxe3ZAedHbzecICar76NuZCo0iLbCnbuhQ6mcPJpNbe7fsE36ZC6xHN314ZCF7NFR5fgZDZD";

            throw new PendingException();
        }

        @When("^Enviar uma requisicao para \"([^\"]*)\"$")
        public void enviar_uma_requisicao_para_ (String strArg1) throws Throwable {
            this.useURL = URL + "/?access_token=" + authToken;
            reqResp = RestAssured.get(this.useURL);

            throw new PendingException();
        }

        @Then("^A resposta do sistema deve ser 200$")
        public void a_resposta_do_sistema_deve_ser_200 ( ) throws Throwable {
            if (reqResp.getStatusCode() == 200) {
                System.out.println(reqResp.getStatusCode() + " - sucessful to connect");
            } else {
                System.out.println(reqResp.getStatusCode() + " - fail - check your credentials");
            }
            throw new PendingException();
        }

        @And("^Ele e autenticado com sucesso$")
        public void ele_e_autenticado_com_sucesso ( ) throws Throwable {
            Assert.assertEquals(200, reqResp.getStatusCode());
            throw new PendingException();
        }
    }
}
