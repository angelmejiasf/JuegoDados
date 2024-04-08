package examenrecuperacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AppJuego {
	public ArrayList<jugador> Jugadores;
	public double dineroBanca;

	public AppJuego(ArrayList<jugador> jugadores, double dineroBanca) {
		super();
		Jugadores = jugadores;
		dineroBanca = 0;
	}

	public ArrayList<jugador> getJugadores() {
		return Jugadores;
	}

	public void setJugadores(ArrayList<jugador> jugadores) {
		Jugadores = jugadores;
	}

	public double getDineroBanca() {
		return dineroBanca;
	}

	public void setDineroBanca(double dineroBanca) {
		this.dineroBanca = dineroBanca;
	}

	public void cargarjugadores(String filename) {
		File fichero = new File(filename);
		try {
			String cadena = "";
			String[] linea;
			Scanner entrada = new Scanner(fichero);

			// En caso de que queramos saltar la primera linea
			cadena = entrada.nextLine();
			while (entrada.hasNext()) {
				cadena = entrada.nextLine();

				linea = cadena.split(";");
				Jugadores.add(new jugador(linea[0], Integer.parseInt(linea[1]), linea[2]));

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarjugadores() {
		System.out.println(Jugadores);
	}

	public void jugar(int npartida) {
		double dineroApostadoTotal = 0;
		for (jugador jugador : Jugadores) {
			System.out.print(jugador.getNombre());

			// Actualizar los lanzamientos del jugador
			jugador.lanzamiento();

			// Obtener los lanzamientos del jugador
			int[] lanzamientos = jugador.getLanzamientos();
			// System.out.println("Lanzamientos: " + Arrays.toString(lanzamientos));

			// Verificar si hay tres números iguales consecutivos
			boolean tresConsecutivos = false;
			for (int i = 0; i < lanzamientos.length - 2; i++) {
				if (lanzamientos[i] == lanzamientos[i + 1] && lanzamientos[i] == lanzamientos[i + 2]) {
					tresConsecutivos = true;
					break;
				}
			}

			double apuesta = jugador.apostardinero();
			double restante = jugador.getDineroDisponible() - apuesta;
			jugador.setDineroDisponible(restante);

			// Si hay tres números iguales consecutivos, acumular el dinero de la banca al
			// restante del jugador
			if (tresConsecutivos) {
				double dineroAcumulado = jugador.getDineroDisponible() + dineroBanca;
				jugador.setDineroDisponible(dineroAcumulado);
				dineroBanca = 0;
				System.out.println(
						"¡Tres números iguales consecutivos! Se acumula el dinero de la banca al restante del jugador.");
			}

			System.out.print("Apuesta: " + apuesta + "€ ----->");
			System.out.println("Restante: " + jugador.getDineroDisponible() + "€");
			System.out.println("");

			dineroApostadoTotal += apuesta;
		}
		System.out.println("Dinero total apostado: " + dineroApostadoTotal);
		dineroBanca += dineroApostadoTotal; // Acumula el dinero apostado al bote de la banca
		System.out.println("Dinero total en la banca: " + dineroBanca);
	}

	public static void main(String[] args) throws FileNotFoundException {
		AppJuego juego = new AppJuego(new ArrayList<jugador>(), 0);
		juego.cargarjugadores("jugadores.csv");
		juego.mostrarjugadores();

		for (int i = 0; i < 10; i++) {
			System.out.println("-----------------Partida------------------");
			juego.jugar(i);

		}

		juego.mostrarInformes();
		juego.generarInformes();

	}

	private void mostrarInformes() {

		System.out.println("*****************************************");
		System.out.println("***********Resultado final***************");
		DecimalFormat formato = new DecimalFormat("#.##");
		String dineroFormateado = "";
		for (jugador j : this.getJugadores()) {
			dineroFormateado = formato.format(j.getDineroDisponible());
			System.out.print(j.getNombre() + " de " + j.getCiudad());
			System.out.println(" tiene " + dineroFormateado + "€");

		}
		dineroFormateado = formato.format(this.getDineroBanca());
		System.out.println("La banca ha ganado: " + dineroFormateado + "€");
		System.out.println("*****************************************");
		System.out.println("*****************************************");
	}

	private void generarInformes() throws FileNotFoundException {
		DecimalFormat formato = new DecimalFormat("#.##");
		String dineroFormateado = "";
		for (jugador j : this.getJugadores()) {
			PrintWriter salida = new PrintWriter(new File("Resultado" + j.getNombre() + ".txt"));
			salida.println("Resultado del juego para " + j.getNombre() + ":");

			dineroFormateado = formato.format(j.getDineroDisponible());
			salida.println("Dinero conseguido: " + dineroFormateado + "€");
			salida.flush();
			salida.close();

		}

	}

}
