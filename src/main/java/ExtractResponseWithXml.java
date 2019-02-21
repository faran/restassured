import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static resources.HelperUtils.generateStringFromResources;
import static resources.HelperUtils.getConfigValue;
import static resources.Resources.RES_ADDX;


public class ExtractResponseWithXml {

    private static Logger logger = LogManager.getLogger(ExtractResponseWithJson.class.getName());

    @Test
    public void BasicsTestRestAssured() throws IOException {
        String baseURI = getConfigValue("/env.properties","HOST");
        String key = getConfigValue("/env.properties","KEY");

        String xmlPost = generateStringFromResources("./src/main/resources/addplacepost.xml");

        RestAssured.baseURI = baseURI;
        logger.info("Host Information :" + baseURI);
        Response res =
                given().
                queryParam("key", key).
                body(xmlPost).
                when().
                post(RES_ADDX).
                then().
                statusCode(200).and().contentType(ContentType.XML).and().extract().response();
                //body("status", equalTo("OK"));
        XmlPath xmlPath = HelperUtils.rawToXml(res);
        logger.info("Status: " + xmlPath.get("response.status"));

    }


}
