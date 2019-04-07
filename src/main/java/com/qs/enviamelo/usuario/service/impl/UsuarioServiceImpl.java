package com.qs.enviamelo.usuario.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.qs.enviamelo.usuario.dao.Usuario;
import com.qs.enviamelo.usuario.dao.repository.UsuarioRepository;
import com.qs.enviamelo.usuario.dto.UsuarioRequest;
import com.qs.enviamelo.usuario.dto.UsuarioResponse;
import com.qs.enviamelo.usuario.dto.util.BuildMapperUtil;
import com.qs.enviamelo.usuario.exception.ValidationExceptions;
import com.qs.enviamelo.usuario.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	UsuarioRepository dao;

	BuildMapperUtil mapper = new BuildMapperUtil();

	@Override
	public UsuarioResponse saveUsuario(UsuarioRequest usuarioRequest) {
		Usuario usuario = mapper.buildEntity(usuarioRequest);
		System.out.println("OBJ MAPEADO " + usuario.toString());
		return mapper.buildResponse(dao.save(usuario));
	}

	@Override
	public UsuarioResponse getUsuario(Long id) throws ValidationExceptions {
		return mapper.buildResponse(dao.findById(id).orElseThrow(() -> new ValidationExceptions("0101",
				"No existe usuario con este id: " + id , HttpStatus.OK)));
	}

	@Override
	public List<UsuarioResponse> getAllUsuario(String coincidencia, String pais) throws ValidationExceptions {
		Iterable<Usuario> userList = dao.findByNombreContainingIgnoreCaseAndPaisAndEstadoIsTrue(coincidencia, pais);
		if (IterableUtils.isEmpty(userList)) {
			throw new ValidationExceptions("4030", "La lista de usuarios esta vacia", HttpStatus.OK);
		}
		return mapper.buildResponseList(userList);
	}

	@Override
	public UsuarioResponse deleteUsuario(Long id) throws ValidationExceptions {
		Optional<Usuario> userDel = dao.findByIdAndEstadoIsTrue(id);
		if (!userDel.isPresent()) {
			throw new ValidationExceptions("0101", "No existe usuario con este id: " + id, HttpStatus.OK);
		}
		Usuario usuario = userDel.get();
		usuario.setEstado(false);
		return mapper.buildResponse(dao.save(usuario));
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
		return mapper.buildResponse(dao.save(usuario));
	}
}