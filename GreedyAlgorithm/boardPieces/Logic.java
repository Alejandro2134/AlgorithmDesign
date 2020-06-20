package boardPieces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Logic {
	
	/**
	 * El algoritmo está escrito sobre la idea de que todas las piezas se pueden partir en cuadriculas de 1x1,
	 * de modo que no se tiene en cuenta las dimensiones de cada una de las piezas, de esta forma, logro simular 
	 * que el tablero se está llenando de forma horizontal para no depender de el tamaño de las piezas y conseguir
	 * el mayor beneficio. 
	 * 
	 * Las piezas se organizan dependiendo de la que tenga el mayor beneficio individual por cuadricula.
	 */
	
	public static void greedyAlgorithm (double [] beneficioDiv, String [] dimTablero, String [][] dimPiezas) {
		
		int m1 = Integer.parseInt(dimTablero[0]), m2 = Integer.parseInt(dimTablero[1]);
		
		int counter = 0, aux = 0;
		double beneficioTotal = 0;
		
		int totalCuadriculas = Integer.parseInt(dimPiezas[aux][0]) * Integer.parseInt(dimPiezas[aux][1]);
		
		int dim1 = Integer.parseInt(dimPiezas[aux][0]);
		int dim2 = Integer.parseInt(dimPiezas[aux][1]);
		
		System.out.println("Piezas usadas: ");
		
		while(counter != (m1 * m2)) {
			
			totalCuadriculas--; counter++;
			
			double beneficio = beneficioDiv[aux];
			
			System.out.println("Cudricula de 1x1 con beneficio " + beneficio + 
								" tomada de la pieza con dimensiones " 
								+ dim1 + "x" + dim2);
			
			if(totalCuadriculas == 0) {
				
				beneficioTotal += (beneficio * (dim1 * dim2));
				
				if(counter != (m1 * m2)) {
					aux++;
					dim1 = Integer.parseInt(dimPiezas[aux][0]);
					dim2 = Integer.parseInt(dimPiezas[aux][1]);
					totalCuadriculas = dim1 * dim2;
				}		
			}
			
			if(counter == (m1 * m2) && totalCuadriculas != 0) 
				beneficioTotal += (beneficio * ((dim1 * dim2) - totalCuadriculas));
		}
		
		System.out.println("Beneficio total: " + beneficioTotal);
	}
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println("Ingrese las dimensiones del tablero con el siguiente formato (M1xM2)");
			String [] dimTablero = br.readLine().split("x"); 
			
			System.out.println("Ingrese el número de piezas");
			int nPiezas = Integer.parseInt(br.readLine());
		
			String [][] dimPiezas = new String[nPiezas][2]; 
			
			double [] beneficioDiv = new double [nPiezas];
			
			for(int i = 0; i < nPiezas; i++) {
				
				System.out.println("Ingrese las dimensiones de la pieza " + (i + 1) + " con el siguiente formato (M1xM2)");
				dimPiezas[i] = br.readLine().split("x");
				
				double m1 = Double.parseDouble(dimPiezas[i][0]);
				double m2 = Double.parseDouble(dimPiezas[i][1]);
				
				System.out.println("Ingrese el beneficio de la pieza");
				beneficioDiv[i] = Double.parseDouble(br.readLine()) / (m1 * m2);
				
			}
			
			int totalCuadriculas = 0;
			
			for(int i = 0; i < dimPiezas.length; i++) 
				totalCuadriculas += Integer.parseInt(dimPiezas[i][0]) *  Integer.parseInt(dimPiezas[i][1]);
			
			if(totalCuadriculas >= Integer.parseInt(dimTablero[0]) * Integer.parseInt(dimTablero[1])) {
					
				double temporal = 0;
				String [] temporal2;
				
				//Bubble Sort, ordena de mayor a menor teniendo en cuenta el beneficio individual de las cuadriculas de una pieza.
				for(int i = 0; i < nPiezas; i++)
					for(int j = 1; j < nPiezas - i; j++)
						if(beneficioDiv[j - 1] < beneficioDiv[j]) {
							
							temporal = beneficioDiv[j - 1];
							beneficioDiv[j - 1] = beneficioDiv[j];
							beneficioDiv[j] = temporal;
							
							temporal2 = dimPiezas[j - 1];
							dimPiezas[j - 1] = dimPiezas[j];
							dimPiezas[j] = temporal2;	
						}
					
				greedyAlgorithm(beneficioDiv, dimTablero, dimPiezas);
				
			} else 
				System.out.println("La cantidad de cuadriculas en el tablero ingresado no satisface la cantidad de las piezas declaradas");
					
		} catch (Exception e) {}
	}
}
