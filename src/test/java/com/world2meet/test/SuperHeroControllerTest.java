package com.world2meet.test;
import com.google.gson.Gson;
import com.world2meet.test.controller.SuperHeroController;
import com.world2meet.test.payload.mapper.SuperHeroRequestMapper;
import com.world2meet.test.persistence.model.SuperHero;
import com.world2meet.test.service.SuperHeroService;
import com.world2meet.test.utils.Constants;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = SuperHeroController.class)
public class SuperHeroControllerTest {
    private List<SuperHero> superHeroes;
    @MockBean
    private SuperHeroService superHeroService;
    @MockBean
    SuperHeroRequestMapper srqm;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.superHeroes = new ArrayList<>();
        this.superHeroes.add(new SuperHero(1L, "Super Man", "ocupation 1", "MARVEL", "Human", "Male"));
        this.superHeroes.add(new SuperHero(2L, "Super Girl", "ocupation 2", "MARVEL", "Human", "Woman"));
        this.superHeroes.add(new SuperHero(3L, "Spider Man", "ocupation 3", "DC", "Human", "Male"));
        this.superHeroes.add(new SuperHero(4L, "Bat Man", "ocupation 4", "DC", "Human", "Male"));
    }

    @Test
    void should_fetch_all_superheroes() throws Exception {
        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("user-api:user").getBytes()));
        given(superHeroService.getAllSuperHeroes()).willReturn(superHeroes);
        this.mockMvc.perform(get("/superhero/").header("Authorization", basicDigestHeaderValue))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(superHeroes.size())));
    }

    @Test
    void should_fetch_all_superheroes_filtered_by_name() throws Exception {
        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("user-api:user").getBytes()));
        superHeroes.remove(1);
        given(superHeroService.findSuperHeroesByName("man")).willReturn(superHeroes);
        String payload = new Gson().toJson(superHeroes);
        this.mockMvc.perform(get("/superhero/filter?name=man")
                        .header("Authorization", basicDigestHeaderValue))
                .andExpect(status().isOk())
                .andExpect(content().json(payload));
    }

    @Test
    void should_return_error_when_not_found_superhero_for_update() throws Exception {
        String basicDigestHeaderValue = "Basic " + new String(Base64.encodeBase64(("user-api:user").getBytes()));
        given(superHeroService.updateSuperHeroById(1L,new SuperHero(1L,"name","new ocupation","marvel","human","male"))).willReturn(null);
        this.mockMvc.perform(put("/superhero/232")
                        .header("Authorization", basicDigestHeaderValue)
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(Constants.NOT_SUPER_HEROES_FOUND_MSG)));
    }
}
