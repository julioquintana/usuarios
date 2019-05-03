package com.qs.telotengo.user.service;

import java.util.List;

import com.qs.telotengo.user.dao.Address;
import com.qs.telotengo.user.dto.AddressRequest;
import com.qs.telotengo.user.dto.AddressResponse;
import com.qs.telotengo.user.dto.UserRequest;
import com.qs.telotengo.user.dto.UserResponse;
import com.qs.telotengo.user.exception.ValidationExceptions;

public interface UsuarioService {
	UserResponse saveUsuario(UserRequest usuarioRequest) throws ValidationExceptions;
	UserResponse getUsuario(String id) throws ValidationExceptions;
	List<UserResponse> getAllUsuarioCoincidencia(String coincidencia,String pais) throws ValidationExceptions;
	List<UserResponse> getAllUsuario(String pais) throws ValidationExceptions;
	UserResponse deleteUsuario(String id) throws ValidationExceptions; //softDelete
	
	List<Address> getAllAddresses(String id) throws ValidationExceptions;
	AddressResponse saveAddress(AddressRequest addressRequest, String idUsuario) throws ValidationExceptions;
	boolean deleteAddress(String idAddress) throws ValidationExceptions;
	AddressResponse setToAddressPrimary(String idAddress) throws ValidationExceptions;

	
}
