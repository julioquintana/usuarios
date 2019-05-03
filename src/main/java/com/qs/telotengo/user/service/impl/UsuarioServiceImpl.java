package com.qs.telotengo.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.bson.types.ObjectId;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.NullableUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mongodb.lang.Nullable;
import com.qs.telotengo.user.dao.Address;
import com.qs.telotengo.user.dao.User;
import com.qs.telotengo.user.dao.repository.UsuarioRepository;
import com.qs.telotengo.user.dto.AddressRequest;
import com.qs.telotengo.user.dto.AddressResponse;
import com.qs.telotengo.user.dto.UserRequest;
import com.qs.telotengo.user.dto.UserResponse;
import com.qs.telotengo.user.exception.ValidationExceptions;
import com.qs.telotengo.user.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger LOGGER = Logger.getLogger(UsuarioServiceImpl.class.getName());

	@Autowired
	private UsuarioRepository dao;

	@Autowired
	private Mapper mapper;

	@Override
	public UserResponse saveUsuario(UserRequest usuarioRequest) throws ValidationExceptions {
		Optional<User> userExist = dao.findByIdAndStatusIsTrue(usuarioRequest.getId());
		User usuario;
		if (Objects.isNull(usuarioRequest.getId()) && !userExist.isPresent()) {
			usuario = saveUserAndVerifyExistsRut(buildEntity(usuarioRequest));
		} else {
			usuario = updateUser(userExist, usuarioRequest);
		}
		return buildResponse(usuario);
	}

	private boolean existsByRut(String rut) {
		return dao.existsByRut(rut);
	}

	private List<Address> setIdToAddress(List<Address> lista) {
		for (int contador = 0; contador < lista.size(); contador++) {
			lista.get(contador).setId(new ObjectId().toString());
			lista.get(contador).setStatus(true);
		}
		return lista;
	}

	private User saveUserAndVerifyExistsRut(User user) throws ValidationExceptions {
		if (!existsByRut(user.getRut())) {
			user.setStatus(true);
			if (!Objects.isNull(user.getAddresses())) {
				user.setAddresses(setIdToAddress(user.getAddresses()));
			}
			user = dao.save(user);
			LOGGER.info("Guardando usuario " + user);
		} else {
			throw new ValidationExceptions("0107",
					"No se puede agregar, Existe una cuenta de usuario actualmente para este rut, Rut: "
							+ user.getRut(),
					HttpStatus.OK);
		}
		return user;
	}

	private User updateUser(Optional<User> userExist, UserRequest userRequest) throws ValidationExceptions {
		if (dao.existsByIdAndStatusIsTrue(userRequest.getId())) {
			userExist.get().setName(userRequest.getName());
			if (!Objects.isNull(userRequest.getAddresses())) {
				userExist.get().setAddresses(userRequest.getAddresses());
			}
		} else {
			throw new ValidationExceptions("0108",
					"No se puede modificar, No existe una cuenta de usuario para este id, Id: " + userRequest.getId(),
					HttpStatus.OK);
		}
		LOGGER.info("modificando usuario " + userExist.get());
		return dao.save(userExist.get());
	}

	@Override
	public UserResponse getUsuario(String id) throws ValidationExceptions {
		Optional<User> user = dao.findByIdAndStatusIsTrue(id);
		LOGGER.info("Message: " + user.toString());

		UserResponse uResponse = buildResponse(dao.findByIdAndStatusIsTrue(id).orElseThrow(
				() -> new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK)));
		return uResponse;
	}

	@Override
	public List<UserResponse> getAllUsuarioCoincidencia(String coincidencia, String pais) throws ValidationExceptions {
		Iterable<User> userList = dao.findByNameContainingIgnoreCaseAndAddressesCountryAndStatusIsTrue(coincidencia,
				pais);
		if (IterableUtils.isEmpty(userList)) {
			throw new ValidationExceptions("4030", "La lista de usuarios esta vacia", HttpStatus.OK);
		}
		return buildResponseList(userList);
	}

	@Override
	public List<UserResponse> getAllUsuario(String pais) throws ValidationExceptions {
		Iterable<User> userList = dao.findByAddressesCountryAndStatusIsTrue(pais);
		if (IterableUtils.isEmpty(userList)) {
			throw new ValidationExceptions("4030", "La lista de usuarios esta vacia", HttpStatus.OK);
		}
		return buildResponseList(userList);
	}

	@Override
	public UserResponse deleteUsuario(String id) throws ValidationExceptions {
		Optional<User> userDel = dao.findByIdAndStatusIsTrue(id);
		if (!userDel.isPresent()) {
			throw new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK);
		}
		User usuario = userDel.get();
		usuario.setStatus(false);
		return buildResponse(dao.save(usuario));
	}
	// |||||||||||||||||||||||||||||||||||||||||||||||||||||||
	// |||||||||||||||ADDRESSES METHOD||||||||||||||||||||||||
	// |||||||||||||||||||||||||||||||||||||||||||||||||||||||

	@Override
	public List<Address> getAllAddresses(String id) throws ValidationExceptions {
		Optional<User> uResponse = dao.findAddressesByIdAndStatusIsTrue(id);
		if (!uResponse.isPresent()) {
			throw new ValidationExceptions("0101", "No exise usuario con este id: "+ id, HttpStatus.OK);
		} else {
			if(!Objects.isNull(uResponse.get().getAddresses())) {
			return uResponse.get().getAddresses();
			}else {
				throw new ValidationExceptions("0101", "Este usuario no tienes direcciones registradas", HttpStatus.OK);
			}
		}
	}

	@Override
	public AddressResponse saveAddress(AddressRequest addressRequest, String id) throws ValidationExceptions {
		Address nuevaAddress = buildEntityAddress(addressRequest);
		Optional<User> user = dao.findByIdAndStatusIsTrue(id);
		int countAddress = 0;
		if (user.isPresent() && !Objects.isNull(user.get().getAddresses())) {
			countAddress = user.get().getAddresses().size();
		}
		if (user.isPresent()) {
			Optional<User> addressExist = dao
					.findAddressesByAddressesIdAndAddressesStatusIsTrue(addressRequest.getId());

			if (!addressExist.isPresent()) {
				nuevaAddress.setStatus(true);
				if (countAddress > 0) {
					nuevaAddress.setPrimary(false);
				} else {
					nuevaAddress.setPrimary(true);
				}
				nuevaAddress.setId(new ObjectId().toString());

				dao.save(AddAddressInUser(user, nuevaAddress));
				LOGGER.info("Agregando Address: " + user.toString());

			} else {
				nuevaAddress.setStatus(true);
				nuevaAddress.setPrimary(false);

				List<Address> listaAddresses = user.get().getAddresses();

				listaAddresses.removeIf(l -> l.getId().equals(nuevaAddress.getId()));
				listaAddresses.add(nuevaAddress);
				user.get().setAddresses(listaAddresses);

				dao.save(user.get());
				LOGGER.info("Modificando Address: " + nuevaAddress.toString());
			}

			return buildResponseAddress(nuevaAddress);
		} else {
			throw new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK);
		}
	}

	@Override
	public boolean deleteAddress(String idAddress) throws ValidationExceptions {
		Optional<User> user = dao.findAddressesByAddressesIdAndAddressesStatusIsTrue(idAddress);
		boolean elimino = false;
		if (user.isPresent()) {
			elimino = user.get().getAddresses().removeIf(l -> l.getId().equals(idAddress));
		}
		if (elimino) {
			dao.save(user.get());
			LOGGER.info("Eliminando Address id: " + idAddress);
			return elimino;
		} else {
			throw new ValidationExceptions("0101", "No se pudo eliminar la direccion No existe id: " + idAddress,
					HttpStatus.OK);
		}
	}

	@Override
	public AddressResponse setToAddressPrimary(String idAddress) throws ValidationExceptions {
		Optional<User> userExist = dao.findAddressesByAddressesIdAndAddressesStatusIsTrue(idAddress);
		if(!Objects.isNull(userExist)) {
		 userExist.get().getAddresses().stream().forEach(l -> {
			if (l.getId().equalsIgnoreCase(idAddress)) {
				l.setPrimary(true);
			} else {
				l.setPrimary(false);
			}
		});
		dao.save(userExist.get());
		Address address = userExist.get().getAddresses().stream()
				.filter(s -> s.getId().equalsIgnoreCase(idAddress))
				.collect(Collectors.toList())
			    .get(0);
		LOGGER.info("Se cambio esta direccion aprincipal : " + address);		
		return buildResponseAddress(address);
		}else {
			throw new ValidationExceptions("0101", "No se pudo cambiar la direccion No existe id: " + idAddress,
					HttpStatus.OK);			
		}
	}

	private User AddAddressInUser(Optional<User> user, Address address) {
		List<Address> lasAddresses = new ArrayList<Address>();
		if (!Objects.isNull(user.get().getAddresses())) {
			lasAddresses = user.get().getAddresses();
		}

		lasAddresses.add(address);
		user.get().setAddresses(lasAddresses);
		return user.get();
	}

	// UTILL Build DTO
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

	public Address buildEntityAddress(AddressRequest addressRequest) {
		try {
			return mapper.map(addressRequest, Address.class);
		} catch (RuntimeException e) {

			LOGGER.info("BulidEntityAddress: " + e.getMessage());
		}
		return null;

	}

	public AddressResponse buildResponseAddress(Address address) {
		try {
			return mapper.map(address, AddressResponse.class);

		} catch (RuntimeException e) {

			LOGGER.info("BulidResponseAddress" + e.getCause());
		}
		return null;
	}
}