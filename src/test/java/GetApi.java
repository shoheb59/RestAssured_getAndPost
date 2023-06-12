import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.configuration.ConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetApi {
    Properties properties = new Properties();
    FileInputStream fileInputStream;

    {
        try {
            fileInputStream = new FileInputStream("./src/test/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getApi() throws IOException {
        properties.load(fileInputStream);
        RestAssured.baseURI = properties.getProperty("baseUrl");

        Response response = given()
                .contentType("application/json")
                .when()
                .get("/api/users?page=2")
                .then()
                .assertThat()
                .statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();

        ResponseBody body = response.getBody();
        body.prettyPrint();


        System.out.println("Plain Text: " + jsonPath.get("data").toString());


    }

    public void postApi() throws ConfigurationException, IOException {

        properties.load(fileInputStream);
        RestAssured.baseURI = properties.getProperty("baseUrl");

        Response response =
                given().contentType("application/json")
                        .body(
                                "{\n" +
                                        "\"name\": \"morpheus\",\n" +
                                        "\"job\": \"leader\"\n" +
                                        "}"
                        ).
                        when()
                        .post("/api/users")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath jsonPath = response.jsonPath();

        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Palin Text for PostAPI: "+jsonPath.get().toString());




    }
}
