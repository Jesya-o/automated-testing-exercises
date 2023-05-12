package api_testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public record App(RestfulAPIFramework apiFramework) {

    private static final String BOOKING = "/booking";
    private static final String AUTH = "/auth";
    private static final String ID_PARAM = "{id}";

    private RequestSpecification createJsonRequest() {
        return apiFramework.createRequest(ContentType.JSON);
    }

    public Response getAllBookings() {
        return apiFramework.sendRequest(createJsonRequest(), "GET", BOOKING);
    }

    public Response getBookingById(int id) {
        RequestSpecification request = createJsonRequest().pathParam("id", id);
        return apiFramework.sendRequest(request, "GET", BOOKING + "/" + ID_PARAM);
    }

    public Response createBooking(Map<String, Object> payload) {
        RequestSpecification request = createJsonRequest();
        apiFramework.setPayload(request, payload);
        return apiFramework.sendRequest(request, "POST", BOOKING);
    }

    public Response authenticate(Map<String, String> credentials) {
        RequestSpecification request = createJsonRequest();
        apiFramework.setPayload(request, credentials);
        return apiFramework.sendRequest(request, "POST", AUTH);
    }

    public String getToken(Map<String, String> credentials) {
        Response response = authenticate(credentials);
        return response.jsonPath().getString("token");
    }

    public Response createBookingWithWrongAcceptHeader(Map<String, Object> payload, String wrongAcceptHeader) {
        RequestSpecification request = createJsonRequest();
        apiFramework.setPayload(request, payload);
        return apiFramework.sendRequest(request, "POST", BOOKING, wrongAcceptHeader);
    }

    public Response updateBooking(int bookingId, Map<String, Object> updatedPayload, String token) {
        RequestSpecification request = createJsonRequest()
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId);
        apiFramework.setPayload(request, updatedPayload);
        return apiFramework.sendRequest(request, "PUT", BOOKING + "/" + ID_PARAM);
    }

    public Response deleteBooking(int bookingId, String token) {
        RequestSpecification request = createJsonRequest()
                .header("Cookie", "token=" + token)
                .pathParam("id", bookingId);
        return apiFramework.sendRequest(request, "DELETE", BOOKING + "/" + ID_PARAM);
    }
}
