package com.randomBeerApp.controller;

import com.randomBeerApp.Application;
import com.randomBeerApp.model.Beer;
import com.randomBeerApp.repository.BeerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BeerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BeerRepository beerRepository;

    @Test
    public void random_ReturnsNotFoundWhenNoBeersExist() throws Exception {
        // given
        // then
        mockMvc.perform(get("/beer/random"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void random_ReturnsABeerWhenOneExists() throws Exception {
        // given
        Beer beer = new Beer(null, "Guinness Draught", "Stout", 4.2, null);
        beerRepository.save(beer);
        // then
        mockMvc.perform(get("/beer/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(beer.getName())))
                .andExpect(jsonPath("$.description", is(beer.getDescription())))
                .andExpect(jsonPath("$.alcoholPercentage", is(beer.getAlcoholPercentage())));
    }
}
