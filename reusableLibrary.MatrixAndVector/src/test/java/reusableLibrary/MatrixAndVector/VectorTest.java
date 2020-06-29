package reusableLibrary.MatrixAndVector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Exception.MatrixMisconstructionException;
import Exception.WrongShapeException;
import matrix.Matrix;
import vector.*;

public class VectorTest {
	private Matrix matrix1;
	private Matrix vect1;
	private Matrix vect2;
	
	
	@Before
	public void start() throws MatrixMisconstructionException, WrongShapeException {
		this.matrix1 = new Matrix(new int[][]{ {1, 2, 5},
								   				{-1, 5, -7},
								   				{-8, 7, 11}} );
		
		this.vect1 = new RowVector(new int[]{1, 2, 3});
		
		this.vect2 = new ColumnVector(new int[]	{-4, 3, -7});
	}
	
	
	@Test
	public void test_add() throws WrongShapeException {
		RowVector result = new RowVector(new int[] {2, 4, 6});
		
		assertTrue(result.equals(vect1.add(vect1)));
	}
	
}
