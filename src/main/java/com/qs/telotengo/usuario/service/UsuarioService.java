package com.qs.telotengo.usuario.service;

import java.util.List;

import com.qs.telotengo.usuario.dto.UsuarioRequest;
import com.qs.telotengo.usuario.dto.UsuarioResponse;
import com.qs.telotengo.usuario.exception.ValidationExceptions;

public interface UsuarioService {
	UsuarioResponse saveUsuario(UsuarioRequest usuarioRequest);
	UsuarioResponse getUsuario(Long id) throws ValidationExceptions;
	List<UsuarioResponse> getAllUsuario(String coincidencia,String pais) throws ValidationExceptions;
	UsuarioResponse deleteUsuario(Long id) throws ValidationExceptions; //elimiacion Logica
	UsuarioResponse updateUsuario(UsuarioRequest usuarioRequest) throws ValidationExceptions;
}
