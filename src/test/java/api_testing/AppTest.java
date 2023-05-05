package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    public static final String BOOKING = "/booking";

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get(BOOKING)
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void givenAcceptType_whenGetBookingById_thenShouldReturnHttpStatus200() {
        int bookingId = 5237; // Any booking ID

        given()
                .pathParam("id", bookingId)
                .when()
                .get(BOOKING + "/{id}")
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void postBookingShouldReturnHttpStatus200() {
        Response response = createBooking();
        assertEquals(SC_OK, response.getStatusCode());
    }

    @Test
    public void postBookingResponseShouldContainId() {
        Response response = createBooking();
        Map<String, Object> responseBody = response.jsonPath().getMap("$");

        assertNotNull(responseBody.get("bookingid"));
    }

    @Test
    public void postBookingResponseShouldContainBooking() {
        Response response = createBooking();
        Map<String, Object> responseBody = response.jsonPath().getMap("$");

        assertNotNull(responseBody.get("booking"));
    }

    private Response createBooking() {
        String requestBody = "{" +
                "\"firstname\" : \"Max\"," +
                "\"lastname\" : \"Well\"," +
                "\"totalprice\" : 666," +
                "\"depositpaid\" : true," +
                "\"bookingdates\" : {" +
                "    \"checkin\" : \"2023-05-10\"," +
                "    \"checkout\" : \"2023-05-17\"" +
                "}," +
                "\"additionalneeds\" : \"Breakfast\"" +
                "}";

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BOOKING);
    }
}
