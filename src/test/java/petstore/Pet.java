package petstore;



import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

public class Pet {
    //3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet


    //3.2 - métodos e funçòes

    public String lerJson(String caminhoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //inclur - Create - Post
    @Test //identifica o metodo ou função como um teste para testNG
    public void incluirPet() throws IOException{
        String jsonBody = lerJson("db/pet1.json");
        //Sintaxe gherkin
        //Given - when - then
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("snoopy"))
                .body("status", is("available"));

    }

//    @Test
//    public void getPet(){
//        //String jsonBody = lerJson("db/pet1.json");
//
//        RestAssured.
//                .post(uri)
//                .statusCode(200);
//    }

}
