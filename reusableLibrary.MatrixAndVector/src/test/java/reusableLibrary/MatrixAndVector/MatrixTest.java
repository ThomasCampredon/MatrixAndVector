package reusableLibrary.MatrixAndVector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Exception.MatrixMisconstructionException;
import matrix.Matrix;

public class MatrixTest {
	private Matrix matrix1;
	private Matrix matrix2;
	
	
	@Before
	public void start() throws MatrixMisconstructionException {
		this.matrix1 = new Matrix(new int[][]{ {1, 2, 5},
								   				{-1, 5, -7},
								   				{-8, 7, 11}} );
		this.matrix2 = new Matrix(new int[][] {{1, 2, 3},
											  {2, -5, 7}});
	}
	
	
	
	
	
	@Test
	public void matrix23_should_return_23shape() {
		int[] result = new int[] {2, 3};
		
		assertArrayEquals(result, matrix2.getTableShape());
	}
	
	
	@Test
	public void matrix23_should_return_m2_and_n3() {	
		assertEquals(2, matrix2.m());
		assertEquals(3, matrix2.n());
	}
	
	@Test(expected = MatrixMisconstructionException.class)
	public void misconstructed_matrix_should_trows_exception() throws MatrixMisconstructionException {
		Matrix badMatrix = new Matrix(new int[][] {{1, 2},
													{2, 4, 5}});
	}
	
}