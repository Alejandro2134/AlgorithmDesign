package determinant;

import java.util.Scanner;

public class Logic {
	
	public static int prodCruz (double m[][]) {
		
		if(m.length == 2) //Caso base
			
			return (int) ((m[0][0] * m[1][1]) - (m[0][1] * m[1][0]));
		
		else { //Caso recursivo
			 
			int counter = 0;
			int acumulado = 0;
			
			/**
			 * Matriz que almacena la sub matriz de la matriz pasada como argumento. La sub matriz tendrá un orden
			 * menor en 1 a la matriz pasada como argumento.
			 */
			double subM [][] = new double [m.length - 1][m.length - 1]; 
			
			/**
			 * Se recorre la matriz m.length veces de modo que en cada iteración se ignoren las filas y las columnas 
			 * pertinentes para generar la sub matriz, luego se multiplica el primer elemento de cada columna con su menor 
			 * o sub matriz de forma recursiva hasta que está se vuelve de orden 2 y devuelve el valor
			 * de su determinante, calculando así el determinante de las matrices de mayor orden a 2 y guardando y sumando
			 * todos los resultados en una variable acumulado. 
			 */
			while(counter != m.length) {
				
				for(int i = 0; i < m.length; i++) 
					for(int j = 0; j < m.length; j++)
						if(i != 0 && j != counter)
							if(j > counter)
								subM[i - 1][j - 1] = m[i][j];
							else
								subM[i - 1][j] = m[i][j];
									
				if(m.length == 3)
					if(((counter + 1) % 2) == 0) 
						acumulado += -m[0][counter] * prodCruz(subM);
					else
						acumulado += m[0][counter] * prodCruz(subM); 
				else 
					if(((counter + 1) % 2) == 0) 
						acumulado += (m[0][counter] * Math.pow(-1, 1 + (counter + 1)))  * prodCruz(subM); 
					else
						acumulado += m[0][counter] * prodCruz(subM); 
					
				counter += 1;	
			}
			
			return acumulado;	
		}
	}
	
	public static int gaussJordan (double m[][]) {
		
		int counter = 0;
		double resultado = 1;
	    double firstDiagElem = 0;
		int iAux = 0;
		double min = 0;
		double[] arrAux = new double [m.length]; 
		double mul = 0;
		
		for(int j = 0; j < m.length - 1; j++) {
			
			for(int i = 0 + j; i < m.length; i++) {
				
				if(i == j) {
					min = m[i][j];
					firstDiagElem = m[i][j];
					iAux = i;			
				}
					
				if(Math.abs(m[i][j]) < Math.abs(m[iAux][j]) && m[i][j] != 0) {
					iAux = i;
					min = m[i][j];
				}
				
				if(min == 0) 
					if(Math.abs(m[i][j]) > min) {
						iAux = i;
						min = m[i][j];
					}
				
				if(min == 1)
					break;
			}
			
			/*
			 * Si se encuentra un valor menor que el de los primeros valores de la diagonal en su fila
			 * la primera fila es remplazada por la fila que tiene el menor valor
			 */
			
			if(firstDiagElem != m[iAux][j]) {
				arrAux = m[iAux];
				m[iAux] = m[j];
				m[j] = arrAux;
				
				counter += 1;
			}
			
			/**
			 * Se realiza la respectiva operación (suma de fila, resta de fila o multiplicación de fila y suma) 
			 * dependiendo de las condiciones de la matriz
			 */
			for(int i = 1 + j; i < m.length; i++) {
				
				double arrAux2 [] = new double [m.length];
					
				if(Math.abs(min) == Math.abs(m[i][j])) {
					
					if(m[i][j] < min || m[i][j] > min)
						for(int a = 0; a < m.length; a++)
							m[i][a] += m[j][a];
						
					else if(min == m[i][j]) 
						for(int a = 0; a < m.length; a++)
							m[i][a] -= m[j][a];	
				} 
				else {
					
					if((m[i][j] < min) && m[i][j] != 0) {
					
						if(m[i][j] < 0 && min < 0) {
							mul = (double) m[i][j] / min;
							mul = -mul;
						} else {
							mul = (double) m[i][j] / min;
							mul = -mul; //Comvierte a positivo
						}
						
						for(int a = 0; a < m.length; a++)
							arrAux2[a] = (double) mul * m[j][a];
							
						for(int a = 0; a < m.length; a++)
							m[i][a] += arrAux2[a];	
					}
					else if(m[i][j] != 0) {
						
						if(m[i][j] > 0 && min > 0) {
							mul = (double) m[i][j] / min;
							mul = -mul;
						} else 
							if(m[i][j] > 0 && min < 0) {
								mul = (double) m[i][j] / min;
								mul = -mul; //Comvierte a positivo
							}
						
						for(int a = 0; a < m.length; a++)
							arrAux2[a] = (double) mul * m[j][a];
							
						for(int a = 0; a < m.length; a++)
							m[i][a] += arrAux2[a];
								
					}
					
				}
				
			}	
		}
		
		/**
		 * Se toman los valores de la diagonal y se multiplican
		 */
		for(int i = 0; i < m.length; i++) 
			for(int j = 0; j < m.length; j++) 
				if(i == j)
					resultado *= m[i][j];
		
		/**
		 * Si el counter es diferente de 0 significa que se hicieron cambios de filas y el signo del resultado
		 * debe cambiar tantas veces se halla hecho el cambio.
		 */
		if(counter != 0)
			for(int i = 0; i < counter; i++)
				resultado = -resultado;
				
		return (int) Math.round(resultado);
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el tamaño de la matriz");
		int size = sc.nextInt();
		
		double m[][] = new double [size][size];
		
		for(int i = 0; i < m.length; i++)
			for(int j = 0; j < m.length; j++) {
				m[i][j] = Math.round((Math.random() * 20)) - 10;
			}
		
		System.out.println("Resultado: " + prodCruz(m));
		System.out.println("Resultado: " + gaussJordan(m));
			
		System.out.println("Ingrese g (gauss-jordan) o p (producto cruz recursivo), dependiendo del método que desea realizar");
		String res = sc.next();
		
		if(res.equals("g")) {
			long startTime = System.nanoTime();
			System.out.println("Resultado: " + gaussJordan(m));
			long endTime = System.nanoTime() - startTime;
			System.out.println("Tiempo de ejecución: " + endTime + " nanosegundos");
		}	
		else {
			long startTime = System.nanoTime();
			System.out.println("Resultado: " + prodCruz(m));
			long endTime = System.nanoTime() - startTime;
			System.out.println("Tiempo de ejecución: " + endTime + " nanosegundos");
		}
			
		sc.close();
	}
}
