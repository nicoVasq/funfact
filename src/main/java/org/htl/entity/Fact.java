package org.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.checkerframework.checker.units.qual.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Fact extends PanacheEntity {

    private String text;
    @ManyToOne
    @JoinColumn(name = "name")
    private Animal animal;
    @Column(length = 2500)
    private String source;
    private LocalDate updatedAt;


    public static List<Fact> getAllFacts(){ return listAll(); }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
