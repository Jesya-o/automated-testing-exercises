package api_testing;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class App {

    private final api_testing.RestfulAPIFramework apiFramework;
    private final String BOOKING = "/booking";
    private final String GET = "GET";

    public RestfulAPIFramework getApiFramework() {
        return apiFramework;
    }

    public App(RestfulAPIFramework apiFramework) {
        this.apiFramework = apiFramework;
    }

    public Response getAllBookings() {
        RequestSpecification request = apiFramework.createRequest();
        return apiFramework.sendRequest(request, GET, BOOKING);
    }

    public Response getBookingById(int id) {
        RequestSpecification request = apiFramework.createRequest();
        request.pathParam("id", id);
        return apiFramework.sendRequest(request, GET, BOOKING + "/{id}");
    }
}
