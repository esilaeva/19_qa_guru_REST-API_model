package guru.qa.tests;

import guru.qa.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.CreateUserSpec.createUserRequestSpec;
import static guru.qa.specs.CreateUserSpec.createUserResponseSpec;
import static guru.qa.specs.LoginSpec.loginRequestSpec;
import static guru.qa.specs.LoginSpec.loginResponseSpec;
import static guru.qa.specs.RegisterUserSpec.*;
import static guru.qa.specs.UpdateUserSpec.updateRequestSpec;
import static guru.qa.specs.UpdateUserSpec.updateResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InReqresTests extends TestBase{

    @Test
    @DisplayName("Successful Login Test. Method POST")
    void successfulLoginTest() {
        LoginBodyModel autoData = new LoginBodyModel();
        autoData.setEmail("eve.holt@reqres.in");
        autoData.setPassword("cityslicka");

        LoginResponseModel response = step("Make login request", () ->
                given(loginRequestSpec)
                        .body(autoData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    @DisplayName("Create User. Method POST")
    void createUser() {
        CreateUserBodyModel createUserData = new CreateUserBodyModel();
        createUserData.setName("morpheus");
        createUserData.setJob("leader");

        CreateUserResponseModel response = step("Create user request", () ->
                given(createUserRequestSpec)
                        .body(createUserData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(CreateUserResponseModel.class));

        step("Verify response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    @Test
    @DisplayName("Successful Register User. Method POST")
    void successfulRegisterUser() {
        SuccessfulRegisterUserBodyModel registerUserData = new SuccessfulRegisterUserBodyModel();
        registerUserData.setEmail("eve.holt@reqres.in");
        registerUserData.setPassword("pistol");

        SuccessfulRegisterUserResponseModel response = step("Register user request", () ->
                given(registerUserRequestSpec)
                .body(registerUserData)
                .when()
                .post("/register")
                .then()
                        .spec(successfulRegisterUserResponseSpec)
                .extract().as(SuccessfulRegisterUserResponseModel.class));

        step("Verify response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    @DisplayName("Unsuccessful Register User. Method POST")
    void unsuccessfulRegisterUser() {
        UnsuccessfulRegisterUserBodyModel registerUserData = new UnsuccessfulRegisterUserBodyModel();
        registerUserData.setEmail("sydney@fife");

        UnsuccessfulRegisterUserResponseModel response = step("Register user request", () ->
                given(registerUserRequestSpec)
                .body(registerUserData)
                .when()
                .post("/register")
                .then()
                        .spec(unsuccessfulRegisterUser400ResponseSpec)
                .extract().as(UnsuccessfulRegisterUserResponseModel.class));

        step("Verify response", () ->
                assertEquals("Missing password", response.getError()));
    }

    @Test
    @DisplayName("Update. Method PUT")
    void updateUser() {
        UpdateUserBodyModel updateUserData = new UpdateUserBodyModel();
        updateUserData.setName("morpheus");
        updateUserData.setJob("zion resident");

        UpdateUserResponseModel response = step("Update user request", () ->
                given(updateRequestSpec)
                        .body(updateUserData)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(UpdateUserResponseModel.class));

        step("Verify response", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }
}
