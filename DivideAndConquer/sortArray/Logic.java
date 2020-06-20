package sortArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Logic {

	public static int [] maxNumber (int [] n) {
		
		int max [] = new int [1];
		
		for(int i = 0; i < n.length; i++)
			if(max[0] < n[i])
				max[0] = n[i];
		
		return max;
	}
	
	public static int [] divideAndConquer (int [] n) {
		
		if(n.length == 2 || n.length == 1) 
			return n;
		else {
			
			int middle = n.length / 2;
			
			int left [] = new int[middle];
			int rigth [];
			
			if(n.length % 2 == 0)
				rigth = new int [middle];
			else
				rigth = new int [middle + 1];
			
			for(int i = 0; i < middle; i++)
				left[i] = n[i];
				
			int aux = 0;
			
			for(int i = middle; i < n.length; i++) {
				rigth[aux] = n[i];
				aux++;
			}
				
			if(maxNumber(divideAndConquer(left))[0] > maxNumber(divideAndConquer(rigth))[0])
				return maxNumber(divideAndConquer(left));
			else
				return maxNumber(divideAndConquer(rigth));
		}	
	}

	public static void main(String[] args) {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println("Ingrese el tamaño del arreglo");
			int [] n = new int [Integer.parseInt(br.readLine())];
			
			for(int i = 0; i < n.length; i++)
				n[i] = (int) Math.floor(Math.random() * 100 + 1);

			for(int i = 0; i < n.length; i++)
				System.out.print(n[i] + " ");
			
			System.out.println("\n");
			
			System.out.println(divideAndConquer(n)[0]);
			
		} catch (Exception ex) {}	
	}
}
