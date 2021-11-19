import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PetTypeResourceTest {
    @Test
    public void testPetTypeEndpoint() {
        given()
                .when().get("pets_type")
                .then()
                .statusCode(200);

    }

    @Test
    public void testPetTypeAddEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n       \"petType\":\"Bird\"\r\n}")
                .when().post("pets_type/create_pet_type")
                .then()
                .statusCode(200)
                .body("petTypeId",notNullValue())
                .body("petType",equalTo("Bird"));
    }
    @Test
    public void testPetTypeUpdateEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petTypeId",1)
                .body("{\n \"petType\":\"Cat\"\n }")
                .when().put("pets_type/update_pet_type/{petTypeId}")
                .then()
                .statusCode(200)
                .body("petTypeId",equalTo(1))
                .body("petType",equalTo("Cat"));
    }
    @Test
    public void testPetTypeDeleteEndpoint() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petTypeId", 2)
                .when().delete("pets_type/delete_pet_type/{petTypeId}")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
