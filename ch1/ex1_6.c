#include <stdio.h>

// TODO: figure out

void printMatrix(int m, int n, int mat[m][n]) {
	// m is nrows, n is ncols
	int i, j;

	for (i = 0; i < m; i++) {
		for (j = 0; j < n; j++) {
			printf("%d\t", mat[i][j]);
		}
		printf("\n");
	}
}

void rotate90(int n, int mat[][n]) {
	int i, j, temp;

	for (i = 0; i < n / 2; i++) {
		for (j = i; j < n - i - 1; j++) {
			temp = mat[i][j];
			mat[i][j] = mat[j][n - 1 - i];
			mat[j][n - 1 - i] = mat[n - 1 - i][n - 1 - j];
			mat[n - 1 - i][n - 1 - j] = mat[n - 1 - j][i];
			mat[n - 1 - j][i] = temp;
		}
	}
}

int main() {
	int x[9][9], i, j;

	for (i = 0; i < 9; i++) {
		for (j = 0; j < 9; j++) {
			x[i][j] = 9 * i + j;
		}
	}

	printMatrix(9, 9, x);
	rotate90(9, x);

	printf("\n");
	printMatrix(9, 9, x);


	return 0;
}


