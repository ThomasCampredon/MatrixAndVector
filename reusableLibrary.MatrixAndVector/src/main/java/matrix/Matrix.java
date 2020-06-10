package matrix;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

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
		if (m <= 0 || n <=0) {
			throw new IllegalArgumentException("The sizes of the matrix must be superior to 0");
		}
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
	
	// return the m row
	public BigDecimal[] getRow(int m) {
		if ((m >= this.m()) || (m<0)) {
			throw new IllegalArgumentException("The row number is not valid : the row number ("+m+")is not between 0 and " + this.m());
		}
		return this.matrix[m];
	}
	
	// return the n column
	public BigDecimal[] getColumn(int n) {
		if ((n >= this.n()) || (n<0)) {
			throw new IllegalArgumentException("The column number is not valid : the column number ("+n+") is not between 0 and " + this.n());
		}
		BigDecimal[] col = new BigDecimal[this.m()];
		
		// for each rows
		for (int i = 0 ; i < this.m() ; i++) {
			col[i] = this.getValueAt(i, n); // take the n value
		}
		return col;
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
	
	
	
//================CONTROL====================
	
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
	
	// verify if the two matrixes have the same shape, if not, throw an exception
	private void verifySameShape(Matrix matrix2) throws WrongShapeException {
		if (!Arrays.equals(this.getShape(),matrix2.getShape())) {
			throw new WrongShapeException("The two matrixes don't have the same shape "+
					this.getStringShape()+" != " + matrix2.getStringShape());
		}
	}
	
	// verify if the number of columns of the first matrix is the same as the number of rows of the second matrix
	private void verifyMultiplicationPossibility(Matrix matrix2) throws WrongShapeException {
		if (this.n() != matrix2.m()) {
			throw new WrongShapeException("The shape of the two matrixes are not compatible for a multiplication : "+
					this.getStringShape()+" incompatible with " + matrix2.getStringShape());
		}
	}
	
	// return true if the matrix is a square matrix
	public boolean isSqareMatrix() {
		return this.m()==this.n();
	}
	
//=============COMPARISON=============
	
	
	
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
	public Matrix add(Matrix matrix2) throws WrongShapeException {
		this.verifySameShape(matrix2);
		
		Matrix addition = new Matrix(m(), n());
		BigDecimal current;
		
		// for each rows
		for (int i = 0 ; i < m() ; i++) {
			//for each columns
			for (int j = 0 ; j < n() ; j++) {
				current = this.getValueAt(i, j).add(matrix2.getValueAt(i, j));
				addition.setValueAt(i, j, current);
			}
		}
		
		return addition;
	}
	
	
	// a matrix minus a matrix
	public Matrix subtract(Matrix matrix2) throws WrongShapeException {
		this.verifySameShape(matrix2);
		
		Matrix subtraction = new Matrix(m(), n());
		BigDecimal current;
		
		// for each rows
		for (int i = 0 ; i < m() ; i++) {
			//for each columns
			for (int j = 0 ; j < n() ; j++) {
				current = this.getValueAt(i, j).subtract(matrix2.getValueAt(i, j));
				subtraction.setValueAt(i, j, current);
			}
		}
		return subtraction;
	}
	
	
	// a matrix multiply by a matrix
	public Matrix multiply(Matrix matrix2) throws WrongShapeException {
		this.verifyMultiplicationPossibility(matrix2);
		
		Matrix multiplication = new Matrix(this.m(), matrix2.n());
		
		//for each rows of the first matrix
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns of the second matrix
			for (int j = 0 ; j < matrix2.n() ; j++) {
				BigDecimal value = this.sumMultiplication(this.getRow(i), matrix2.getColumn(j));
				multiplication.setValueAt(i, j, value);
			}
		}
		return multiplication;
	}
	
	
	// a matrix multiply by a scalar
	public Matrix multiply(int scalar) {		
		Matrix multiplication = new Matrix(this.m(), this.n());
		
		//for each rows
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns
			for (int j = 0 ; j < this.n() ; j++) {
				multiplication.setValueAt(i, j, this.getValueAt(i, j).multiply(new BigDecimal(scalar)));
			}
		}
		return multiplication;
	}
	
	// a matrix multiply by a scalar
	public Matrix multiply(double scalar) {		
		Matrix multiplication = new Matrix(this.m(), this.n());
		
		//for each rows
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns
			for (int j = 0 ; j < this.n() ; j++) {
				multiplication.setValueAt(i, j, this.getValueAt(i, j).multiply(new BigDecimal(scalar)));
			}
		}
		return multiplication;
	}
	
	// a matrix multiply by a scalar
	public Matrix multiply(BigDecimal scalar) {		
		Matrix multiplication = new Matrix(this.m(), this.n());
		
		//for each rows
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns
			for (int j = 0 ; j < this.n() ; j++) {
				multiplication.setValueAt(i, j, this.getValueAt(i, j).multiply(scalar));
			}
		}
		return multiplication;
	}
	
	
	// multiplication of each terms with the one at the same index
	public Matrix elementsWizeMultiplication(Matrix matrix2) throws WrongShapeException {
		this.verifySameShape(matrix2);
		
		Matrix result = new Matrix(this.m(), this.n());
		
		//for each rows
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns
			for (int j = 0 ; j < this.n() ; j++) {
				result.setValueAt(i, j, this.getValueAt(i, j).multiply(matrix2.getValueAt(i, j)));
			}
		}
		return result;
	}
	
	
	// sum of the multiplication term to term of each array
	private BigDecimal sumMultiplication(BigDecimal[] row, BigDecimal[] column) {
		BigDecimal result = BigDecimal.ZERO;
		
		for (int i = 0 ; i < row.length ; i++) {
			result = result.add(row[i].multiply(column[i]));
		}
		return result;
	}
	
	
	// transpose of the matrix
	public Matrix T() {
		Matrix transpose = new Matrix(this.n(), this.m());
		
		// for each row
		for (int i = 0 ; i < this.m() ; i++) {
			//for each column
			for (int j = 0 ; j < this.n() ; j++) {
				transpose.setValueAt(j, i, this.getValueAt(i, j));
			}
		}
		return transpose;
	}
	
	
	// matrix powered
	public Matrix pow(int power) throws WrongShapeException {
		if (power < 1) {
			throw new IllegalArgumentException("The power must be strictly superior to 0");
		}
		if (!this.isSqareMatrix()) {
			throw new WrongShapeException("This matrix is not a sqare matrix, it can not be powered ");
		}
		if (power == 1) {
			return this;
		}else {
			Matrix powered = this;
			
			for(int i = 0 ; i < power-1 ; i++) {
				powered = powered.multiply(this);
			}
			return powered;
		}
	}
	
	
	
