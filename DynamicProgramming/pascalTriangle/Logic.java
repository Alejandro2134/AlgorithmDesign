package pascalTriangle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Logic {
	
	public static BigInteger[][] pascalTriangle(int n, int k) {

		BigInteger m[][] = new BigInteger[n][k];

		for (int i = 0; i < n; i++)
			m[i][0] = BigInteger.valueOf(1);

		for (int i = 1; i < n; i++)
			m[i][1] = BigInteger.valueOf(i);

		for (int i = 2; i < k; i++)
			m[i][i] = BigInteger.valueOf(1);

		for (int i = 3; i < n; i++)
			for (int j = 2; j < n - 1; j++)
				if (j <= k) {

					if (m[i - 1][j] == null)
						m[i - 1][j] = BigInteger.valueOf(0);

					if (m[i - 1][j - 1] == null)
						m[i - 1][j - 1] = BigInteger.valueOf(0);

					m[i][j] = m[i - 1][j - 1].add(m[i - 1][j]);
				}

		return m;
	}

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			System.out.println("Ingrese el exponente a calcular");
			int n = Integer.parseInt(br.readLine());

			BigInteger[] res;

			res = pascalTriangle(n + 1, n + 1)[n];

			for (int i = 0; i < res.length; i++)
				System.out.println("Coeficiente número " + (i + 1) + ": " + res[i]);

		} catch (Exception e) {}
		
	}
}
