package reusableLibrary.MatrixAndVector;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import matrix.Matrix;

public class MatrixTest {
	private Matrix matrix1;
	
	
	@Before
	public void start() {
		this.matrix1 = new Matrix(new int[][]{ {1, 2, 5},
								   				{-1, 5, -7},
								   				{-8, 7, 11}} );
	}
	
	
	@Test
	public void matrix23_should_return_23shape() {
		Matrix test = new Matrix(new int[][] {{1, 2, 3},
											  {2, -5, 7}});
		
		int[] result = new int[] {2, 3};
		
		assertArrayEquals(result, test.getTableShape());
	}
	
}