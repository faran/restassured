import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.HelperUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static resources.HelperUtils.rawToJson;

public class TwitterAPI {

    private final String ConsumerKey = "rwGUo56zwdCyf7auoKLELVjMi";
    private final String ConsumerSecret = "vM0OsBcTuqADvsmKSyvzQO2NHIKi4N4KNm7nwCcu6I9U3Vt53Z";
    private final String Token = "21671123-P0W8iS0bylU3RifoHEbuRKWf00OBnrR1Jb4J78im9";
    private final String TokenSecret = "2cvBl9XspyV3lZVBROaVKy4hSp2oxtHRuyGpZyBgxS3Ua";


    Properties prop = new Properties();
    @BeforeTest
    public void beforeTest() throws IOException {

        FileInputStream fis = new FileInputStream("/Users/fkha0003/Downloads/graphwalker-example/restassured/src/main/resources/env.properties");
        prop.load(fis);
    }

    //@Test
    public void getLatestTweets(){

        RestAssured.baseURI = prop.getProperty("TWITTERHOST");
        Response response = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret, OAuthSignature.HEADER).

                queryParam("count", 1)
                .when().get("/home_timeline.json").then().extract().response();

//        ArrayList<Object> text = new ArrayList<>(Arrays.asList(jsonPath.get("text")), Arrays.asList(jsonPath.get("id")));
//        ArrayList<Object> id = new ArrayList<>(Arrays.asList(jsonPath.get("id")));

//        System.out.println(id);
//        System.out.println(text);
    }

    //@Test
    public void createTweet(){

        RestAssured.baseURI = prop.getProperty("TWITTERHOST");
        Response response = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret, OAuthSignature.HEADER).

                queryParam("status", "Tweet from Rest Assured 2")
                .when().post("/update.json").then().extract().response();

        Long tweet = HelperUtils.rawToJson(response).get("id");
        System.out.println(tweet);

    }

    //@Test
    public void deleteTweet(){

        RestAssured.baseURI = prop.getProperty("TWITTERHOST");
        Response response = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret, OAuthSignature.HEADER).

                when().post("/destroy/1097896113867317249.json").then().extract().response();

        Long tweet = HelperUtils.rawToJson(response).get("id");
        System.out.println(tweet);

    }

    @Test
    public void getMyLatestTweets(){

        RestAssured.baseURI = prop.getProperty("TWITTERHOST");
        Response response = given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret, OAuthSignature.HEADER).

                queryParam("screen_name", "faaran").queryParam("count", 2)
                .when().get("/home_timeline.json").then().extract().response();

//        ArrayList<Object> text = new ArrayList<>(Arrays.asList(jsonPath.get("text")), Arrays.asList(jsonPath.get("id")));
//        ArrayList<Object> id = new ArrayList<>(Arrays.asList(jsonPath.get("id")));

//        System.out.println(id);
//        System.out.println(text);
    }
}
