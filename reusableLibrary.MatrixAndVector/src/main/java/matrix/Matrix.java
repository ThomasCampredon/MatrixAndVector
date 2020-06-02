package matrix;

import java.math.BigDecimal;

import Exception.*;

public class Matrix {
	private BigDecimal[][] matrix; // a two dimensional table
	private int[] shape; // i=0 is the rows' number ; i=1 is the columns' number
	
	
//===============CONSTRUCTORS==================
	
	public Matrix(int[][] matrix) throws MatrixMisconstructionException {
		this.verifyEntry(matrix);
		this.matrix = this.transformToBigDecimal(matrix);
		this.initializeShape();
	}

	public Matrix (double[][]matrix) throws MatrixMisconstructionException {
		this.verifyEntry(matrix);
		this.matrix = this.transformToBigDecimal(matrix);
		this.initializeShape();
	}
	
	public Matrix (BigDecimal[][]matrix) throws MatrixMisconstructionException {
		this.verifyEntry(matrix);
		this.matrix = matrix;
		this.initializeShape();
	}


	
	
//===============CONSTRUCTORS HELPERS=================
	
	// return a BigDecimal table
	private BigDecimal[][] transformToBigDecimal(int[][] matrix2) {
		BigDecimal[][] result = new BigDecimal[matrix2.length][matrix2[0].length];
		
		// for each rows
		for (int i = 0 ; i < result.length ; i++) {
			// for each columns
			for (int j = 0 ; j < result[0].length ; j++) {
				result[i][j] = new BigDecimal(matrix2[i][j]);
			}
		}
		return result;
	}
	
	
	// return a BigDecimal table
	private BigDecimal[][] transformToBigDecimal(double[][] matrix2) {
		BigDecimal[][] result = new BigDecimal[matrix2.length][matrix2[0].length];
		
		// for each rows
		for (int i = 0 ; i < result.length ; i++) {
			// for each columns
			for (int j = 0 ; j < result[0].length ; j++) {
				result[i][j] = new BigDecimal(matrix2[i][j]);
			}
		}
		return result;
	}
	
	
	// save the matrix size so that it does not need to be recalculated 
	private void initializeShape() {
		this.shape = new int[2];
		this.shape[0] = this.matrix.length;
		this.shape[1] = this.matrix[0].length;
	}
	
	
	// verify if the matrix is complete
	private void verifyEntry(int[][] matrix) throws MatrixMisconstructionException {
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		//for each rows
		for (int i = 0 ; i < rows ; i++) {
			// check if the number of columns is always the same, else throws an exception
			if (matrix[i].length != columns) {
				throw new MatrixMisconstructionException ("The matrix lack some elements");
			}
		}
	}
	
	// verify if the matrix is complete
	private void verifyEntry(double[][] matrix) throws MatrixMisconstructionException {
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		//for each rows
		for (int i = 0 ; i < rows ; i++) {
			// check if the number of columns is always the same, else throws an exception
			if (matrix[i].length != columns) {
				throw new MatrixMisconstructionException ("The matrix lack some elements");
			}
		}
	}
	
	// verify if the matrix is complete
	private void verifyEntry(BigDecimal[][] matrix) throws MatrixMisconstructionException {
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		//for each rows
		for (int i = 0 ; i < rows ; i++) {
			// check if the number of columns is always the same, else throws an exception
			if (matrix[i].length != columns) {
				throw new MatrixMisconstructionException ("The matrix lack some elements");
			}
		}
	}

	
	
//==============GETTERS AND SETTERS================
	
	public int[] getTableShape() {
		return this.shape;
	}
	
	// return a string which contains the shape
	public String getShape() {
		return "("+ this.shape[0] + ", " + this.shape[1] + ")";
	}
	
	// return the number of rows
	public int m() {
		return this.shape[0];
	}
	
	// return the number of columns
	public int n() {
		return this.shape[1];
	}
	
	
	
}


















