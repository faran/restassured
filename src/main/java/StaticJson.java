import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static resources.HelperUtils.generateStringFromResources;
import static resources.Resources.LIB_RES_ADD;

public class StaticJson {

    @Test
    public void PostJsonFromFile() throws IOException {

        RestAssured.baseURI="http://216.10.245.166";
        Response response = given().
                header("Content-Type","application/json").
                queryParam("key", "qaclick123").log().all().
                body(generateStringFromResources("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/Addbookdetaills.json")).
                when().
                post(LIB_RES_ADD).
                then().
                assertThat().statusCode(200).
                extract().response();

        JsonPath jsonPath = HelperUtils.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);
    }
}
