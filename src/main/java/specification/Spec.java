package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec {
    /**@param String url - url страницы к которой будет применена спецификация
     * */
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    /**@param int statusCode - принимает ожидаемый статус код
     * */
    public static ResponseSpecification responseSpecification(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void initSpec(ResponseSpecification response, RequestSpecification request){
        RestAssured.responseSpecification =response;
        RestAssured.requestSpecification = request;
    }

}
