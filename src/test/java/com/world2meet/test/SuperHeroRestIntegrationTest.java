package com.world2meet.test;
import com.world2meet.test.payload.request.SuperHeroRequest;
import com.world2meet.test.persistence.model.SuperHero;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SuperHeroRestIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void should_create_super_hero_and_return_super_hero_object_cerated(){
        //Create object to add in post request
        SuperHeroRequest superHeroRequest = new SuperHeroRequest();
        superHeroRequest.setGender("male");
        superHeroRequest.setName("SUper hero 1");
        superHeroRequest.setPublisher("marvel");
        superHeroRequest.setOccupation("ocup");
        superHeroRequest.setRace("human");

        //when
        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("user-api:user").getBytes()));
        webTestClient.post().uri("http://localhost:8080/world2meet_test/api/superhero/")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(superHeroRequest)
                .exchange()
                //then
                .expectStatus().isCreated()
                .expectBody(SuperHero.class)
                .consumeWith(response ->{
                    SuperHero superHeroToCheck = response.getResponseBody();
                    assertNotNull(superHeroToCheck);
                    assertEquals("male",superHeroToCheck.getGender());
                    assertEquals("SUper hero 1",superHeroToCheck.getName());
                    assertEquals("marvel",superHeroToCheck.getPublisher());
                    assertEquals("ocup",superHeroToCheck.getOccupation());
                    assertEquals("human",superHeroToCheck.getRace());
                });
    }
}
