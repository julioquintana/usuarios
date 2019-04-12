package com.qs.telotengo.usuario.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
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

	public Usuario() {
		super();
	}
	public Usuario(Long id, String rut, String nombre, String correo, String telefono, String clave, String pais,
			String direccion, boolean estado) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.clave = clave;
		this.pais = pais;
		this.direccion = direccion;
		this.estado = estado;
	}

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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", correo=" + correo + ", telefono="
				+ telefono + ", clave=" + clave + ", pais=" + pais + ", direccion=" + direccion + ", estado=" + estado
				+ "]";
	}

}
