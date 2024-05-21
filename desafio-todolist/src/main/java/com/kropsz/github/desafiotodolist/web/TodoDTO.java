package com.kropsz.github.desafiotodolist.web;

import jakarta.validation.constraints.NotBlank;

public record TodoDTO(
@NotBlank        
String nome, 
@NotBlank
String descricao, int prioridade) {
    
}
