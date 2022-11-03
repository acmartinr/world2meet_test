package com.world2meet.test;

import com.google.gson.Gson;
import com.world2meet.test.payload.mapper.SuperHeroRequestMapper;
import com.world2meet.test.payload.request.SuperHeroRequest;
import com.world2meet.test.persistence.model.SuperHero;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SuperHeroRestIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    SuperHeroRequestMapper srqm;

    @Test
    void testCreateSuperHero(){
        SuperHeroRequest superHeroRequest = new SuperHeroRequest();
        superHeroRequest.setGender("male");
        superHeroRequest.setName("SUper hero 1");
        superHeroRequest.setPublisher("marvel");
        superHeroRequest.setOccupation("ocup");
        superHeroRequest.setRace("human");

        SuperHero superHero = srqm.superHeroRequestToSuperHero(superHeroRequest);

        //Set id with last id in rows inserted -1
        superHero.setId(5L);
        String superHeroResponse = new Gson().toJson(superHero);
        //when
        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("user-api:user").getBytes()));
        webTestClient.post().uri("http://localhost:8080/world2meet_test/api/superhero/")
                .header("Authorization", basicDigestHeaderValue)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(superHeroRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(superHeroResponse);
    }
}
