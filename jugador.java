package examenrecuperacion;

import java.util.Arrays;

public class jugador {
	private String nombre;
	private int edad;
	private String ciudad;
	private int[]lanzamientos=new int[10];
	private int[]gana;
	private static double apuesta;
	private double dineroDisponible=500;
	
	
	
	
	

	public jugador(String nombre, int edad, String ciudad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.ciudad = ciudad;
		this.dineroDisponible=500;
	}


	

	



	public double getDineroDisponible() {
		return dineroDisponible;
	}








	public void setDineroDisponible(double dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}








	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) {
		this.edad = edad;
	}



	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public int[] getLanzamientos() {
		return lanzamientos;
	}



	public void setLanzamientos(int[] lanzamientos) {
		this.lanzamientos = lanzamientos;
	}



	public int[] getGana() {
		return gana;
	}



	public void setGana(int[] gana) {
		this.gana = gana;
	}



	public double getApuesta() {
		return apostardinero();
	}



	public void setApuesta(double apuesta) {
		this.apuesta = apuesta;
	}


	

	@Override
	public String toString() {
		return nombre+" de "+edad+" vive en "+ciudad;
	}



	public void lanzamiento() {
	    for(int i = 0; i < 10; i++) {
	        int num = (int) (Math.random() * 6) + 1; 
	        lanzamientos[i] = num;
	        System.out.print("[" + num + "]");
	    }
	    System.out.println("");
	}

	
	public static double apostardinero() {
		int num=(int) (Math.random()*500);
		
		
		return num;
		
		
		
	}








	public void setGana(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	
}
