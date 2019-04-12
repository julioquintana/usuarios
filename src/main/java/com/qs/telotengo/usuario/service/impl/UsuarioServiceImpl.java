package com.qs.telotengo.usuario.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.qs.telotengo.usuario.dao.Usuario;
import com.qs.telotengo.usuario.dao.repository.UsuarioRepository;
import com.qs.telotengo.usuario.dto.UsuarioRequest;
import com.qs.telotengo.usuario.dto.UsuarioResponse;
import com.qs.telotengo.usuario.exception.ValidationExceptions;
import com.qs.telotengo.usuario.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository dao;

	//BuildMapperUtil mapper = new BuildMapperUtil();
	
	@Autowired
	 private Mapper mapper;

	@Override
	public UsuarioResponse saveUsuario(UsuarioRequest usuarioRequest) {
		Usuario usuario = buildEntity(usuarioRequest);
		System.out.println("OBJ MAPEADO " + usuario.toString());
		return buildResponse(dao.save(usuario));
	}

	@Override
	public UsuarioResponse getUsuario(Long id) throws ValidationExceptions {
		Optional<Usuario> user = dao.findByIdAndEstadoIsTrue(id);
		System.out.println(user.toString());

		UsuarioResponse uResponse =	buildResponse(dao.findByIdAndEstadoIsTrue(id).orElseThrow(() -> new ValidationExceptions("0101",
				"No existe usuario con este id: " + id , HttpStatus.OK)));
		return uResponse;		
	}

	@Override
	public List<UsuarioResponse> getAllUsuario(String coincidencia, String pais) throws ValidationExceptions {
		Iterable<Usuario> userList = dao.findByNombreContainingIgnoreCaseAndPaisAndEstadoIsTrue(coincidencia,pais);
		if (IterableUtils.isEmpty(userList)) {
			throw new ValidationExceptions("4030", "La lista de usuarios esta vacia", HttpStatus.OK);
		}
		return buildResponseList(userList);
	}

	@Override
	public UsuarioResponse deleteUsuario(Long id) throws ValidationExceptions {
		Optional<Usuario> userDel = dao.findByIdAndEstadoIsTrue(id);
		if (!userDel.isPresent()) {
			throw new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK);
		}
		Usuario usuario = userDel.get();
		usuario.setEstado(false);
		return buildResponse(dao.save(usuario));
	}

	@Override
	public UsuarioResponse updateUsuario(UsuarioRequest usuarioRequest) throws ValidationExceptions {
		Optional<Usuario> userUpd = dao.findByIdAndEstadoIsTrue(usuarioRequest.getId());

		if (!userUpd.isPresent()) {
			throw new ValidationExceptions("0101",
					"No existe usuario con este id: " + usuarioRequest.getId(), HttpStatus.OK);
		}
		Usuario usuario = userUpd.get();
		usuario.setDireccion(usuarioRequest.getDireccion());
		usuario.setNombre(usuarioRequest.getNombre());
		usuario.setTelefono(usuarioRequest.getTelefono());
		return buildResponse(dao.save(usuario));
	}
	
	public UsuarioResponse buildResponse(Usuario usuario) {
		 try {
		return mapper.map(usuario, UsuarioResponse.class);

		 } catch (Throwable e) {
		 System.out.println("BulidResponse" + e.getCause());
		 }
		 return null;
	}

	public Usuario buildEntity(UsuarioRequest usuarioRequest) {
		 try {
		return mapper.map(usuarioRequest, Usuario.class);
		 } catch (Throwable e) {
		 System.out.println("BulidEntity: " + e.getMessage());
		 }
		 return null;

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