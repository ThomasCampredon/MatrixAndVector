package matrix;

import java.math.BigDecimal;

public class Matrix {
	private BigDecimal[][] matrix;
	private int[] shape;
	
	
//===============CONSTRUCTORS==================
	
	public Matrix(int[][] matrix) {
		this.matrix = this.transformToBigDecimal(matrix);
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
	
	
	// set the value of the matrix so that it does not need to be recalculated 
	private void initializeShape() {
		this.shape = new int[2];
		this.shape[0] = this.matrix.length;
		this.shape[1] = this.matrix[0].length;
	}

	
	
//==============GETTERS AND SETTERS================
	
	public int[] getTableShape() {
		return this.shape;
	}
	
	public String getShape() {
		return "("+ this.shape[0] + ", " + this.shape[1] + ")";
	}
	
	
	
}
