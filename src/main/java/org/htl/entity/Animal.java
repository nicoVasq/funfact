package org.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
public class Animal extends PanacheEntity {

    private String name;
    @OneToMany(mappedBy = "animal")
    @JsonbTransient
    private List<Fact> facts;


    public static Animal findAnimalByName(String name){
        return find("name", name).firstResult();
    }

    @Transactional
    public static void addAnimal(Animal animal){
        animal.persist();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Fact> facts) {
        this.facts = facts;
    }
}
