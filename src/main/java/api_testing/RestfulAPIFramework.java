package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestfulAPIFramework {

    public RestfulAPIFramework(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public RequestSpecification createRequest() {
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Response sendRequest(RequestSpecification request, String httpMethod, String endpoint) {
        return request.when().request(httpMethod, endpoint);
    }

    public void setPayload(RequestSpecification request, Map<String, Object> payload) {
        request.body(payload);
    }

    public void validateResponse(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
}