package com.randomBeerApp.service;

import com.randomBeerApp.model.Beer;
import com.randomBeerApp.repository.BeerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceTest {
    private static List<Beer> existingBeers = asList(
            new Beer(UUID.randomUUID(), "Guinness Draught", "Stout", 4.2, null),
            new Beer(UUID.randomUUID(), "Guinness Original", "Stout", 5.0, null),
            new Beer(UUID.randomUUID(), "Guinness West Indies", "Porter", 6.0, null)
    );

    @Mock
    private BeerRepository beerRepository;

    @InjectMocks
    BeerService beerService;

    @Test
    public void getRandom_ReturnsEmptyWhenNoBeersExist() {
        // given
        doReturn(Page.empty()).when(beerRepository).findAll(any(Pageable.class));

        // when
        Optional<Beer> beer = beerService.getRandom();

        // then
        assertThat(beer).isEqualTo(empty());
    }

    @Test
    public void getRandom_ReturnsOneBeerWhenOnlyOneExists() {
        // given
        Beer expectedBeer = existingBeers.get(0);
        doReturn(new PageImpl<>(singletonList(expectedBeer))).when(beerRepository).findAll(any(Pageable.class));

        // when
        Optional<Beer> beer = beerService.getRandom();

        // then
        assertThat(beer).isEqualTo(of(expectedBeer));
    }

    @Test
    public void getRandom_ReturnsABeerFromTheExistingBeers() {
        // given
        doReturn(new PageImpl<>(existingBeers)).when(beerRepository).findAll(any(Pageable.class));

        // when
        Optional<Beer> beer = beerService.getRandom();

        // then
        assertThat(beer).isIn(existingBeers.stream().map(Optional::of).collect(Collectors.toList()));
    }
}
