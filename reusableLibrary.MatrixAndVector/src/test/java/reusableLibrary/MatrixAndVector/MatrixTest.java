package reusableLibrary.MatrixAndVector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Exception.MatrixMisconstructionException;
import Exception.WrongShapeException;
import matrix.Matrix;

public class MatrixTest {
	private Matrix matrix1;
	private Matrix matrix2;
	private Matrix matrix3;
	
	
	@Before
	public void start() throws MatrixMisconstructionException {
		this.matrix1 = new Matrix(new int[][]{ {1, 2, 5},
								   				{-1, 5, -7},
								   				{-8, 7, 11}} );
		
		this.matrix2 = new Matrix(new int[][] {{1, 2, 3},
											  {2, -5, 7}});
		
		this.matrix3 = new Matrix(new int[][]{ 	{-4, 3, -7},
												{7, 8, 3},
												{5, 0, 11}} );
	}
	
	
	
	
	
	@Test
	public void matrix23_should_return_23shape() {
		int[] result = new int[] {2, 3};
		
		assertArrayEquals(result, matrix2.getShape());
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
	 
	@Test
	public void test_equality_should_return_true() throws MatrixMisconstructionException, WrongShapeException {
		Matrix test = new Matrix(new int[][]{ {1, 2, 5},
											{-1, 5, -7},
											{-8, 7, 11}} );
		assertTrue(matrix1.equals(test));
	}
	
	@Test
	public void test_equality_should_return_false() throws MatrixMisconstructionException, WrongShapeException {
		Matrix test = new Matrix(new int[][]{ {-1, 2, 5},
											{-1, 5, -7},
											{-8, 7, 11}} );
		assertFalse(matrix1.equals(test));
	}
	
	@Test(expected = WrongShapeException.class)
	public void test_equality_should_trows_exception() throws WrongShapeException {
		matrix1.equals(matrix2);
	}
	
	@Test
	public void modify_value() throws WrongShapeException, MatrixMisconstructionException {
		Matrix test = new Matrix(new int[][] {{1, -5, 3},
			  					   				{2, -5, 7}});
		
		test.setValueAt(0, 1, 2);
		
		assertTrue(matrix2.equals(test));
	}
	
	@Test
	public void test_identity() throws MatrixMisconstructionException, WrongShapeException {
		Matrix identity3 = new Matrix(new int[][] {{1, 0, 0},
													{0, 1, 0},
													{0, 0, 1}});
		
		assertTrue(identity3.equals(Matrix.identity(3)));
		
	}
	
	@Test
	public void test_add() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{{-3, 5, -2},
												{6, 13, -4},
												{-3, 7, 22}});
		
		assertTrue(result.equals(matrix1.add(matrix3)));
		
	}
	
	@Test(expected = WrongShapeException.class)
	public void test_add_should_trows_exception() throws WrongShapeException {
		matrix1.add(matrix2);
	}
	
	
	@Test
	public void test_subtraction() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{{5, -1, 12},
												{-8, -3, -10},
												{-13, 7, 0}});
		
		assertTrue(result.equals(matrix1.subtract(matrix3)));
		
	}
	
	@Test(expected = WrongShapeException.class)
	public void test_subtraction_should_trows_exception() throws WrongShapeException {
		matrix1.subtract(matrix2);
	}
	
}