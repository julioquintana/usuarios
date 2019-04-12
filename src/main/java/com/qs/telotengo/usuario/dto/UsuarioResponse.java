package com.qs.telotengo.usuario.dto;

import com.qs.telotengo.usuario.dao.Usuario;

public class UsuarioResponse {

	Long id;
	String rut;
	String nombre;
	String correo;
	String telefono;
	String pais;
	String direccion;
	boolean estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public UsuarioResponse() {
	}
	public UsuarioResponse(Usuario user) {
		this.id = user.getId();
		this.rut = user.getRut();
		this.nombre = user.getNombre();
		this.correo = user.getCorreo();
		this.telefono = user.getTelefono();
		this.pais = user.getPais();
		this.direccion = user.getDireccion();
		this.estado = user.isEstado();
	}

}
