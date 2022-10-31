package com.world2meet.test.service;

import com.world2meet.test.persistence.model.SuperHero;
import com.world2meet.test.persistence.repository.SuperHeroRepository;
import com.world2meet.test.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroService {
    @Autowired
    SuperHeroRepository superHeroRepository;

    //getting all super heroes records
    public List<SuperHero> getAllSuperHeroes() {
        return new ArrayList<>(superHeroRepository.findAll());
    }

    //getting all prices records
    public Optional<SuperHero> getSuperHeroById(Long id) {
        return superHeroRepository.findById(id);
    }



    //getting all prices records
    public int deleteSuperHeroById(Long id) {
        if (getSuperHeroById(id).isPresent()) {
            superHeroRepository.deleteById(id);
            return Constants.OBJECT_FOUNDED_CODE;
        } else {
            return Constants.NOT_SUPER_HEROES_FOUND_CODE;
        }
    }

    //get super hero that match name
    public List<SuperHero> getSuperHeroesByName(String name) {
        return superHeroRepository.filterSuperHeroByName(name);
    }

}
