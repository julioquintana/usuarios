package com.qs.enviamelo.usuario.dto;

import com.qs.enviamelo.usuario.dao.Usuario;

public class UsuarioResponse {
	private Long id;
	private String rut;
	private String nombre;
	private String correo;
	private String telefono;
	private String pais;
	private String direccion;
	private boolean estado;

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
	public UsuarioResponse(Usuario user) {
		super();
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