package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestfulAPIFramework {

    public RestfulAPIFramework(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public RequestSpecification createRequest(ContentType contentType) {
        return RestAssured.given().contentType(contentType).header("Accept", "application/json");
    }

    public Response sendRequest(RequestSpecification request, String httpMethod, String endpoint) {
        return request.when().request(httpMethod, endpoint);
    }

    public Response sendRequest(RequestSpecification request, String httpMethod, String endpoint, String acceptHeaderValue) {
        return request.header("Accept", acceptHeaderValue).when().request(httpMethod, endpoint);
    }

    public <T> void setPayload(RequestSpecification request, T payload) {
        request.body(payload);
    }

    public void validateResponse(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
}
