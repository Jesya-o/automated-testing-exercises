package api_testing;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AppTest {
    private static App app;

    @BeforeAll
    public static void setup() {
        RestfulAPIFramework apiFramework = new RestfulAPIFramework("https://restful-booker.herokuapp.com");
        app = new App(apiFramework);
    }

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        Response response = app.getAllBookings();
        app.getApiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void givenAcceptType_whenGetBookingById_thenShouldReturnHttpStatus200() {
        int bookingId = 1977; // Any valid booking ID

        Response response = app.getBookingById(bookingId);
        app.getApiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void givenPayload_whenCreateBooking_thenShouldReturnHttpStatus200() {
        Response response = app.createBooking(createSamplePayload());
        app.getApiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void postBookingResponseShouldContainId() {
        Response response = app.createBooking(createSamplePayload());
        Map<String, Object> responseBody = response.jsonPath().getMap("$");

        assertNotNull(responseBody.get("bookingid"));
        assertNotNull(responseBody.get("booking"));
    }

    @Test
    public void postBookingResponseShouldContainBooking() {
        Response response = app.createBooking(createSamplePayload());
        Map<String, Object> responseBody = response.jsonPath().getMap("$");

        assertNotNull(responseBody.get("bookingid"));
        assertNotNull(responseBody.get("booking"));
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

    @Test
    public void postAuthenticationWithCorrectCredentialsReturns200() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password123");

        Response response = app.authenticate(credentials);
        app.getApiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void postAuthenticationWithIncorrectCredentialsReturnsNullToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "wrong_username");
        credentials.put("password", "wrong_pwd");

        Response response = app.authenticate(credentials);
//        app.getApiFramework().validateResponse(response, HttpStatus.SC_UNAUTHORIZED);
        app.getApiFramework().validateResponse(response, SC_OK);

        String token = response.jsonPath().getString("token");
        assertNull(token);
    }

    @Test
    public void postAuthenticationReturnsToken() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("password", "password123");

        Response response = app.authenticate(credentials);
        app.getApiFramework().validateResponse(response, SC_OK);

        String token = response.jsonPath().getString("token");
        assertNotNull(token);
    }
}
