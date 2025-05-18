public class ApiTestBase {
    static {
        io.restassured.RestAssured.baseURI = "http://localhost:8080";
    }
}
