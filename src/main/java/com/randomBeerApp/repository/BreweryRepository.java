package com.randomBeerApp.repository;

import com.randomBeerApp.model.Brewery;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface BreweryRepository extends PagingAndSortingRepository<Brewery, UUID> {
}
