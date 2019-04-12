package com.qs.telotengo.usuario.dto;

import javax.validation.constraints.NotNull;


public class UsuarioRequest {
	Long id;
	@NotNull
	String rut;
	@NotNull
	String nombre;
	@NotNull
	String correo;
	@NotNull
	String telefono;
	String clave;
	@NotNull
	String pais;
	@NotNull
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "UsuarioRequest [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", correo=" + correo + ", telefono="
				+ telefono + ", clave=" + clave + ", pais=" + pais + ", direccion=" + direccion + ", estado=" + estado
				+ "]";
	}

}
