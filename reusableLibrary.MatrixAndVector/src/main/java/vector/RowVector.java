package vector;

import java.math.BigDecimal;

import Exception.WrongShapeException;
import matrix.Matrix;

public class RowVector extends Matrix{

	
//===========CONSTRUCTORS===============
	
	public RowVector(int length) {
		super(1, length);
	}
	
	public RowVector(int[] values) throws WrongShapeException {
		this(values.length);
		this.setRow(0, values);
	}
	
	public RowVector(double[] values) throws WrongShapeException {
		this(values.length);
		this.setRow(0, values);
	}
	
	public RowVector(BigDecimal[] values) throws WrongShapeException {
		this(values.length);
		this.setRow(0, values);
	}
}
