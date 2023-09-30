package guru.qa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static guru.qa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class RegisterUserSpec {

    public static RequestSpecification registerUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().body()
            .contentType(JSON)
            .baseUri(baseURI)
            .basePath(basePath);

    public static ResponseSpecification successfulRegisterUserResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification unsuccessfulRegisterUser400ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .build();
}
