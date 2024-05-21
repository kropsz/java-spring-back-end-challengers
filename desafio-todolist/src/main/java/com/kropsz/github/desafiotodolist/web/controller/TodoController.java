package com.kropsz.github.desafiotodolist.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kropsz.github.desafiotodolist.entities.Todo;
import com.kropsz.github.desafiotodolist.service.TodoService;
import com.kropsz.github.desafiotodolist.web.TodoDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
    
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<Todo>> create(@RequestBody @Valid TodoDTO todo) {
        return ResponseEntity.status(201).body(todoService.create(todo));
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Todo>> update(@RequestBody TodoDTO todo, @PathVariable Long id) {
        return ResponseEntity.status(200).body(todoService.update(todo, id));
    }
}