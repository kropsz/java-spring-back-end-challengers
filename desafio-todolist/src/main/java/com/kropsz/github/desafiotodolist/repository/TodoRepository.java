package com.kropsz.github.desafiotodolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kropsz.github.desafiotodolist.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
