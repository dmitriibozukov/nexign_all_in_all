package crm;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("CRM Service")
@Feature("Customer Management")
public class CrmApiTest extends ApiTestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Создание клиента")
    @DisplayName("CRM: Успешное создание нового клиента")
    void createCustomer_ShouldReturnCreated() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }")
        .when()
            .post("/api/crm/customers")
        .then()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("email", equalTo("john@example.com"));
    }
}
