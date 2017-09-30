package Entities;

import java.util.ArrayList;

import Controller.Indicador;

public class Usuario {

	public String nombre;
	public String contraseña;
	public ArrayList<Indicador> indicadores;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public ArrayList<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(ArrayList<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
}
