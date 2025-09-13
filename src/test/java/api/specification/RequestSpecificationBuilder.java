package api.specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {
    public RequestSpecification buildCollectingSpecification() {
        RequestSpecification requestSpecification = buildBaseSpecification()
                .basePath("/api");
        return RestAssured.given(requestSpecification);
    }
    private RequestSpecification buildBaseSpecification() {
        RequestSpecification requestSpecification = RestAssured.given()
                .spec((new RequestSpecBuilder()).setAuth(RestAssured.DEFAULT_AUTH).build())
                .relaxedHTTPSValidation()
                .baseUri("https://reqres.in/")
                .accept("application/json, text/plain, */*")
                .header("Connection", "Keep-Alive");
        return RestAssured.given(requestSpecification);
    }
}
