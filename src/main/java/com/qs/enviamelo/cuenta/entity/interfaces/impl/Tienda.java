package com.qs.enviamelo.cuenta.entity.interfaces.impl;

public class Tienda extends Usuario {

	private String ubicacionGps;
	private String horario;

	public String getUbicacionGps() {
		return ubicacionGps;
	}

	public void setUbicacionGps(String ubicacionGps) {
		this.ubicacionGps = ubicacionGps;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "Usuario Tienda [id=" + super.getId() + ", rut=" + super.getRut() + ", nombre=" + super.getNombre()
				+ ", correo=" + super.getCorreo() + ", telefono=" + super.getTelefono() + ", clave=****, pais="
				+ super.getPais() + ", direccion=" + super.getDireccion() + ", tipo=" + super.getTipo()
				+ " ubicacionGps=" + ubicacionGps + ", horario=" + horario + "]";
	}

}
