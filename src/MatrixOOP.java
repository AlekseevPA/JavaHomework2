
public class MatrixOOP {
	
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

	public static void main(String[] args) {
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

	public static class Matrix {
		public static Matrix IDENTITY = Matrix.identity(); // единичная матрица

		protected int n, m; // размерность
		protected float[][] matrix; // сама матрица

		public Matrix() { // конструктор по умолчанию
			n = m = 3; // задаем размер матрицы 3 х 3
			matrix = new float[n][m];
		}

		public Matrix(int n, int m) { // вспомогательный конструктор
			this.n = n; // задаем размер матрицы
			this.m = m;
			this.matrix = new float[n][m];
		}

		public Matrix add(Matrix m) {
			if (this.m != m.m && this.n != m.n) { // если размер матриц не
													// совпадает
				System.out.println("Матрицы должны быть одинакового размера");
				return null;
			}

			Matrix result = new Matrix(m.n, m.m);

			for (int i = 0; i < m.n; i++) {
				for (int j = 0; j < m.m; j++) {
					result.matrix[i][j] = matrix[i][j] + m.matrix[i][j]; // суммируем
				}
			}

			return result;
		}

		public Matrix substract(Matrix m) {
			if (this.m != m.m && this.n != m.n) { // если размер матриц не
													// совпадает
				System.out.println("Матрицы должны быть одинакового размера");
				return null;
			}

			Matrix result = new Matrix(m.n, m.m);

			for (int i = 0; i < m.n; i++) {
				for (int j = 0; j < m.m; j++) {
					result.matrix[i][j] = matrix[i][j] - m.matrix[i][j]; // вычитаем
				}
			}

			return result;
		}

		public Matrix mult(int value) { // умножение матрицы на число
			Matrix result = new Matrix(n, m);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					result.matrix[i][j] = matrix[i][j] * value;
				}
			}

			return result;
		}

		public float det() {
			if (n != m) {
				System.out.println("Матрицы должны быть одинакового размера");
				return 0;
			}

			return det(matrix, n);
		}

		public static float det(float[][] mt, int n) { // вычисляем детерминант

			float det = 0;
			if (n == 1) {
				det = mt[0][0];
			} else if (n == 2) {
				det = mt[0][0] * mt[1][1] - mt[1][0] * mt[0][1];
			} else {
				for (int j1 = 0; j1 < n; j1++) {
					float[][] m = new float[n - 1][];
					for (int k = 0; k < n - 1; k++) {
						m[k] = new float[n - 1];
					}
					for (int i = 1; i < n; i++) {
						int j2 = 0;
						for (int j = 0; j < n; j++) {
							if (j == j1)
								continue;
							m[i - 1][j2] = mt[i][j];
							j2++;
						}
					}
					det += Math.pow(-1.0, j1) * mt[0][j1] * det(m, n - 1);
				}
			}
			return det;
		}

		public Matrix inversion() { // обратная матрица
			if (n != m) {
				System.out.println("Инвертировать можно только квадратные матрицы");
				return null;
			}

			return inversion(matrix, n);

		}

		public static Matrix inversion(Matrix m) { // статический метод для
													// получения обратной
													// матрицы
			return m.inversion();
		}

		public static Matrix inversion(float[][] A, int N) { // вычисляем
																// обратную
																// матрицу
			double temp;

			float[][] E = new float[N][N];

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					E[i][j] = 0f;

					if (i == j)
						E[i][j] = 1f;
				}

			for (int k = 0; k < N; k++) {
				temp = A[k][k];

				for (int j = 0; j < N; j++) {
					A[k][j] /= temp;
					E[k][j] /= temp;
				}

				for (int i = k + 1; i < N; i++) {
					temp = A[i][k];

					for (int j = 0; j < N; j++) {
						A[i][j] -= A[k][j] * temp;
						E[i][j] -= E[k][j] * temp;
					}
				}
			}

			for (int k = N - 1; k > 0; k--) {
				for (int i = k - 1; i >= 0; i--) {
					temp = A[i][k];

					for (int j = 0; j < N; j++) {
						A[i][j] -= A[k][j] * temp;
						E[i][j] -= E[k][j] * temp;
					}
				}
			}

			Matrix result = new Matrix(E.length, E.length);
			result.setMatrix(E);

			return result;
		}

		public static Matrix identity() { // возвращает еденичную матрицу
			Matrix m = new Matrix();

			for (int i = 0; i < m.n; i++) {
				for (int j = 0; j < m.m; j++) {
					m.matrix[i][j] = 0;
					if (i == j) {
						m.matrix[i][j] = 1;
					}
				}
			}

			return m;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Matrix %dx%d \n", n, m));
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sb.append(String.format("%7.2f", matrix[i][j]));
				}
				sb.append("\n");
			}
			return sb.toString();
		}

		public int getN() {
			return n;
		}

		public int getM() {
			return m;
		}

		public float[][] getMatrix() {
			return matrix;
		}

		public void setN(int n) {
			this.n = n;
		}

		public void setM(int m) {
			this.m = m;
		}

		public void setMatrix(float[][] matrix) {
			this.matrix = matrix;
		}

	}
}