package guru.qa.tests;

import guru.qa.models.*;
import org.junit.jupiter.api.Test;

import static guru.qa.helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InReqresTests extends TestBase{

    @Test
    void successfulLoginTest() {
        LoginBodyModel autoData = new LoginBodyModel();
        autoData.setEmail("eve.holt@reqres.in");
        autoData.setPassword("cityslicka");

        LoginResponseModel response = step("Make login request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().method()
                        .log().body()
                        .contentType(JSON)
                        .body(autoData)
                        .when()
                        .post("/login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    void createUser() {
        CreateUserBodyModel createUserData = new CreateUserBodyModel();
        createUserData.setName("morpheus");
        createUserData.setJob("leader");

        CreateUserResponseModel response = step("Create user request", () ->
                given()
                        .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                        .body(createUserData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                        .extract().as(CreateUserResponseModel.class));

        step("Verify response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    @Test
    void successfulRegisterUser() {
        SuccessfulRegisterUserBodyModel registerUserData = new SuccessfulRegisterUserBodyModel();
        registerUserData.setEmail("eve.holt@reqres.in");
        registerUserData.setPassword("pistol");

        SuccessfulRegisterUserResponseModel response = step("Register user request", () ->
        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(registerUserData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(SuccessfulRegisterUserResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    void unsuccessfulRegisterUser() {
        UnsuccessfulRegisterUserBodyModel registerUserData = new UnsuccessfulRegisterUserBodyModel();
        registerUserData.setEmail("sydney@fife");

        UnsuccessfulRegisterUserResponseModel response = step("Register user request", () ->
        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(registerUserData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(UnsuccessfulRegisterUserResponseModel.class));

        step("Verify response", () ->
                assertEquals("Missing password", response.getError()));
    }
}