//============TRANSFORMATION==============
	
	// randomize the values of the matrix
	public void randomizeValue() {
		Random random = new Random();
		for (int i = 0 ; i < this.m() ; i++) {
			for (int j = 0 ; j < this.n() ; j++) {
				this.setValueAt(i, j, random.nextDouble()*2.0-1.0);
			}
		}
	}
	
	
	// create a row matrix base on an array
	public static Matrix fromArray(int[] array) {
		Matrix result = new Matrix(1, array.length);
		
		for (int i = 0 ; i < array.length ; i++) {
			result.setValueAt(0, i, new BigDecimal(array[i]));
		}
		
		return result;
	}
	
	// create a row matrix base on an array
	public static Matrix fromArray(double[] array) {
		Matrix result = new Matrix(1, array.length);
		
		for (int i = 0 ; i < array.length ; i++) {
			result.setValueAt(0, i, new BigDecimal(array[i]));
		}
		
		return result;
	}
	
	// create a row matrix base on an array
	public static Matrix fromArray(BigDecimal[] array) {
		Matrix result = new Matrix(1, array.length);
		
		for (int i = 0 ; i < array.length ; i++) {
			result.setValueAt(0, i, array[i]);
		}
		
		return result;
	}
	
	
	// return a 2D array
	public BigDecimal[][] toArray(){
		return this.matrix;
	}
	
	
	/*
	// inverse of the matrix
	public Matrix inverse() {
		Matrix inverse = new Matrix(1, 1);
		return null; 
	}
	*/
	
	
	
}


















