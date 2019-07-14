package com.randomBeerApp.controller;

import com.randomBeerApp.model.Beer;
import com.randomBeerApp.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/beer")
public class BeerController {
    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/random")
    public Beer random() throws ResourceNotFoundException {
        return beerService.getRandom().orElseThrow(ResourceNotFoundException::new);
    }
}
