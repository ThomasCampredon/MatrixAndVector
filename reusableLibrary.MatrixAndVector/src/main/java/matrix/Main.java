package matrix;

import Exception.MatrixMisconstructionException;
import Exception.WrongShapeException;
import vector.*;

public class Main {

	public static void main(String[] args) throws MatrixMisconstructionException, WrongShapeException {
		Matrix matrix1 = new Matrix(new int[][]{ {1, 2, 5},
				{-1, 5, -7},
				{-8, 7, 11}} );
		
		Matrix matrix3 = new Matrix(new int[][]{ 	{-4, 3, -7},
			{7, 8, 3},
			{5, 0, 11}} );
		
		/*System.out.println(matrix1.pow(3).toString());
		
		Matrix vect1 = new RowVector(5);
		Matrix vect2 = new ColumnVector(5);
		vect1.randomizeValue();
		vect2.randomizeValue();
		
		System.out.println(vect2.T().toString());*/
		
		Matrix result = new Matrix(new int[][]{ 	{3, 5, -2},
			{6, 13, -4},
			{-3, 7, 22}} );
		
		Matrix[] matrixes = {matrix1, matrix3};
		System.out.println(Matrix.SUM(matrixes));

	} 

}
