package api_testing;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;

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
        int bookingId = 5237; // Any booking ID

        Response response = app.getBookingById(bookingId);
        app.getApiFramework().validateResponse(response, SC_OK);
    }

    @Test
    public void givenPayload_whenCreateBooking_thenShouldReturnHttpStatus200() {
        Response response = createBooking();
        app.getApiFramework().validateResponse(response, SC_OK);
    }
}
