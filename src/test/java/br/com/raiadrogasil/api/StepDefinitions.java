package br.com.raiadrogasil.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import br.com.raiadrogasil.api.entity.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SuppressWarnings("rawtypes")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

	@LocalServerPort
	private int port;

	private TestRestTemplate testRestTemplate;

	private String postUrl = "http://localhost:";

	private String type;

	private User user;

	private ResponseEntity response;

	private boolean mustResponseOk;

	private String searchId = "";

	private StepDefinitions() {
		testRestTemplate = new TestRestTemplate();
	}

	@Given("^Irei executar um (.*)$")
	public void i_can_create_a_new_post(String type) {
		this.type = type;
	}

	@Given("^Com o id (.*), o nome (.*) e o email (.*)$")
	public void i_sending_post_to_be_created_with_post_id_new_ID_title_Post_title_and_content_This_is_content(Long id,
			String name, String email) {
		this.user = new User(id == 0 ? null : id, name, email, true);

	}

	@Given("^Buscando pelo id (.*)$")
	public void com_o_id(String id) {
		this.searchId = id;
	}

	@When("executar a chamada")
	public void executar_a_chamada() throws RestClientException, URISyntaxException {
		switch (type) {
		case "post": {
			this.mustResponseOk = this.user.getName() != null && !this.user.getName().isBlank()
					&& this.user.getEmail() != null && !this.user.getEmail().isBlank();
			this.response = this.testRestTemplate.postForEntity(new URI(postUrl.concat(Integer.toString(port))),
					this.user, User.class);
			break;
		}
		case "get": {
			if (this.searchId.isBlank()) {
				this.response = this.testRestTemplate.exchange(postUrl.concat(Integer.toString(port)), HttpMethod.GET,
						null, List.class);
			} else {
				this.response = this.testRestTemplate.exchange(
						postUrl.concat(Integer.toString(port)).concat("/").concat(this.searchId), HttpMethod.GET, null,
						User.class);
			}
			break;
		}
		case "put": {
			this.mustResponseOk = this.user.getId() != null && this.user.getName() != null
					&& !this.user.getName().isBlank() && this.user.getEmail() != null
					&& !this.user.getEmail().isBlank();

			this.response = this.testRestTemplate.exchange(
					postUrl.concat(Integer.toString(port)).concat("/").concat(this.user.getId().toString()),
					HttpMethod.PUT, new HttpEntity<User>(this.user), User.class);
			break;
		}
		case "delete": {
			break;
		}
		}
	}

	@SuppressWarnings("deprecation")
	@Then("Devo verificar o resultado da execução")
	public void i_should_be_able_to_see_my_newly_created_post() {
		System.out.println(response);
		if (this.mustResponseOk) {
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		} else {
			assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
		}
	}

	@Then("^Devo verificar o resultado da consulta (.*)$")
	public void devo_verificar_o_resultado_da_consulta_true(boolean mustFind) {
		System.out.println(response);
		if (mustFind) {
			assertNotNull(response.getBody());
		} else {
			assertNull(response.getBody());
		}
	}

	@Then("Devo verificar o resultado da listagem")
	public void devo_verificar_o_resultado_da_listagem() {
		assertNotNull(response.getBody());
		assertEquals(4, ((List) this.response.getBody()).size());
	}
}
