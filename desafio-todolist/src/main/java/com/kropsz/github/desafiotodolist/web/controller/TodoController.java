package com.kropsz.github.desafiotodolist.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
        return ResponseEntity.ok(todoService.create(todo));
    }

}