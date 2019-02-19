import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.HelperUtils;

import static io.restassured.RestAssured.given;
import static resources.PayLoad.addBook;
import static resources.Resources.LIB_RES_ADD;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void PostJsonFromDataProvider(String aisle, String isbn){

        RestAssured.baseURI="http://216.10.245.166";
        Response response = given().
                header("Content-Type","application/json").
                queryParam("key", "qaclick123").log().all().
                body(addBook(aisle, isbn)).
                when().
                post(LIB_RES_ADD).
                then().
                assertThat().statusCode(200).
                extract().response();

        JsonPath jsonPath = HelperUtils.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);
    }

    @DataProvider (name="BooksData")
    public Object[][] getData(){
        return new Object[][] {{"wfvhui", "5544"},{"zzaaqq", "112244"},{"ppoommnn","8876565"}};
    }


}
