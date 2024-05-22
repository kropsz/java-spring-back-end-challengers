package com.kropsz.github.desafiotodolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import static com.kropsz.github.desafiotodolist.TodoConstants.TODO;
import static com.kropsz.github.desafiotodolist.TodoConstants.TODOS;

import com.kropsz.github.desafiotodolist.entities.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class TodoListTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webTestClient
				.post()
				.uri("/todos/create")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].finalizado").isEqualTo(todo.isFinalizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos/create")
				.bodyValue(
						new Todo("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	void testUpdateTodoSuccess() {
		var todo = new Todo(TODO.getId(), TODO.getNome() + " Up", TODO.getNome() + " Up", TODO.isFinalizado(),
				TODO.getPrioridade() + 1);

		webTestClient
				.put()
				.uri("/todos/" + TODO.getId())
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].finalizado").isEqualTo(todo.isFinalizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testUpdateTodoFailure() {
		var unexinstingId = 1L;

		webTestClient
				.put()
				.uri("/todos/" + unexinstingId)
				.bodyValue(
						new Todo(unexinstingId, "", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

    @Test
    @Sql("/import.sql")
    void testDoneTodoSuccess() {
        webTestClient
                .put()
                .uri("/todos/" + TODOS.get(0).getId() + "/done")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(5)
                .jsonPath("$[0].nome").isEqualTo(TODOS.get(0).getNome())
                .jsonPath("$[0].descricao").isEqualTo(TODOS.get(0).getDescricao())
                .jsonPath("$[0].finalizado").isEqualTo(true)
                .jsonPath("$[0].prioridade").isEqualTo(TODOS.get(0).getPrioridade());
    }

    @Test
    void testDoneTodoFailure() {
        var unexinstingId = 1L;

        webTestClient
                .put()
                .uri("/todos/" + unexinstingId + "/done")
                .exchange()
                .expectStatus().isBadRequest();
    }

	@Sql("/import.sql")
	@Test
	void testDeleteTodoSuccess() {
		webTestClient
				.delete()
				.uri("/todos/" + TODOS.get(0).getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(4)
				.jsonPath("$[0].nome").isEqualTo(TODOS.get(1).getNome())
				.jsonPath("$[0].descricao").isEqualTo(TODOS.get(1).getDescricao())
				.jsonPath("$[0].finalizado").isEqualTo(TODOS.get(1).isFinalizado())
				.jsonPath("$[0].prioridade").isEqualTo(TODOS.get(1).getPrioridade());
	}

	@Test
	void testDeleteTodoFailure() {
		var unexinstingId = 1L;

		webTestClient
				.delete()
				.uri("/todos/" + unexinstingId)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	void testListTodos() throws Exception {
		webTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0]").isEqualTo(TODOS.get(0))
				.jsonPath("$[1]").isEqualTo(TODOS.get(1))
				.jsonPath("$[2]").isEqualTo(TODOS.get(2))
				.jsonPath("$[3]").isEqualTo(TODOS.get(3))
				.jsonPath("$[4]").isEqualTo(TODOS.get(4));
	}
}