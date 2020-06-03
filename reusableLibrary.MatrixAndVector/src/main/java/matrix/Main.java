package matrix;

import Exception.MatrixMisconstructionException;
import Exception.WrongShapeException;

public class Main {

	public static void main(String[] args) throws MatrixMisconstructionException, WrongShapeException {
		Matrix matrix1 = new Matrix(new int[][]{ {1, 2, 5},
				{-1, 5, -7},
				{-8, 7, 11}} );
		
		Matrix matrix3 = new Matrix(new int[][]{ 	{-4, 3, -7},
			{7, 8, 3},
			{5, 0, 11}} );
		
		System.out.println(matrix1.pow(3).toString());

	}

}
