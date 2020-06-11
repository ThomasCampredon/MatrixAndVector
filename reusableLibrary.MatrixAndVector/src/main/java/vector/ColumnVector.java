package vector;

import java.math.BigDecimal;

import Exception.WrongShapeException;
import matrix.Matrix;

public class ColumnVector extends Matrix{

	
//===========CONSTRUCTORS===============
	
	public ColumnVector(int length) {
		super(length, 1);
	}
	
	public ColumnVector(int[] values) throws WrongShapeException {
		this(values.length);
		this.setColumn(0, values);
	}
	
	public ColumnVector(double[] values) throws WrongShapeException {
		this(values.length);
		this.setColumn(0, values);
	}
	
	public ColumnVector(BigDecimal[] values) throws WrongShapeException {
		this(values.length);
		this.setColumn(0, values);
	}
}
