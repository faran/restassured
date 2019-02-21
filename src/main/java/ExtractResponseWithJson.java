import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import resources.HelperUtils;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static resources.HelperUtils.getConfigValue;
import static resources.PayLoad.bodyPost;
import static resources.Resources.RES_ADD;
import static resources.Resources.RES_DELETE;

public class ExtractResponseWithJson {

    private static Logger logger = LogManager.getLogger(ExtractResponseWithJson.class.getName());

    @Test
    public void BasicsTestRestAssured() {

        String baseURI = getConfigValue("/env.properties","HOST");
        String key = getConfigValue("/env.properties","KEY");

        RestAssured.baseURI = baseURI;
        logger.info("Host Information :" + baseURI);
        Response res = given().
                queryParam("key", key).
                body(bodyPost).
                when().
                post(RES_ADD).
                then().
                statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"))
                .extract().response();

        //GRAB place id FROM RESPONSE
        JsonPath jsonPath = HelperUtils.rawToJson(res);
        String placeId = jsonPath.get("place_id");
        logger.info("Place Id: " + placeId);

        //DELETE POST
        given().
                queryParam("key", key).
                body("{\n" +
                        "\"place_id\": \""+placeId+"\"\n"+
                        "}").
                when().
                post(RES_DELETE).
                then().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));

    }
}
