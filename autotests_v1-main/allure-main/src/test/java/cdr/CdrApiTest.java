package cdr;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

@Epic("CDR Service")
@Feature("CDR File Management")
public class CdrApiTest extends ApiTestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Генерация CDR-файла")
    @DisplayName("CDR: Генерация файла должна возвращать 202")
    void generateCdrFile_ShouldReturnAccepted() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/api/cdr/generate")
        .then()
            .statusCode(202);
    }
}
