import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static resources.HelperUtils.generateStringFromResources;
import static resources.Resources.RES_ADDX;


public class ExtractResponseWithXml {

    @Test
    public void BasicsTestRestAssured() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/env.properties");
        prop.load(fis);
        String baseURI = prop.getProperty("HOST");
        String key = prop.getProperty("KEY");

        String xmlPost = generateStringFromResources("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/addplacepost.xml");
        RestAssured.baseURI = baseURI;
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
        System.out.println(xmlPath.get("response.status"));


    }


}
