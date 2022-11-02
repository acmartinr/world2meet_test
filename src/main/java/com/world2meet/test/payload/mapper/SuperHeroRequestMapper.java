package com.world2meet.test.payload.mapper;

import com.world2meet.test.payload.request.SuperHeroRequest;
import com.world2meet.test.persistence.model.SuperHero;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SuperHeroRequestMapper {
    SuperHero superHeroRequestToSuperHero(SuperHeroRequest source);

    List<SuperHero> superHeroRequestListToSuperHeroList(List<SuperHeroRequest> source);
}
