package reusableLibrary.MatrixAndVector;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
	public void test_getRow() {
		BigDecimal[] result = new BigDecimal[3];
		for (int i = 1 ; i < 4 ; i++) { 
			result[i-1] = new BigDecimal(i);
		}
		
		assertArrayEquals(result, matrix2.getRow(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getRow_should_trows_exception() throws WrongShapeException {
		BigDecimal[] test = matrix1.getRow(3);
	}
	
	@Test
	public void test_getColumn() {
		BigDecimal[] result = new BigDecimal[2];
		for (int i = 1 ; i < 3 ; i++) {
			result[i-1] = new BigDecimal(i);
		}
		
		assertArrayEquals(result, matrix2.getColumn(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_getColumn_should_trows_exception() throws WrongShapeException {
		BigDecimal[] test = matrix1.getColumn(-1);
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
	
	@Test
	public void test_multiplication_with_matrix() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{{35, 19, 54},
												{4, 37, -55},
												{136, 32, 198}});
		
		assertTrue(result.equals(matrix1.multiply(matrix3)));
		
	}
	
	@Test
	public void test_multiplication_with_scalar() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{{2, 4, 10},
												{-2, 10, -14},
												{-16, 14, 22}});
		
		assertTrue(result.equals(matrix1.multiply(2)));
		
	}
	
	@Test(expected = WrongShapeException.class)
	public void test_multiplication_with_matrix_should_trows_exception() throws WrongShapeException {
		matrix1.multiply(matrix2);
	}
	
	@Test
	public void test_transpose() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][] {{1,2},
												{2, -5},
												{3, 7}});
		
		assertTrue(result.equals(matrix2.T()));
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void test_pow_should_trows_IllegalArgumentException() throws WrongShapeException {
		matrix1.pow(0);
	}
	
	@Test(expected = WrongShapeException.class)
	public void test_pow_should_trows_WrongShapeException() throws WrongShapeException {
		matrix2.pow(2);
	}
	
	@Test
	public void test_pow_() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{{-456, 475, -28},
												{1012, -849, -855},
												{-455, 498, -835}});
		
		assertTrue(result.equals(matrix1.pow(3)));
		
	}
	
	@Test
	public void test_elementWizeMultiplication() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][] {{-4, 6, -35},
												{-7, 40, -21},
												{-40, 0, 121}});
		
		assertTrue(result.equals(matrix1.elementsWizeMultiplication(matrix3)));
	}
	
	@Test
	public void test_fromArray() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][] {{1,3,2}});
		int[] tab = {1,3,2};
		
		assertTrue(result.equals(Matrix.fromArray(tab)));
	}
	
	@Test
	public void test_setRow() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{ {1,3,2},
												{-1, 5, -7},
												{-8, 7, 11}} );
		int[] tab = {1,3,2};
		matrix1.setRow(0, tab);
		assertTrue(result.equals(matrix1));
	}
	
	@Test
	public void test_setColumn() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{ {1, 1, 5},
												{-1, 3, -7},
												{-8, 2, 11}} );
		
		int[] tab = {1,3,2};
		matrix1.setColumn(0, tab);
		
	}
	
	@Test
	public void test_switchRows() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{ {-1, 5, -7},
												{1, 2, 5},
												{-8, 7, 11}} );
		matrix1.switchRows(0, 1);
		assertTrue(result.equals(matrix1));
	}
	
	@Test
	public void test_switchColumns() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new int[][]{ {2, 1, 5},
												{5, -1, -7},
												{7, -8, 11}} );
		matrix1.switchColumns(0, 1);
		assertTrue(result.equals(matrix1));
	}
	
	@Test
	public void test_average() throws MatrixMisconstructionException, WrongShapeException {
		Matrix result = new Matrix(new double[][]{ 	{-1.5, 2.5, -1.0},
													{3.0, 6.5, -2},
													{-1.5, 3.5, 11}} );
		Matrix[] matrixes = {this.matrix1, this.matrix3};
		
		assertTrue(result.equals(Matrix.AVG(matrixes)));
	}
	
}













