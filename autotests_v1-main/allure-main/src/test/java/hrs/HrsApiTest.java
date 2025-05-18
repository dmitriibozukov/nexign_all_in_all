package hrs;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@Epic("HRS Service")
@Feature("Call Routing")
public class HrsApiTest extends ApiTestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Маршрутизация звонка")
    @DisplayName("HRS: Успешная маршрутизация звонка")
    void routeCall_ShouldReturnSuccess() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"caller\": \"1001\", \"receiver\": \"1002\" }")
        .when()
            .post("/api/hrs/route")
        .then()
            .statusCode(200)
            .body("status", equalTo("routed"));
    }
}
