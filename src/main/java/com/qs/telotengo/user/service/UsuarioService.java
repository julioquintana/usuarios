package com.qs.telotengo.user.service;

import java.util.List;

import com.qs.telotengo.user.dto.UserRequest;
import com.qs.telotengo.user.dto.UserResponse;
import com.qs.telotengo.user.exception.ValidationExceptions;

public interface UsuarioService {
	UserResponse saveUsuario(UserRequest usuarioRequest) throws ValidationExceptions;
	UserResponse getUsuario(Long id) throws ValidationExceptions;
	List<UserResponse> getAllUsuarioCoincidencia(String coincidencia,String pais) throws ValidationExceptions;
	List<UserResponse> getAllUsuario(String pais) throws ValidationExceptions;
	UserResponse deleteUsuario(Long id) throws ValidationExceptions; //softDelete
	//UserResponse updateUsuario(UserRequest usuarioRequest) throws ValidationExceptions;
}
