package task2;

public class Main {

	public static float[][] getValues() { // для формирования матрицы
		int n = 3;
		int m = 3;
		float[][] mx = new float[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mx[i][j] = i * n + j + 1;
			}
		}
		mx[1][1] = -1;
		return mx;
	}

	public static void main(String[] args) throws Exception{
		int n = 3;
		int m = 3;
		Matrix x = new Matrix(n, m);
		x.setMatrix(getValues());

		System.out.println("1. Сумма матриц");
		System.out.println(x.add(x));
		x.setMatrix(getValues());

		System.out.println("2. Разность матриц");
		System.out.println(x.substract(x));
		x.setMatrix(getValues());

		System.out.println("3.1 Инверсия матрицы");
		System.out.println(x.inversion());
		x.setMatrix(getValues());

		System.out.println("3.2 Инверсия матрицы");
		System.out.println(Matrix.inversion(x));
		x.setMatrix(getValues());

		System.out.println("4.1 Единичная матрица");
		System.out.println(Matrix.IDENTITY);

		System.out.println("4.2 Единичная матрица");
		System.out.println(Matrix.identity());

		System.out.println("5. Печать матрицы");
		System.out.println(x);

		System.out.println("6. Размерность матрицы");
		System.out.println("n = " + x.getN() + " m = " + x.getM());

		System.out.println("7. Произведение матрицы на число");
		System.out.println(x.mult(2));
		x.setMatrix(getValues());

		System.out.println("8. Опредилитель");
		System.out.println(x.det());

		System.out.println("9. Обратная матрица");
		System.out.println(x.inversion());
		x.setMatrix(getValues());

	}
}
