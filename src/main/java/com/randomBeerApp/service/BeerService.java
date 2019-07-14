package com.randomBeerApp.service;

import com.randomBeerApp.model.Beer;
import com.randomBeerApp.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.PageRequest.of;

@Service
public class BeerService {
    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Optional<Beer> getRandom() {
        int idx = (int) (Math.random() * beerRepository.count());
        return beerRepository.findAll(of(idx, 1))
                .getContent()
                .stream()
                .findFirst();
    }
}
