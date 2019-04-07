package com.qs.enviamelo.usuario.dto.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.qs.enviamelo.usuario.dao.Usuario;
import com.qs.enviamelo.usuario.dto.UsuarioRequest;
import com.qs.enviamelo.usuario.dto.UsuarioResponse;

public class BuildMapperUtil {
	@SuppressWarnings("null")
	@Autowired
	// public Mapper mapper;

	public UsuarioResponse buildResponse(Usuario usuario) {
		// try {
		UsuarioResponse userResponse = null;

		if (usuario.getId() < 1) {
			userResponse.setId(usuario.getId());
		}
		userResponse.setRut(usuario.getRut());
		userResponse.setCorreo(usuario.getCorreo());
		userResponse.setDireccion(usuario.getDireccion());
		userResponse.setNombre(usuario.getNombre());
		userResponse.setPais(usuario.getPais());
		userResponse.setTelefono(usuario.getTelefono());

		return userResponse;
		// } catch (Exception e) {
		// System.out.println(e.getCause());
		// }
		// return null;
	}

	public Usuario buildEntity(UsuarioRequest usuarioRequest) {
		// try {
		Usuario usuario = null;
		if (usuarioRequest.getId() < 1) {
			usuario.setId(usuarioRequest.getId());
		}
		usuario.setRut(usuarioRequest.getRut());
		usuario.setCorreo(usuarioRequest.getCorreo());
		usuario.setDireccion(usuarioRequest.getDireccion());
		usuario.setNombre(usuarioRequest.getNombre());
		usuario.setPais(usuarioRequest.getPais());
		usuario.setTelefono(usuarioRequest.getTelefono());
		usuario.setClave(usuarioRequest.getClave());
		return usuario;
		// } catch (Exception e) {
		// System.out.println(e.getCause());
		// }
		// return null;

	}

	public List<UsuarioResponse> buildResponseList(Iterable<Usuario> usuarioIterable) {
		List<Usuario> UsuarioRS = (List<Usuario>) usuarioIterable;

		List<UsuarioResponse> asDto = UsuarioRS.stream().map(new Function<Usuario, UsuarioResponse>() {
			@Override
			public UsuarioResponse apply(Usuario s) {

				return new UsuarioResponse(s);
			}
		}).collect(Collectors.toList());
		return asDto;
	}

}
