package api_testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class App {

    private final api_testing.RestfulAPIFramework apiFramework;
    private final String BOOKING = "/booking";
    private final String GET = "GET";
    private final String POST = "POST";
    private final String PUT = "PUT";
    private final String DELETE = "DELETE";


    public RestfulAPIFramework getApiFramework() {
        return apiFramework;
    }

    public App(RestfulAPIFramework apiFramework) {
        this.apiFramework = apiFramework;
    }

    public Response getAllBookings() {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        return apiFramework.sendRequest(request, GET, BOOKING);
    }

    public Response getBookingById(int id) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        request.pathParam("id", id);
        return apiFramework.sendRequest(request, GET, BOOKING + "/{id}");
    }

    public Response createBooking(Map<String, Object> payload) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        apiFramework.setPayload(request, payload);
        return apiFramework.sendRequest(request, POST, BOOKING);
    }

    public Response authenticate(Map<String, String> credentials) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        apiFramework.setPayload(request, credentials);
        return apiFramework.sendRequest(request, POST, "/auth");
    }

    public String getToken(Map<String, String> credentials) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        apiFramework.setPayload(request, credentials);
        Response response = apiFramework.sendRequest(request, POST, "/auth");
        return response.jsonPath().getString("token");
    }

    public Response createBookingWithWrongAcceptHeader(Map<String, Object> payload, String wrongAcceptHeader) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        apiFramework.setPayload(request, payload);
        return apiFramework.sendRequest(request, POST, BOOKING, wrongAcceptHeader);
    }

    public Response updateBooking(int bookingId, Map<String, Object> updatedPayload, String token) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        request.header("Cookie", "token=" + token);
        apiFramework.setPayload(request, updatedPayload);
        request.pathParam("id", bookingId);
        return apiFramework.sendRequest(request, PUT, BOOKING + "/{id}");
    }

    public Response deleteBooking(int bookingId, String token) {
        RequestSpecification request = apiFramework.createRequest(ContentType.JSON);
        request.header("Cookie", "token=" + token);
        request.pathParam("id", bookingId);
        return apiFramework.sendRequest(request, DELETE, BOOKING + "/{id}");
    }
}
