package gorest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class UsersListTest {
    String URL = "https://gorest.co.in/public/v2/users";
    String ONE_USER = URL+"/{userId}/accounts/{accountId}";
    String INACTIVE_USERS = URL + "?status=inactive";
    @Test
    public void arrayListIsNotEmpty(){
        RestAssured.given()
                .log().all()
                .when()
                .get(URL)
                .then().log().all();
    }
    @Test
    public void getOneUser(){
        /*return only one user*/
        Map<String, Integer> params = new HashMap<>() ;
        params.put("userId", 2874);
        params.put("accountId", 56);
        RestAssured.given().log().all()
                .pathParams(params)
                .when()
                .get(ONE_USER)
                .then().log().all();
    }

    @Test
    public void getInactiveUsers(){
        /*added filter to query and return only inactive users*/
        RestAssured.given().log().all()
                .when().get(INACTIVE_USERS)
                .then().log().all();
    }
}
