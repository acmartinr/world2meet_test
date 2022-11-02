package com.world2meet.test.persistence.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SUPER_HERO")
public class SuperHero {
    //mark id as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //defining id as column name

    private String name;

    private String occupation;

    private String publisher;

    private String race;

    private String gender;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public SuperHero(long id, String name, String occupation, String publisher, String race, String gender) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.publisher = publisher;
        this.race = race;
        this.gender = gender;
    }

    public SuperHero() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHero superHero = (SuperHero) o;
        return id == superHero.id && Objects.equals(name, superHero.name) && Objects.equals(occupation, superHero.occupation) && Objects.equals(publisher, superHero.publisher) && Objects.equals(race, superHero.race) && Objects.equals(gender, superHero.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, occupation, publisher, race, gender);
    }
}
