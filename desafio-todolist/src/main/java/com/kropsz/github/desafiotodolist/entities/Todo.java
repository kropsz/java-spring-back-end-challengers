package com.kropsz.github.desafiotodolist.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_todo")
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private boolean finalizado;
    private int prioridade;
    
    public Todo(String nome, String descricao, boolean finalizado, int prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.finalizado = finalizado;
        this.prioridade = prioridade;
    }

    public boolean isFinalizado() {
        return finalizado;
      }
}
