package resources;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static resources.PayLoad.jiraSessionPost;
import static resources.Resources.JIRA_SESSION;

public class HelperUtils {

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

    public static String getConfigValue(String fileName, String key) {
        File resourceDirectory = new File("src/main/resources");
        Properties prop = new Properties();
        String value = null;
        try {
            InputStream input = new FileInputStream(resourceDirectory.getPath()+fileName);
            prop.load(input);
            value = prop.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
