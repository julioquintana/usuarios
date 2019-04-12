package com.qs.telotengo.usuario.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qs.telotengo.usuario.dto.UsuarioRequest;
import com.qs.telotengo.usuario.dto.UsuarioResponse;
import com.qs.telotengo.usuario.exception.ValidationExceptions;
import com.qs.telotengo.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios-service")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuariosService;
	
	@PostMapping("/save")
	public HttpEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest usuarioRequest) throws ValidationExceptions {
		validateCreateRequest(usuarioRequest);
		return new ResponseEntity<UsuarioResponse>(usuariosService.saveUsuario(usuarioRequest), HttpStatus.OK);
	}
	
	@GetMapping("/details/{id}")
	public HttpEntity<UsuarioResponse> getUsuario(@PathVariable("id") Long id) throws ValidationExceptions {
		return new ResponseEntity<UsuarioResponse>(usuariosService.getUsuario(id), HttpStatus.OK);
	}
	@PutMapping("/update")
	public HttpEntity<UsuarioResponse> updateUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest) throws ValidationExceptions {
		validateCreateRequestUpdate(usuarioRequest);
		return new ResponseEntity<UsuarioResponse>(usuariosService.updateUsuario(usuarioRequest), HttpStatus.OK);
	}	
	@GetMapping("/details/{pais}/{coincidencia}")
	public HttpEntity<List<UsuarioResponse>> getAllUsuario(@PathVariable("pais") String pais, @PathVariable("coincidencia") String coincidencia ) throws ValidationExceptions {
		
		return new ResponseEntity<List<UsuarioResponse>>(usuariosService.getAllUsuario(coincidencia,pais), HttpStatus.OK);
	}
	
	
	
	
	private void validateCreateRequest(UsuarioRequest request) throws ValidationExceptions {

		if (Stream.of(
				request.getNombre(),
				request.getDireccion(),
				request.getRut()
				)
				.anyMatch(Objects::isNull)) {
			throw new ValidationExceptions("4050", "El request no debe tener datos nulos", HttpStatus.BAD_REQUEST);
		}

	}

	private void validateCreateRequestUpdate(UsuarioRequest request) throws ValidationExceptions {

		if (Stream.of(
				request.getNombre(),
				request.getCorreo(),
				request.getDireccion(),
				request.getPais(),
				request.getRut(),
				request.getTelefono()
				)
				.anyMatch(Objects::isNull)) {
			throw new ValidationExceptions("4050", "El request no debe tener datos nulos", HttpStatus.BAD_REQUEST);
		}

	}

	
	
	
}
