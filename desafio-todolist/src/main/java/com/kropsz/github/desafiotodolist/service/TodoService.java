package com.kropsz.github.desafiotodolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kropsz.github.desafiotodolist.entities.Todo;
import com.kropsz.github.desafiotodolist.exceptions.BadRequestException;
import com.kropsz.github.desafiotodolist.repository.TodoRepository;
import com.kropsz.github.desafiotodolist.service.util.BuilderTodo;
import com.kropsz.github.desafiotodolist.web.TodoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final BuilderTodo builder;

    public List<Todo> create(TodoDTO todoDto) {
        var todo = builder.build(todoDto);
        todoRepository.save(todo);
        return findAll();
    }

    public List<Todo> findAll() {
        var sort = Sort.by("prioridade").descending().and(
                Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(TodoDTO todo, Long id) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
            existingTodo.setDescricao(todo.descricao());
            existingTodo.setNome(todo.nome());
            existingTodo.setPrioridade(todo.prioridade());
            todoRepository.save(existingTodo);
        }, () -> {
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });

        return findAll();

    }

    public List<Todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> todoRepository.delete(existingTodo), () -> {
          throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });
        return findAll();
      }
}
