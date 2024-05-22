package com.kropsz.github.desafiotodolist;

import java.util.ArrayList;
import java.util.List;

import com.kropsz.github.desafiotodolist.entities.Todo;

public class TodoConstants {
    public static final List<Todo> TODOS = new ArrayList<>() {
    {
      add(new Todo(9995L, "@pedroesteves", "Estudar", false, 1));
      add(new Todo(9996L, "@pedroesteves", "Academia", false, 1));
      add(new Todo(9997L, "@pedroesteves", "Pilotar", false, 1));
      add(new Todo(9998L, "@pedroesteves", "Jogar", false, 1));
      add(new Todo(9999L, "@pedroesteves", "Ver filmes", false, 1));
    }
  };

  public static final Todo TODO = TODOS.get(0);
}

