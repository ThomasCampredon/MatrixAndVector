package matrix;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

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
	

	//===============SPECIAL MATRIX===============
	
	// return a blank matrix
	public Matrix(int m, int n) {
		this.matrix = new BigDecimal[m][n];
		
		// for each rows
		for (int i = 0 ; i < m ; i++) {
			// for each columns
			for (int j = 0 ; j < n ; j++) {
				this.setValueAt(i, j, 0); 
			}
		}
		this.initializeShape();
	}
	
	// return the identity matrix
	public static Matrix identity(int size) {
		Matrix identity = new Matrix(size, size);
		
		for (int i = 0 ; i < size ; i++) {
			for (int j = 0 ; j < size ; j++) {
				if (i==j) {
					identity.setValueAt(i, j, 1);
				}
			}
		}
		return identity;
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
	
	public int[] getShape() {
		return this.shape;
	}
	
	// return a string which contains the shape
	public String getStringShape() {
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
	
	// return the at the row m and the column n
	public BigDecimal getValueAt(int m, int n) {
		return this.matrix[m][n];
	}
	
	// modify the value at the row m and the column n
	public void setValueAt(int m, int n, int value) {
		this.matrix[m][n] = new BigDecimal(value);
	}
	
	// modify the value at the row m and the column n
	public void setValueAt(int m, int n, double value) {
		this.matrix[m][n] = new BigDecimal(value);
	}
	
	// modify the value at the row m and the column n
	public void setValueAt(int m, int n, BigDecimal value) {
		this.matrix[m][n] = value;
	}
	
	// display of the matrix
	public String toString() {
		String info = "";
		DecimalFormat df = new DecimalFormat("###.########");
		
		for (int i = 0 ; i < m() ; i++) {
			info+="| ";
			for (int j = 0 ; j < n() ; j++) {
				BigDecimal value = getValueAt(i, j);
				if (value.compareTo(BigDecimal.ZERO)>=0) {
					info+=" ";
				}
				info+=df.format(value.doubleValue())+" ";
			}
			info+="|\n";
		}
		return info;
	}
	
	
	
//=============COMPARISON=============
	
	// verify if the two matrixes have the same shape, if not, throw an exception
	private void verifySameShape(Matrix matrix2) throws WrongShapeException {
		if (!Arrays.equals(this.getShape(),matrix2.getShape())) {
			throw new WrongShapeException("The two matrixes don't have the same shape "+
					this.getStringShape()+" != " + matrix2.getStringShape());
		}
	}
	
	// test if all the values of the matrix are same as the other
	public boolean equals(Matrix matrix2) throws WrongShapeException {
		verifySameShape(matrix2);
		
		for (int i = 0 ; i < this.m() ; i++) {
			for (int j = 0 ; j < this.n() ; j++) {
				if (!this.getValueAt(i, j).equals(matrix2.getValueAt(i, j))) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
//===============OPERATIONS===============
	
	// a matrix plus a matrix
	public Matrix add(Matrix matrix2) {
		return null;
	}
	
	
	// a matrix minus a matrix
	public Matrix subtract(Matrix matrix2) {
		return null;
	}
	
	
	// a matrix multiply by a matrix
	public Matrix multiply(Matrix matrix2) {
		return null;
	}
	
	
	// transpose of the matrix
	public Matrix T() {
		return null;
	}
	
	
}


















