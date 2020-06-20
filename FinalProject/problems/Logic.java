package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Logic {
	
	public static int[][] torneo (int tabla [][], int n) {
		
		if(n == 2) {
			tabla[0][0] = 2;
			tabla[1][0] = 1;
			return tabla;
		}
		
		if((n % 2) != 0) {
		
			 tabla = torneo(tabla, n + 1);
			 
			 for(int jug = 0; jug < n; jug++)
				 for(int dia = 0; dia < n; dia++)
					 if(tabla[jug][dia] == n + 1)
						 tabla[jug][dia] = 0;
			 
			 return tabla;
			
		} else {
			
			int m = n / 2;
			
			tabla = torneo(tabla, m);
			
			if((m % 2) == 0) { //Si m es par
				
				//Cuadrante inferior izquierdo
				for(int jug = m; jug < n; jug++)
					for(int dia = 0; dia < m-1; dia++)
						tabla[jug][dia] = tabla[jug-m][dia] + m;
				
				//Cuadrante superior derecho
				for(int jug = 0; jug < m; jug++)
					for(int dia = m-1; dia < n-1; dia++)
						if(((jug + 1) + (dia + 1)) <= n)
							tabla[jug][dia] = (jug + 1) + (dia + 1);
						else
							tabla[jug][dia] = (jug + 1) + (dia + 1) - m;
				
				//Cuadrante inferior derecho
				for(int jug = m; jug < n; jug++)
					for(int dia = m-1; dia < n-1; dia++)
						if(jug > dia)
							tabla[jug][dia] = (jug + 1) - (dia + 1);
						else
							tabla[jug][dia] = ((jug + 1) + m) - (dia + 1);
				
				return tabla;	
				
			} else {
				
				//Cuadrante inferior izquierdo
				for(int jug = m; jug < n; jug++) 
					for(int dia = 0; dia < m; dia++)
						if(tabla[jug-m][dia] == 0)
							tabla[jug][dia] = 0;
						else
							tabla[jug][dia] = tabla[jug-m][dia] + m;
				
				//Ceros cuadrante izquierdo
				for(int jug = 0; jug < m; jug++) 
					for(int dia = 0; dia < m; dia++)
						if(tabla[jug][dia] == 0) {
							tabla[jug][dia] = (jug + 1) + m;
							tabla[jug + m][dia] = jug + 1;
						}
				
				//Cuadrante superior derecho
				for(int jug = 0; jug < m; jug++) 
					for(int dia = m; dia < n-1; dia++)
						if(jug + dia < n-1)
							tabla[jug][dia] = (jug + 1) + (dia + 1);
						else
							tabla[jug][dia] = ((jug + 1) + (dia + 1)) - m;
				
				//Cuadrante inferior derecho
				for(int jug = m; jug < n; jug++)
					for(int dia = m; dia < n-1; dia++)
						if(jug > dia)
							tabla[jug][dia] = (jug + 1) - (dia + 1);
						else
							tabla[jug][dia] = ((jug + 1) + m) - (dia + 1);
				
				return tabla;
			}
		}
	}
		
	public static void divideAndConquer () {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println("Ingrese el número de jugadores");
			int n = Integer.parseInt(br.readLine());
			
			int tabla [][];
			
			if(n % 2 == 0)
				tabla = new int [n][n];
			else
				tabla = new int [n+1][n+1];
			
			tabla = torneo(tabla, n);
			
			System.out.println("");
			System.out.println("Tabla de enfrentamientos: \n");
			
			if(n % 2 != 0) 
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(j == 0)
							System.out.print("J" + (i + 1) + " | " + tabla[i][j] + " ");
						else
							System.out.print(tabla[i][j] + " ");
					}
					
					System.out.print("\n");
				}
			else
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n - 1; j++) {
						if(j == 0)
							System.out.print("J" + (i + 1) + " | " + tabla[i][j] + " ");
						else
							System.out.print(tabla[i][j] + " ");
					}
					
					System.out.print("\n");
				}
			
			System.out.println(" ");
				
		} catch (Exception ex) {}
	}
	
	public static int [][] embarcaderos (int n, int [] costos) {
		
		int c [][] = new int [n][n];
		int aux = 0;
		
		for(int i = 0; i < n; i++)
			for(int j = i + 1; j < n; j++) {
				
				if(i == 0)
					c[i][j] = costos[aux];
				else 
					c[i][j] = Math.min(c[i-1][i] + costos[aux], c[i-1][j]);
					
				aux++;
			}
				
		return c;
	}
	
	public static void dynamicProgramming () {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println("");
			System.out.println("Ingrese la salida y la llegada separados por coma (1,2)");
			String [] puntos = br.readLine().split(",");
			int salida = Integer.parseInt(puntos[0]);
			int llegada = Integer.parseInt(puntos[1]);
			int n = llegada - (salida - 1);
			
			int numeroCostos = 0;
			
			for(int i = 1; i <= llegada - salida; i++)
				numeroCostos += i;
			
			int [] costos = new int [numeroCostos];
			
			int [][] matriz;
			
			System.out.println("Ingrese los " + numeroCostos + " costos separados por coma: (1,2,3,4,5,..,n)");
			String [] costos2 = br.readLine().split(",");
			
			for(int i = 0; i < numeroCostos; i++)
				costos[i] = Integer.parseInt(costos2[i]);
			
			matriz = embarcaderos(n, costos);
			
			System.out.println("");
			System.out.println("Tabla de memorización: ");
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(matriz[i][j] >= 10)
						System.out.print(matriz[i][j] + "    ");
					else
						System.out.print(matriz[i][j] + "     ");
				}
					
				System.out.print("\n");
			}
			
			System.out.println("");
			
			int aux = matriz[0][n-1];
			
			for(int i = 1; i < n; i++)
				for(int j = n-1; j < n; j++) {
					
					int num = matriz[i][j];
					
					if(num != aux) {
						aux = matriz[i][j];
						System.out.println("Viaje al embarcadero " + (i + 1));
					}
				}
			
			System.out.println("");
			System.out.println("Coste minimo: " + matriz[n-2][n-1]);
			System.out.println("");
					
		} catch (Exception ex) {}
	}
	
	public static ArrayList <Integer> tareas (int [][] tablaTareas, int acum, String anterior)  {
		
		if(tablaTareas.length == 1) {
			System.out.print(anterior + tablaTareas[0][0] + " coste total de las tareas: " + (acum + tablaTareas[0][0]) + "\n");
			ArrayList <Integer> lista = new ArrayList <Integer> (); 
			lista.add(tablaTareas[0][0] + acum);
			return lista;
			
		} else {
			
			ArrayList <Integer> lista = new ArrayList <Integer> (); 
			
			int aux = 0;
			
			for(int i = 0; i < 1; i++)
				for(int j = 0; j < tablaTareas.length; j++) {
					
					anterior += tablaTareas[i][j] + " ";
					
					int [][] tabla = crearTabla(tablaTareas, i, j);
					aux = tablaTareas[i][j] + acum;
					lista.addAll(tareas(tabla, aux, anterior)); 
					
					anterior = anterior.substring(0, anterior.length() - 2);
				}
			
			return lista;
		}	
	}
	
	public static int[][] crearTabla (int [][] tablaTareas, int p, int t) {
		
		int [][] tabla = new int [tablaTareas.length - 1][tablaTareas.length - 1];
		
		int iAux = 0;
		
		for(int i = 0; i < tablaTareas.length; i++) {
			
			int jAux = 0;
			
			for(int j = 0; j < tablaTareas.length; j++)
				if(i != p && j != t) {
					tabla[iAux][jAux] = tablaTareas[i][j];
					jAux++;
				}
			
			if(i != p)
				iAux++;
		}
				
		return tabla;
	}
	
	public static void backtracking () {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.print("\n");
			System.out.println("Ingrese el número de personas o de tareas");
			
			int n = Integer.parseInt(br.readLine());
			
			int [][] tablaTareas = new int [n][n];
			
			System.out.println("Ingrese las " + n * n + " costos separados por coma (1,2,3,4...,n)");
			String [] tareas = br.readLine().split(",");
			int aux = 0;
			
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++) {
					tablaTareas[i][j] = Integer.parseInt(tareas[aux]);
					aux++;
				}
			
			ArrayList <Integer> lista = new ArrayList <Integer> (); 
			
			System.out.print("\n");
			lista = tareas(tablaTareas, 0, "");
			System.out.print("\n");
			lista.sort(null);
			System.out.println("El costo mas bajo es : " + lista.get(0));
			System.out.print("\n");						
			
		} catch (Exception ex) {}
	}

	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			int res = 0;
			
			do {
				
				System.out.println("Escoja el algoritmo a evaluar: \n"
						+ "1. Dividir y vencer \n"
						+ "2. Programación dinámica \n"
						+ "3. Backtracking o Vuelta atrás \n"
						+ "0. Salir");
	
				res = Integer.parseInt(br.readLine());
				
				switch(res) {
				
					case 1: {
						divideAndConquer();
						break;
					}
					
					case 2: {
						dynamicProgramming();
						break;
					}
					
					case 3: {
						backtracking();
						break;
					}	
				}
				
			} while (res != 0);
				
		} catch (Exception ex) {}
	}
}