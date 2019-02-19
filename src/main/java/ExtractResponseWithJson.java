import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static resources.PayLoad.bodyPost;
import static resources.Resources.RES_ADD;
import static resources.Resources.RES_DELETE;

public class ExtractResponseWithJson {


    @Test
    public void BasicsTestRestAssured() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/env.properties");
        prop.load(fis);
        String baseURI = prop.getProperty("HOST");
        String key = prop.getProperty("KEY");
        System.out.println(baseURI);

        RestAssured.baseURI = baseURI;

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
        System.out.println(placeId);

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
