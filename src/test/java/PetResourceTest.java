
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
@QuarkusTest
public class PetResourceTest {
    @Test
    public void testPetEndpoint() {
        given()
                .when().get("/pets")
                .then()
                .statusCode(200);

    }
    @Test
    public void testPetAddEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"petName\":\"Columbidae\",\r\n  \"petAge\":8,\r\n   \"petType\":\"Bird\"\r\n}")
                .when().post("pets/create_pet")
                .then()
                .statusCode(200)
                .body("petId",notNullValue())
                .body("petAge",equalTo(8))
                .body("petName",equalTo("Columbidae"))
                .body("petType",equalTo("Bird"));
    }
    @Test
    public void testPetUpdateEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .body("{\n \"petName\":\"Sudda\"\n }")
                .when().put("pets/update_pet/{petId}")
                .then()
                .statusCode(200)
                .body("petId",equalTo(1))
                .body("petAge",notNullValue())
                .body("petName",equalTo("Sudda"))
                .body("petType",notNullValue());
    }
    @Test
    public void testPetDeleteEndpoint() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petId", 1)
                .when().delete("pets/delete_pet/{petId}")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
