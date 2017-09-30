package Entities;

import java.util.ArrayList;

import Controller.Indicador;

public class Usuario {

	public String nombre;
	public String contrase�a;
	public ArrayList<Indicador> indicadores;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public ArrayList<Indicador> getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(ArrayList<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
}
