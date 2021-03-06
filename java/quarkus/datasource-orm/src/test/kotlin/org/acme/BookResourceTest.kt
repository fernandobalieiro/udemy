package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class BookResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/book")
          .then()
             .statusCode(200)
             .body(`is`("hello"))
    }

}