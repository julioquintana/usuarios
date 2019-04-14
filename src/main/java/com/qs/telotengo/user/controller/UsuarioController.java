package com.qs.telotengo.user.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qs.telotengo.user.dto.UserRequest;
import com.qs.telotengo.user.dto.UserResponse;
import com.qs.telotengo.user.exception.ValidationExceptions;
import com.qs.telotengo.user.service.UsuarioService;

@RestController
@RequestMapping("/user-service/v1/")
public class UsuarioController {

	@Autowired
	private UsuarioService usuariosService;

	@PostMapping("/save")
	public HttpEntity<UserResponse> save(@RequestBody UserRequest usuarioRequest) throws ValidationExceptions {
		validateCreateRequest(usuarioRequest);
		return new ResponseEntity<UserResponse>(usuariosService.saveUsuario(usuarioRequest), HttpStatus.OK);
	}

	@GetMapping("/details/{id}")
	public HttpEntity<UserResponse> getUsuario(@PathVariable("id") Long id) throws ValidationExceptions {
		return new ResponseEntity<UserResponse>(usuariosService.getUsuario(id), HttpStatus.OK);
	}

	@GetMapping("/list/{pais}/{coincidencia}")
	public HttpEntity<List<UserResponse>> getAllUsuarioCoincidencia(@PathVariable("pais") String pais,
			@PathVariable("coincidencia") String coincidencia) throws ValidationExceptions {
		return new ResponseEntity<List<UserResponse>>(usuariosService.getAllUsuarioCoincidencia(coincidencia, pais), HttpStatus.OK);
	}
	@GetMapping("/list/{pais}")
	public HttpEntity<List<UserResponse>> getAllUsuario(@PathVariable("pais") String pais) throws ValidationExceptions {
		return new ResponseEntity<List<UserResponse>>(usuariosService.getAllUsuario(pais), HttpStatus.OK);
	}

	private void validateCreateRequest(UserRequest request) throws ValidationExceptions {

		if (Stream.of(request.getName(), request.getCountry(), request.getAddress(), request.getRut()).anyMatch(Objects::isNull)) {
			throw new ValidationExceptions("4050", "El request no debe tener datos nulos", HttpStatus.BAD_REQUEST);
		}

	}
}
