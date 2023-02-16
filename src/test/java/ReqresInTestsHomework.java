import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresInTestsHomework {

    @Test
    void matchColorNumberInListTest() {

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.findAll { it.name == 'fuchsia rose' }.color", hasItem("#C74375"));
    }

    @Test
    void numberOfArraysListTest() {

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", hasSize(6));
    }

    @Test
    void missingParametresInBodyRequestTest() {
        String data = "{ \"name\": null, \"job\": null }"; // name and job null

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

    }

    @Test
    void MissingParametrInBodyRequestTest() {
        String data = "{ \"job\": \"leader\" }";  // missing name

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"));

    }

    @Test
    void matchingFirstandLastNameTest() {

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.findAll { it.first_name == 'Michael' }.last_name", hasItem("Lawson"));
    }

    @Test
    void allNamesInListTest() {
            // returns all names in the list

        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", containsInAnyOrder("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"));
    }
}