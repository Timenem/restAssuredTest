import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.CreateUser;
import pojo.Sportsman;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ApiTests {

    private static final String URl = "http://dummy.restapiexample.com/";

    @Test
    public void getAllSportsmanTest(){
        List<Sportsman> sportsmanList = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URl+"api/v1/employees")
                .then().log().all()
                .extract().body().jsonPath().getList("data", Sportsman.class);
        Assertions.assertEquals(sportsmanList.size() ,24);
    }


    @Test
    public void getOneSportsman() {
        given().baseUri(URl)
               .contentType(ContentType.JSON)
               .request(Method.GET, "api/v1/employee/1")
               .then()
               .statusCode(200)
               .and()
               .contentType(ContentType.JSON);

    }

    @Test
    public void createUserTest() {
        //create new User
        String age = "32", salary = "2311", name = "tomas";
        String body = String.format("{name:%s","salary:%s","age:%s}", name, salary, age);

        Response response = given().baseUri(URl)
                .contentType(ContentType.JSON)
                .body(body)
                .request(Method.POST, "api/v1/create");
        String responseBody = response.getBody().asString();
        Assertions.assertEquals(response.getStatusCode(),200);
        Assertions.assertNotNull(responseBody);
        Assertions.assertTrue(responseBody.contains("Successfully! Record has been added."));
    }

    @Test
    public void deleteUser(){
        //delete user
        Response response = given().baseUri(URl)
                .when()
                .request(Method.DELETE,"api/v1/delete/-");
        Assertions.assertEquals(response.statusCode() , 200);
        Assertions.assertTrue(response.body().asString().contains("Successfully! Record has been deleted"));

    }
}
