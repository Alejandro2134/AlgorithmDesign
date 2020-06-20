package minimumTasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Logic {
	
	/**
	 * El algoritmo esta escrito bajo la idea de que si multiplico la ganancia por el tiempo,
	 * puedo obtener un valor que me indica la ganancia de ejecutar la misma tarea el valor que
	 * tenga el tiempo veces, de modo que ordeno los valores de mayor a menor y voy seleccionando
	 * los que satisfagan la condición de tiempo limite.
	 */
	
	public static void greedyAlgorithm (int[] tareas, int[] tiempo, int[] ganancia, int tiempoLim) {
		
		int timeCounter = 0;
		int gananciaCounter = 0;
		
		System.out.println("Tareas ejecutadas: ");
		
		while(timeCounter != tiempoLim) {
			
			for(int i = 0; i < tareas.length; i++) {
				
				if((tiempo[i] + timeCounter) <= tiempoLim) {
					timeCounter += tiempo[i];
					gananciaCounter += ganancia[i];
					System.out.println("Tarea " + tareas[i] + " ejecutada completamente");
				}			
			}
			
			if(timeCounter != tiempoLim)
				break;
		}
		
		System.out.println("Ganancia total: " + gananciaCounter);
		
	}

	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println("Para este algoritmo se uso la tabla definida en el enunciado");
			System.out.println("Ingrese el tiempo limite en minutos:");
			int tiempoLim = Integer.parseInt(br.readLine());
			
			int [] tareas = {1, 2, 3, 4, 5, 6, 7, 8};
			int [] tiempo = {30, 20, 60, 50, 40, 10, 70, 20};
			int [] ganancia = {4, 8, 2, 5, 9, 6, 8, 1};
			
			int [] gananciaPorTiempo = new int [tareas.length];
			
			for(int i = 0; i < tareas.length; i++)
				gananciaPorTiempo[i] = ganancia[i] * tiempo[i];
			
			int temporal = 0; 
			int temporal1 = 0;
			int temporal2 = 0;
			int temporal3 = 0;
			
			for(int i = 0; i < tareas.length; i++)
				for(int j = 1; j < tareas.length - i; j++)
					if(gananciaPorTiempo[j - 1] < gananciaPorTiempo[j]) {
							
						temporal = gananciaPorTiempo[j-1];
						gananciaPorTiempo[j-1] = gananciaPorTiempo[j];
						gananciaPorTiempo[j] = temporal;
						
						temporal1 = tareas[j-1];
						tareas[j-1] = tareas[j];
						tareas[j] = temporal1;
						
						temporal2 = tiempo[j-1];
						tiempo[j-1] = tiempo[j];
						tiempo[j] = temporal2;
						
						temporal3 = ganancia[j-1];
						ganancia[j-1] = ganancia[j];
						ganancia[j] = temporal3;
						
					}
			
			greedyAlgorithm(tareas, tiempo, ganancia, tiempoLim);
			
		} catch (Exception e) {}
	}
}