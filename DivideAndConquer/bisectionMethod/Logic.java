package bisectionMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Logic {
	
	public static double operacion (String ecuacion [], double x ) {
		
		double res = 0;
		
		for(int i = 0; i < ecuacion.length; i++) {
			for(int j = 0; j < ecuacion[i].length(); j++) {
				if(ecuacion[i].charAt(j) == 'x') {
					
					if(!ecuacion[i].substring(0, j).equals("")) {
						
						double izq = 0;
						
						if(ecuacion[i].substring(0, j).equals("-")) 
							izq = -1;
						else
							izq = Double.parseDouble(ecuacion[i].substring(0, j));
						
						if(ecuacion[i].substring(j+1, ecuacion[i].length()).equals(""))
							res += izq * x;
						else {
							double der = Double.parseDouble(ecuacion[i].substring(j + 1, ecuacion[i].length()));
							res += izq * Math.pow(x, der);
						}	
						
					} else {
						double der = Double.parseDouble(ecuacion[i].substring(j + 1, ecuacion[i].length()));	
						res += Math.pow(x, der);
					}	
				}
			}
		}
		
		res += Integer.parseInt(ecuacion[ecuacion.length - 1]);
		return res;	
	}
	
	public static double divideAndConquer (String intervalo [], String ecuacion [], double mAnterior) {
		
		double a = Double.parseDouble(intervalo[0]);
		double b = Double.parseDouble(intervalo[1]);
		
		double m = (a + b) / 2;
		
		double resM = operacion(ecuacion , m);
		
		if((Math.abs(mAnterior) - Math.abs(resM)) < 0.000000001 && (Math.abs(mAnterior) - Math.abs(resM)) > 0)
			return m;
		else {
			
			double resA = operacion(ecuacion , a);
			double resB = operacion(ecuacion , b);
			
			double resultado = 0;
			
			if(Math.signum(resM) != Math.signum(resB)) { 
				
				String [] nIntervalo = new String [2];
				
				nIntervalo[0] = String.valueOf(m);
				nIntervalo[1] = String.valueOf(b);
				
				resultado = divideAndConquer(nIntervalo, ecuacion, resM);
			}
			
			if(Math.signum(resM) != Math.signum(resA)) {
					
				String [] nIntervalo = new String [2];
					
				nIntervalo[0] = String.valueOf(a);
				nIntervalo[1] = String.valueOf(m);
					
				resultado = divideAndConquer(nIntervalo, ecuacion, resM);	
			}
			
			return resultado;
		}
	}

	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println("Ingrese la ecuación sin la igualdad y de la forma del siguiente ejemplo: -x3-8x2+3x+2");
			
			String ecuacion = br.readLine();
				
			System.out.println("Ingrese los números del intervalo separados por coma (2,8)");
			String [] intervalo = br.readLine().split(",");
			
			int counter = 1;
			
			for(int i = 0; i < ecuacion.length(); i++)
				if(ecuacion.charAt(i) == '+' || ecuacion.charAt(i) == '-')
					if(i != 0)
						counter++;
			
			String [] sub = new String [counter];
			
			int aux = 0;
			
			for(int i = 0; i < ecuacion.length(); i++)
				if((ecuacion.charAt(i) == '+' || ecuacion.charAt(i) == '-') && i != 0) {
						
					sub[aux] = ecuacion.substring(0, i);
					
					if(ecuacion.charAt(i) == '+' )
						ecuacion = ecuacion.substring(i + 1, ecuacion.length());
					else
						ecuacion = ecuacion.substring(i, ecuacion.length());
						
					i = 0;
					
					aux++;	
				}
			
			sub[aux] = ecuacion;
		
			if (divideAndConquer(intervalo, sub, 0) == 0)
				System.out.println("No hay raices en el intervalo introducido");
			else 
				System.out.println("La raiz es aproximadamente: " + divideAndConquer(intervalo, sub, 0));
			
		} catch (Exception ex) {}
	}
}
