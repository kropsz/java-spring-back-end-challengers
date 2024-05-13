package com.github.kropsz.poi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "point_of_interest")
public class PointOfInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long x;
    private Long y;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getX() {
        return x;
    }
    public void setX(Long x) {
        this.x = x;
    }
    public Long getY() {
        return y;
    }
    public void setY(Long y) {
        this.y = y;
    }
    public PointOfInterest() {
    }
    public PointOfInterest(String nome, Long x, Long y) {
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    
    
}
