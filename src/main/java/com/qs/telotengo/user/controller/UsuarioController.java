package com.qs.telotengo.user.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

import com.qs.telotengo.user.dao.Address;
import com.qs.telotengo.user.dto.AddressRequest;
import com.qs.telotengo.user.dto.AddressResponse;
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
	public HttpEntity<UserResponse> save(@Valid @RequestBody UserRequest usuarioRequest) throws ValidationExceptions {
		validateCreateRequest(usuarioRequest);
		return new ResponseEntity<UserResponse>(usuariosService.saveUsuario(usuarioRequest), HttpStatus.OK);
	}

	@GetMapping("/details/{id}")
	public HttpEntity<UserResponse> getUsuario(@PathVariable("id") String id) throws ValidationExceptions {
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

	@PutMapping("/delete/{id}")
	public HttpEntity<UserResponse> deleteUsuario(@PathVariable("id") String id) throws ValidationExceptions {
		return new ResponseEntity<UserResponse>(usuariosService.deleteUsuario(id), HttpStatus.OK);
	}

	
	
	
	//request de Adresses
	
	//todas las direcciones de un usuario
	@GetMapping("/	/{id}")
	public HttpEntity<List<Address>> getAllAddresses(@PathVariable("id") String pais) throws ValidationExceptions {
		return new ResponseEntity<List<Address>>(usuariosService.getAllAddresses(pais), HttpStatus.OK);
	}

	//guardar direccion de un usuario
	@PostMapping("/save/address/{id}")
	public HttpEntity<AddressResponse> saveAddresses(@Valid @RequestBody AddressRequest addressRequest, @PathVariable("id") String id) throws ValidationExceptions {
		validateCreateRequestAddress(addressRequest);
		return new ResponseEntity<AddressResponse>(usuariosService.saveAddress(addressRequest, id), HttpStatus.OK);
	}

	//borrar direccion 
	@PutMapping("/delete/address/{id}")
	public HttpEntity<AddressResponse> deleteAddresses(@PathVariable("id") String idAddress) throws ValidationExceptions {
		usuariosService.deleteAddress(idAddress);
		return new ResponseEntity<AddressResponse>(HttpStatus.OK);
	}

	//Cambiar a direccion principal
	@GetMapping("/edit/address/setprimary/{id}")
	public HttpEntity<AddressResponse> setToAddressPrimary(@PathVariable("id") String idAddress) throws ValidationExceptions {
		return new ResponseEntity<AddressResponse>(usuariosService.setToAddressPrimary(idAddress), HttpStatus.OK);
	}


	
	
	private void validateCreateRequestAddress(AddressRequest request) throws ValidationExceptions {

		if (Stream.of(request.getAddress(),request.getCountry(), request.getCity(), request.getComuna(), request.getPhone()).anyMatch(Objects::isNull)) {
			throw new ValidationExceptions("4050", "El request no debe tener datos nulos", HttpStatus.BAD_REQUEST);
		}
		if(!isNumeric(request.getPhone()) || !cantidadDigitosValidos(request.getPhone()) ){
        	 throw new ValidationExceptions("1515", "No es un numero de telefono valido", HttpStatus.BAD_REQUEST);
        }
		
		//&& (request.getPhone().length() > 8 && request.getPhone().length() < 14)
		//&& !(request.getPhone().isEmpty())
		
		
	}
	
	
	private void validateCreateRequest(UserRequest request) throws ValidationExceptions {

		if (Stream.of(request.getName(), request.getRut()).anyMatch(Objects::isNull)) {
			throw new ValidationExceptions("4050", "El request no debe tener datos nulos", HttpStatus.BAD_REQUEST);
		}
		//if(!isNumeric(request.getPhone()) || !cantidadDigitosValidos(request.getPhone()) ){
        //	 throw new ValidationExceptions("1515", "No es un numero de telefono valido", HttpStatus.BAD_REQUEST);
        //}
		
		//&& (request.getPhone().length() > 8 && request.getPhone().length() < 14)
		//&& !(request.getPhone().isEmpty())
		
		
	}
	private static boolean cantidadDigitosValidos(String phone) {
		if(phone.length() > 8 && phone.length() < 14) {
			return true;
		}
		return false;		
	}
	private static boolean isNumeric(String cadena){
		try {
			Long.parseLong(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}