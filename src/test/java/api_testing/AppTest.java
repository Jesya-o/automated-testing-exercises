package api_testing;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AppTest {
    private static final String BOOKING_ID_KEY = "bookingid";
    private static final String BOOKING_KEY = "booking";

    private static App app;

    @BeforeAll
    public static void setup() {
        RestfulAPIFramework apiFramework = new RestfulAPIFramework("https://restful-booker.herokuapp.com");
        app = new App(apiFramework);
    }

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        Response response = app.getAllBookings();
        app.apiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void givenAcceptType_whenGetBookingById_thenShouldReturnHttpStatus200() {
        int sampleBookingId = getSampleBookingId();

        Response response = app.getBookingById(sampleBookingId);

        app.apiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void givenPayload_whenCreateBooking_thenShouldReturnHttpStatus200() {
        Response response = createSampleBooking();

        app.apiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void postBookingResponseShouldContainId() {
        Map<String, Object> responseBody = getSampleBookingResponseBody();

        assertNotNull(responseBody.get(BOOKING_ID_KEY));
        assertNotNull(responseBody.get(BOOKING_KEY));
    }

    @Test
    public void postBookingResponseShouldContainBooking() {
        Map<String, Object> responseBody = getSampleBookingResponseBody();

        assertNotNull(responseBody.get(BOOKING_ID_KEY));
        assertNotNull(responseBody.get(BOOKING_KEY));
    }

    @Test
    public void postAuthenticationWithCorrectCredentialsReturns200() {
        Response response = app.authenticate(getCredentials());
        app.apiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void postAuthenticationWithIncorrectCredentialsReturnsNullToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "wrong_username");
        credentials.put("password", "wrong_pwd");

        Response response = app.authenticate(credentials);
        app.apiFramework().validateResponse(response, SC_OK);

        String token = getToken(response);
        assertNull(token);
    }

    @Test
    public void postAuthenticationReturnsToken() {
        Response response = app.authenticate(getCredentials());
        app.apiFramework().validateResponse(response, SC_OK);

        String token = getToken(response);
        assertNotNull(token);
    }

    @Test
    public void postBookingWithWrongAcceptHeaderReturns418() {
        String wrongAcceptHeader = "text/plain";

        Response response = app.createBookingWithWrongAcceptHeader(createSamplePayload(), wrongAcceptHeader);
        app.apiFramework().validateResponse(response, 418);
    }

    @Test
    public void putBookingShouldReturn200() {
        int sampleBookingId = getSampleBookingId();

        Map<String, Object> updatedPayload = createSamplePayload();
        updatedPayload.put("lastname", "new-lastname");

        String token = app.getToken(getCredentials());

        Response response = app.updateBooking(sampleBookingId, updatedPayload, token);
        app.apiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void deleteBookingShouldReturn201() {
        int sampleBookingId = getSampleBookingId();

        String token = app.getToken(getCredentials());

        Response response = app.deleteBooking(sampleBookingId, token);
        app.apiFramework().validateResponse(response, SC_CREATED);
    }

    private Map<String, Object> createSamplePayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "Max");
        payload.put("lastname", "Well");
        payload.put("totalprice", 666);
        payload.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-05-10");
        bookingDates.put("checkout", "2023-05-17");

        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }

    private Response createSampleBooking() {
        return app.createBooking(createSamplePayload());
    }

    private int getBookingIdFromResponse(Response response) {
        return response.jsonPath().getInt(BOOKING_ID_KEY);
    }

    private int getSampleBookingId() {
        Response bookingCreationResponse = createSampleBooking();
        return getBookingIdFromResponse(bookingCreationResponse);
    }

    private Map<String, Object> getSampleBookingResponseBody() {
        Response response = createSampleBooking();
        return response.jsonPath().getMap("$");
    }

    private String getToken(Response response) {
        return response.jsonPath().getString("token");
    }

    private Map<String, String> getCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password123");
        return credentials;
    }
}
