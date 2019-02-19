package resources;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static resources.PayLoad.jiraSessionPost;
import static resources.Resources.JIRA_SESSION;

public class HelperUtils {

    static Properties prop = new Properties();
    static FileInputStream fis;

    {
        try {
            fis = new FileInputStream("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/env.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static XmlPath rawToXml(Response r){
        String response = r.asString();
        return new XmlPath(response);
    }

    public static JsonPath rawToJson(Response r){
        String response = r.asString();
        return new JsonPath(response);
    }

    public static String generateStringFromResources(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static String getJiraSessionID() throws IOException {
        RestAssured.baseURI = "http://localhost:8080";
        Response response = given().header("Content-Type", "application/json").
                body(jiraSessionPost).
                when().
                post(JIRA_SESSION).then().statusCode(200).
                extract().response();

        JsonPath jsonPath = HelperUtils.rawToJson(response);
        String svalue = jsonPath.get("session.value");
        System.out.println(svalue);

        return svalue;
    }
}
