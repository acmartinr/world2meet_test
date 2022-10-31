package com.world2meet.test.persistence.repository;
import com.world2meet.test.persistence.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

//Interface for use SQL sentences in java code
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    //Load all prices with date between start and end date item and predctId ad brandId seted by param order by priority and return only 1 element
    @Query(nativeQuery = true, value = "SELECT * FROM SUPER_HERO s where name ilike concat('%',?1,'%');")
    List<SuperHero> filterSuperHeroByName(String name);
}
