package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class CryptoCurrencyResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/crypto")
          .then()
             .statusCode(200)
             .body(`is`("hello"))
    }

}