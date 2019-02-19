
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get {
    Properties prop = new Properties();

    @BeforeTest
    public void beforeTest() throws IOException {

        FileInputStream fis = new FileInputStream("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/env.properties");
        prop.load(fis);
    }

    @Test
    public void BasicTestRestAssured(){

        RestAssured.baseURI = prop.getProperty("HOST");
        Response response = given().
                param("AuthorName","Rahul").
        when().
                get("/Library/GetBook.php").
                then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON).and().
                body("[0].isbn", equalTo("a1b2c312")).and().
                body("[0].book_name", equalTo("Learn Appium Automation with Java")).and().
                header("Server","Apache").log().body().
                extract().response();

        JsonPath jp = HelperUtils.rawToJson(response);
        int count = jp.get("results.size()");

        for (int i = 0; i < count; i++) {
            System.out.println(jp.get("book_name["+i+"]"));
        }

    }
}
