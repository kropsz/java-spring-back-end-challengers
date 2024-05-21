package com.kropsz.github.desafiotodolist.service.util;

import org.springframework.stereotype.Component;

import com.kropsz.github.desafiotodolist.entities.Todo;
import com.kropsz.github.desafiotodolist.web.TodoDTO;

@Component
public class BuilderTodo {
    
    public Todo build(TodoDTO todoDTO) {
        return Todo.builder()
            .nome(todoDTO.nome())
            .descricao(todoDTO.descricao())
            .finalizado(false)
            .prioridade(todoDTO.prioridade())
            .build();
    }
}
