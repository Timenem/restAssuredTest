package gorest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class UsersListTest {
    String URL = "https://gorest.co.in/public/v2";

    // Endpoints
    String ONE_USER = "/users/{userId}";
    String INACTIVE_USERS ="/users?status=inactive";
    String COMMENTS_URL = "/comments/{commentId}";

    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = URL;
    }

    @Test
    public void arrayListIsNotEmpty(){
        RestAssured.given()
                .log().all()
                .when()
                .get("users")
                .then().log().all()
                .body("$.size()", Matchers.equalTo(20));
    }
    @Test
    public void getOneUser() {
        /*return only one user*/
        Map<String, Integer> params = new HashMap<>();
        params.put("userId", 2874);
        RestAssured.given().log().all()
                .pathParams(params)
                .when()
                .get(ONE_USER)
                .then().log().all()
                .body("$",Matchers.hasEntry("id", 2874));
    }

    @Test
    public void getInactiveUsers(){
        /*added filter to query and return only inactive users*/
        RestAssured.given().log().all()
                .when().get(INACTIVE_USERS)
                .then().log().all()
                .body("$", Matchers.everyItem(Matchers.hasEntry("status", "inactive")));
    }

    @Test
    public void getActiveFemaleUsers(){
        /*return only active female users*/
        Map<String,String> filter = new HashMap<>();
        filter.put("gender", "female");
        filter.put("status", "active");
        RestAssured.given().log().all()
                .queryParams(filter)
                .when()
                .get("users")
                .then().log().all()
                .body("$", Matchers.everyItem(Matchers.hasEntry("gender", "female")))
                .body("$", Matchers.everyItem(Matchers.hasEntry("status", "active")))
                .body("[0].id", Matchers.notNullValue());

    }

    @Test
    public void getCommentUser(){
        RestAssured.given().log().all()
                .pathParams("commentId", 2)
                .when()
                .get(COMMENTS_URL)
                .then().log().all()
                .body("id", Matchers.equalTo(2));
    }
}
