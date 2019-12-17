package services;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;



public class cryptocurrencyAPICalls {
    private static String BASE_URL = "https://pro-api.coinmarketcap.com/v1/";
    private static String ENDPOINT_GET_ID = "cryptocurrency/map";
    private static String ENDPOINT_GET_INFO = "cryptocurrency/info";
    private static String ENDPOINT_CONVERSION = "tools/price-conversion";

    public static String jsonAsString;

    static Response response;

    public static String getCurrencyID(String symbol) {

        response = given().
                header("X-CMC_PRO_API_KEY", "a5fb23ad-1429-4aab-8901-e5c5aa4f4bda")
                .queryParam("symbol", symbol)

                .when()
                .get(BASE_URL + ENDPOINT_GET_ID)
                .then().log().all().statusCode(200)
                .extract().response();

        return response.jsonPath().getList("data.id").get(0).toString();


    }


    public static void covertInto(String currencyId, String convertToCurrency) {

        response = given().
                header("X-CMC_PRO_API_KEY", "a5fb23ad-1429-4aab-8901-e5c5aa4f4bda")
                .queryParam("id", currencyId)
                .queryParam("convert", convertToCurrency)
                .queryParam("amount", "50")
                .when()
                .get(BASE_URL + ENDPOINT_CONVERSION)
                .then().statusCode(200)
                .extract().response();
    }


    public static Response getCurrencyInfoByCurrencyCode(String currency) {
        response = given().
                header("X-CMC_PRO_API_KEY", "a5fb23ad-1429-4aab-8901-e5c5aa4f4bda")
                .queryParam("symbol", currency)
                .when()
                .get(BASE_URL + ENDPOINT_GET_INFO);
        return response;
    }

    public static Response getCurrencyInfoByCurrencyId(String id) {
        response = given().
                header("X-CMC_PRO_API_KEY", "a5fb23ad-1429-4aab-8901-e5c5aa4f4bda")
                .queryParam("id", id)
                .when()
                .get(BASE_URL + ENDPOINT_GET_INFO);
        return response;
    }
}
