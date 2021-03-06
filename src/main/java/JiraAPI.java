import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static resources.HelperUtils.getConfigValue;
import static resources.HelperUtils.getJiraSessionID;
import static resources.PayLoad.*;
import static resources.Resources.*;

public class JiraAPI {
    String baseURI = getConfigValue("/env.properties","JIRAHOST");

    //@Test
    public void JiraApiCreateIssue() throws IOException {
    RestAssured.baseURI = baseURI;

    Response response = given().header("Content-Type", "application/json").
            header("Cookie", "JSESSIONID="+getJiraSessionID()).
            body(JIRACREATEISSUEBODY).
            when().
            post(J_CREATE_ISSUE).
            then().statusCode(201).
            extract().response();

        JsonPath jsonPath = HelperUtils.rawToJson(response);
        String id = jsonPath.get("id");
        System.out.println(id);

    }

   //@Test
    public void JiraApiAddComment() throws IOException {
        RestAssured.baseURI = baseURI;

        Response response = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID="+getJiraSessionID()).
                body(JIRA_ADD_COMMENT).
                when().
                post(addComment("RAT-2")).
                then().statusCode(201).
                extract().response();

        JsonPath jsonPath = HelperUtils.rawToJson(response);
        String id = jsonPath.get("id");
        System.out.println(id);

    }

    @Test
    public void JiraApiUpdateComment() throws IOException {
        RestAssured.baseURI = baseURI;

        Response response = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID="+getJiraSessionID()).
                body(JIRA_UPDATE_COMMENT).
                when().
                put(updateComment("RAT-2","10001")).
                then().statusCode(200).
                extract().response();

        JsonPath jsonPath = HelperUtils.rawToJson(response);
        String id = jsonPath.get("id");
        System.out.println(id);

    }
}
