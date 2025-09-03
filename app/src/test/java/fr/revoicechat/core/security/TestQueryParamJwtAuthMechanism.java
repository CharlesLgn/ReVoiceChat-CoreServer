package fr.revoicechat.core.security;

import org.junit.jupiter.api.Test;

import fr.revoicechat.core.junit.CleanDatabase;
import fr.revoicechat.core.quarkus.profile.MultiServerProfile;
import fr.revoicechat.core.security.TestQueryParamJwtAuthMechanism.DevTestProfile;
import fr.revoicechat.core.web.tests.RestTestUtils;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;

@QuarkusTest
@CleanDatabase
@TestProfile(DevTestProfile.class)
class TestQueryParamJwtAuthMechanism {

  @Test
  void shouldFailWithoutJwt() {
    RestAssured.given()
               .when().get("/tests/secured-endpoint")
               .then()
               .statusCode(401);
  }

  @Test
  void shouldFailWithInvalidJwt() {
    RestAssured.given()
               .queryParam("jwt", "not-a-real-token")
               .when().get("/tests/secured-endpoint")
               .then()
               .statusCode(401);
  }

  @Test
  void shouldSucceedWithValidJwt() {
    String validJwt = RestTestUtils.logNewUser();
    RestAssured.given()
               .queryParam("jwt", validJwt)
               .when().get("/tests/secured-endpoint")
               .then()
               .statusCode(200); // adapt depending on what your endpoint returns
  }

  public static class DevTestProfile extends MultiServerProfile {
    @Override
    public String getConfigProfile() {
      return "dev,test";
    }
  }
}