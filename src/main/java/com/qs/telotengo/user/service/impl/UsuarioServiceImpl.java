package com.qs.telotengo.user.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.qs.telotengo.user.dao.User;
import com.qs.telotengo.user.dao.repository.UsuarioRepository;
import com.qs.telotengo.user.dto.UserRequest;
import com.qs.telotengo.user.dto.UserResponse;
import com.qs.telotengo.user.exception.ValidationExceptions;
import com.qs.telotengo.user.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger LOGGER = Logger.getLogger( UsuarioServiceImpl.class.getName() );
	
	
	@Autowired
	private UsuarioRepository dao;

	@Autowired
	private Mapper mapper;

	@Override
	public UserResponse saveUsuario(UserRequest usuarioRequest) throws ValidationExceptions {
		if (Objects.isNull(usuarioRequest.getId())) {
			usuarioRequest.setId(-1L);
		}
		Optional<User> userExist = dao.findByIdAndStatusIsTrue(usuarioRequest.getId());
		User usuario;
		if (usuarioRequest.getId() == -1L && !userExist.isPresent()){
			usuario = saveUserAndVerifyExistsRut(buildEntity(usuarioRequest));
		}else{
			usuario = updateUser(userExist,usuarioRequest);
		}
		return buildResponse(usuario);
	}
	
	private boolean existsByRut(String rut) {
		return dao.existsByRut(rut);
	}
	
	private User saveUserAndVerifyExistsRut(User user) throws ValidationExceptions {
		if (!existsByRut(user.getRut())) {
			user.setStatus(true);
			user = dao.save(user);
		} else {
			throw new ValidationExceptions("0107","No se puede agregar, Existe una cuenta de usuario actualmente para este rut, Rut: "+
					user.getRut(),HttpStatus.OK);
		}		
		return user;
	}
	private User updateUser(Optional<User> userExist , UserRequest userRequest) throws ValidationExceptions {
		if(dao.existsByIdAndStatusIsTrue(userRequest.getId())) {
			userExist.get().setAddress(userRequest.getAddress());
			userExist.get().setName(userRequest.getName());
			userExist.get().setPhone(userRequest.getPhone());
		}else {
			throw new ValidationExceptions("0108","No se puede modificar, No existe una cuenta de usuario para este id, Id: "+
					userRequest.getId(),HttpStatus.OK);
		}
		return dao.save(userExist.get());
	}

	@Override
	public UserResponse getUsuario(Long id) throws ValidationExceptions {
		Optional<User> user = dao.findByIdAndStatusIsTrue(id);
		LOGGER.info("Message: "+user.toString());

		UserResponse uResponse = buildResponse(dao.findByIdAndStatusIsTrue(id).orElseThrow(
				() -> new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK)));
		return uResponse;
	}

	@Override
	public List<UserResponse> getAllUsuarioCoincidencia(String coincidencia, String pais) throws ValidationExceptions {
		Iterable<User> userList = dao.findByNameContainingIgnoreCaseAndCountryAndStatusIsTrue(coincidencia, pais);
		if (IterableUtils.isEmpty(userList)) {
			throw new ValidationExceptions("4030", "La lista de usuarios esta vacia", HttpStatus.OK);
		}
		return buildResponseList(userList);
	}

	@Override
	public List<UserResponse> getAllUsuario(String pais) throws ValidationExceptions {
		Iterable<User> userList = dao.findByCountryAndStatusIsTrue(pais);
		if (IterableUtils.isEmpty(userList)) {
			throw new ValidationExceptions("4030", "La lista de usuarios esta vacia", HttpStatus.OK);
		}
		return buildResponseList(userList);
	}

	@Override
	public UserResponse deleteUsuario(Long id) throws ValidationExceptions {
		Optional<User> userDel = dao.findByIdAndStatusIsTrue(id);
		if (!userDel.isPresent()) {
			throw new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK);
		}
		User usuario = userDel.get();
		usuario.setStatus(false);
		return buildResponse(dao.save(usuario));
	}

	public UserResponse buildResponse(User usuario) {
		try {
			return mapper.map(usuario, UserResponse.class);

		} catch (RuntimeException e) {
			
			LOGGER.info("BulidResponse" + e.getCause());
		}
		return null;
	}

	public User buildEntity(UserRequest usuarioRequest) {
		try {
			return mapper.map(usuarioRequest, User.class);
		} catch (RuntimeException e) {
			
			LOGGER.info("BulidEntity: " + e.getMessage());
		}
		return null;

	}

	public List<UserResponse> buildResponseList(Iterable<User> usuarioIterable) {
		List<User> UsuarioRS = (List<User>) usuarioIterable;

		List<UserResponse> asDto = UsuarioRS.stream().map(new Function<User, UserResponse>() {
			@Override
			public UserResponse apply(User s) {

				return new UserResponse(s);
			}
		}).collect(Collectors.toList());
		return asDto;
	}

}