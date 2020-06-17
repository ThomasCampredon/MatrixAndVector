package matrix;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Exception.*;

public class Matrix {
	protected BigDecimal[][] matrix; // a two dimensional table
	protected int[] shape; // i=0 is the rows' number ; i=1 is the columns' number
	
	
	
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
		this.initializeShape();
		// for each rows
		for (int i = 0 ; i < m ; i++) {
			// for each columns
			for (int j = 0 ; j < n ; j++) {
				this.setValueAt(i, j, 0); 
			}
		}
		
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
	protected BigDecimal[][] transformToBigDecimal(int[][] matrix2) {
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
	protected BigDecimal[][] transformToBigDecimal(double[][] matrix2) {
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
	protected void initializeShape() {
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
	
	// return a 2D array
	public BigDecimal[][] toArray(){
		return this.matrix;
	}
	
	// return the at the row m and the column n
	public BigDecimal getValueAt(int m, int n) {
		this.verifyColumnIndex(n);
		this.verifyRowIndex(m);
		return this.matrix[m][n];
	}
	
	// return the m row
	public BigDecimal[] getRow(int m) {
		verifyRowIndex(m);
		return this.matrix[m];
	}
	
	// return the n column
	public BigDecimal[] getColumn(int n) {
		verifyColumnIndex(n);
		BigDecimal[] col = new BigDecimal[this.m()];
		
		// for each rows
		for (int i = 0 ; i < this.m() ; i++) {
			col[i] = this.getValueAt(i, n); // take the n value
		}
		return col;
	}
	
	
	// modify the m row
	public void setRow(int m, int[] newRow) throws WrongShapeException {
		verifyRowIndex(m);
		if (newRow.length != this.n()) {
			throw new WrongShapeException("The new row doesn't have the same length as the previous one ("+newRow.length+" != "+this.n()+")");
		}
		this.matrix[m] = this.tranformToBigDecimal(newRow);
	}
	
	
	// modify the m row
	public void setRow(int m, double[] newRow) throws WrongShapeException {
		verifyRowIndex(m);
		if (newRow.length != this.n()) {
			throw new WrongShapeException("The new row doesn't have the same length as the previous one ("+newRow.length+" != "+this.n()+")");
		}
		this.matrix[m] = this.tranformToBigDecimal(newRow);
	}
	
	// modify the m row
	public void setRow(int m, BigDecimal[] newRow) throws WrongShapeException {
		verifyRowIndex(m);
		if (newRow.length != this.n()) {
			throw new WrongShapeException("The new row doesn't have the same length as the previous one ("+newRow.length+" != "+this.n()+")");
		}
		this.matrix[m] = newRow;
	}
	
	
	// modify the n column
	public void setColumn(int n, int[] newColumn) throws WrongShapeException {
		verifyColumnIndex(n);
		if (newColumn.length != this.m()) {
			throw new WrongShapeException("The new column doesn't have the same length as the previous one ("+newColumn.length+" != "+this.m()+")");
		}
		
		for (int i = 0 ; i < this.m(); i++) {
			this.setValueAt(i, n, newColumn[i]);
		}
	}
	
	// modify the n column
	public void setColumn(int n, double[] newColumn) throws WrongShapeException {
		verifyColumnIndex(n);
		if (newColumn.length != this.m()) {
			throw new WrongShapeException("The new column doesn't have the same length as the previous one ("+newColumn.length+" != "+this.m()+")");
		}
		
		for (int i = 0 ; i < this.m(); i++) {
			this.setValueAt(i, n, newColumn[i]);
		}
	}
		
	// modify the n column
	public void setColumn(int n, BigDecimal[] newColumn) throws WrongShapeException {
		verifyColumnIndex(n);
		if (newColumn.length != this.m()) {
			throw new WrongShapeException("The new column doesn't have the same length as the previous one ("+newColumn.length+" != "+this.m()+")");
		}
		
		for (int i = 0 ; i < this.m(); i++) {
			this.setValueAt(i, n, newColumn[i]);
		}
	}
		

	// modify the value at the row m and the column n
	public void setValueAt(int m, int n, int value) {
		this.verifyColumnIndex(n);
		this.verifyRowIndex(m);
		this.matrix[m][n] = new BigDecimal(value);
	}
	
	// modify the value at the row m and the column n
	public void setValueAt(int m, int n, double value) {
		this.verifyColumnIndex(n);
		this.verifyRowIndex(m);
		this.matrix[m][n] = new BigDecimal(value);
	}
	
	// modify the value at the row m and the column n
	public void setValueAt(int m, int n, BigDecimal value) {
		this.verifyColumnIndex(n);
		this.verifyRowIndex(m);
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
	
	
	//==============HELPERS===================
	
	// change the value of the array into BigDecimal
	protected BigDecimal[] tranformToBigDecimal(int[] array) {
		BigDecimal[] result = new BigDecimal[array.length];
		for (int i = 0 ; i < array.length ; i++) {
			result[i] = new BigDecimal(array[i]);
		}
		return result;
	}
	
	// change the value of the array into BigDecimal
	protected BigDecimal[] tranformToBigDecimal(double[] array) {
		BigDecimal[] result = new BigDecimal[array.length];
		for (int i = 0 ; i < array.length ; i++) {
			result[i] = new BigDecimal(array[i]);
		}
		return result;
	}
	
	
//================CONTROL====================
	
	// verify if the matrix is complete
	protected void verifyEntry(int[][] matrix) throws MatrixMisconstructionException {
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
	protected void verifyEntry(double[][] matrix) throws MatrixMisconstructionException {
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
	protected void verifyEntry(BigDecimal[][] matrix) throws MatrixMisconstructionException {
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
	protected void verifySameShape(Matrix matrix2) throws WrongShapeException {
		if (!Arrays.equals(this.getShape(),matrix2.getShape())) {
			throw new WrongShapeException("The two matrixes don't have the same shape "+
					this.getStringShape()+" != " + matrix2.getStringShape());
		}
	}
	
	// verify if the number of columns of the first matrix is the same as the number of rows of the second matrix
	protected void verifyMultiplicationPossibility(Matrix matrix2) throws WrongShapeException {
		if (this.n() != matrix2.m()) {
			throw new WrongShapeException("The shape of the two matrixes are not compatible for a multiplication : "+
					this.getStringShape()+" incompatible with " + matrix2.getStringShape());
		}
	}
	
	// verify if the m index is superior or equal to 0 and inferior to the number of rows, else throw an exception
	protected void verifyRowIndex(int m) {
		if (!isValidRowIndex(m)) {
			throw new IllegalArgumentException("The row number is not valid : the row number ("+m+")is not between 0 and " + this.m());
		}
	}
	
	// verify if the n index is superior or equal to 0 and inferior to the number of columns, else throw an exception
	protected void verifyColumnIndex(int n) {
		if (!isValidColumnIndex(n)) {
			throw new IllegalArgumentException("The column number is not valid : the column number ("+n+") is not between 0 and " + this.n());
		}
	}
	
	// return true if the matrix is a square matrix
	public boolean isSqareMatrix() {
		return this.m()==this.n();
	}
	
	// return true if the m parameter is compatible with the matrix
	protected boolean isValidRowIndex(int m) {
		return ((m < this.m()) && (m>=0));
	}
	
	// return true if the n parameter is compatible with the matrix
	protected boolean isValidColumnIndex(int n) {
		return (n < this.n()) && (n>=0);
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
	protected BigDecimal sumMultiplication(BigDecimal[] row, BigDecimal[] column) {
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
	
	
	// average values between all the matrix
	public static Matrix AVG(Matrix[] matrixes) throws WrongShapeException {
		int matrixesNumber = matrixes.length;
		
		// all the matrixes need the same shape
		for (int i = 1 ; i < matrixesNumber ; i++) {
			matrixes[i-1].verifySameShape(matrixes[i]);
		}
		Matrix average = new Matrix(matrixes[0].m(), matrixes[0].n());
		
		// for each rows
		for (int i = 0 ; i < matrixes[0].m() ; i++) {
			// for each columns
			for (int j = 0 ; j < matrixes[0].n() ; j++) {
				BigDecimal avg = BigDecimal.ZERO;
					
				// for all matrixes
				for (int k = 0 ; k < matrixesNumber ; k++) {
					avg = avg.add(matrixes[k].getValueAt(i, j));
				}
				avg = avg.divide(new BigDecimal(matrixesNumber));
				average.setValueAt(i, j, avg);
			}
		} 		
		return average;
	}
	
	
	// average values between all the matrix
	public static Matrix AVG(ArrayList<Matrix> matrixes) throws WrongShapeException {
		int matrixesNumber = matrixes.size();
		
		// all the matrixes need the same shape
		for (int i = 1 ; i < matrixesNumber ; i++) {
			matrixes.get(i-1).verifySameShape(matrixes.get(i));
		}
		Matrix average = new Matrix(matrixes.get(0).m(), matrixes.get(0).n());
		
		// for each rows
		for (int i = 0 ; i < matrixes.get(0).m() ; i++) {
			// for each columns
			for (int j = 0 ; j < matrixes.get(0).n() ; j++) {
				BigDecimal avg = BigDecimal.ZERO;
					
				// for all matrixes
				for (int k = 0 ; k < matrixesNumber ; k++) {
					avg = avg.add(matrixes.get(k).getValueAt(i, j));
				}
				avg = avg.divide(new BigDecimal(matrixesNumber));
				average.setValueAt(i, j, avg);
			}
		} 		
		return average;
	}
	
	
	// sum of the values of the matrixes
	public static Matrix SUM(Matrix[] matrixes) throws WrongShapeException {
		int matrixesNumber = matrixes.length;
		
		// all the matrixes need the same shape
		for (int i = 1 ; i < matrixesNumber ; i++) {
			matrixes[i-1].verifySameShape(matrixes[i]);
		}
		Matrix sum = new Matrix(matrixes[0].m(), matrixes[0].n());
		
		// for each rows
		for (int i = 0 ; i < matrixes[0].m() ; i++) {
			// for each columns
			for (int j = 0 ; j < matrixes[0].n() ; j++) {
				BigDecimal qsum = BigDecimal.ZERO;
					
				// for all matrixes
				for (int k = 0 ; k < matrixesNumber ; k++) {
					qsum = qsum.add(matrixes[k].getValueAt(i, j));
				}
				sum.setValueAt(i, j, qsum);
			}
		}
		return sum;
	}
	
	
	// sum of the values of the matrixes
	public static Matrix SUM(ArrayList<Matrix> matrixes) throws WrongShapeException {
		int matrixesNumber = matrixes.size();
		
		// all the matrixes need the same shape
		for (int i = 1 ; i < matrixesNumber ; i++) {
			matrixes.get(i-1).verifySameShape(matrixes.get(i));
		}
		Matrix sum = new Matrix(matrixes.get(0).m(), matrixes.get(0).n());
		
		// for each rows
		for (int i = 0 ; i < matrixes.get(0).m() ; i++) {
			// for each columns
			for (int j = 0 ; j < matrixes.get(0).n() ; j++) {
				BigDecimal qsum = BigDecimal.ZERO;
					
				// for all matrixes
				for (int k = 0 ; k < matrixesNumber ; k++) {
					qsum = qsum.add(matrixes.get(k).getValueAt(i, j));
				}
				sum.setValueAt(i, j, qsum);
			}
		}
		return sum;
	}
		
		
	
//============TRANSFORMATION==============
	
	// randomize the values of the matrix ([-1 ; 1])
	public void randomizeValue() {
		Random random = new Random();
		for (int i = 0 ; i < this.m() ; i++) {
			for (int j = 0 ; j < this.n() ; j++) {
				this.setValueAt(i, j, random.nextDouble()*2.0-1.0);
			}
		}
	}
	
	// randomize the values of the matrix ([-max ; +max])
	public void randomizeValue(double max) {
		Random random = new Random();
		for (int i = 0 ; i < this.m() ; i++) {
			for (int j = 0 ; j < this.n() ; j++) {
				double value = (random.nextDouble()*(2.0*max)-max);
				this.setValueAt(i, j, value);
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
	
	// switch 2 rows
	public void switchRows(int indexRow1, int indexRow2) throws WrongShapeException {
		this.verifyRowIndex(indexRow1);
		this.verifyRowIndex(indexRow2);
		BigDecimal[] buff = this.getRow(indexRow1);
		this.setRow(indexRow1, this.getRow(indexRow2));
		this.setRow(indexRow2, buff);
	}
	
	// switch 2 columns
	public void switchColumns(int indexColumn1, int indexColumn2) throws WrongShapeException {
		this.verifyColumnIndex(indexColumn1);
		this.verifyColumnIndex(indexColumn2);
		BigDecimal[] buff = this.getColumn(indexColumn1);
		this.setColumn(indexColumn1, this.getColumn(indexColumn2));
		this.setColumn(indexColumn2, buff);
	}
	
	
	
	
	
	/*
	// inverse of the matrix
	public Matrix inverse() {
		Matrix inverse = new Matrix(1, 1);
		return null; 
	}
	*/
	
	
	
}


















