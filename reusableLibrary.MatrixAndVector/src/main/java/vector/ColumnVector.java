package vector;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
	
	
//================INFORMATIONS===============

	@Override
	public String toString() {
		String info = "";
		DecimalFormat df = new DecimalFormat("###.########");
		
		for (int i = 0 ; i < m() ; i++) {
			info+="| ";
			BigDecimal value = getValueAt(i, 0);
			if (value.compareTo(BigDecimal.ZERO)>=0) {
				info+=" ";
			}
			info+=df.format(value.doubleValue())+" ";
			
			info+="|\n";
		}
		return info;
	}
	
	
	
	
//==================OPERATIONS================
	
	@Override
	public ColumnVector multiply(int scalar) {
		Matrix superC = super.multiply(scalar);
		ColumnVector result = new ColumnVector(this.m());
		try {
			result.setColumn(0, superC.getColumn(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public ColumnVector multiply(double scalar) {
		Matrix superC = super.multiply(scalar);
		ColumnVector result = new ColumnVector(this.m());
		try {
			result.setColumn(0, superC.getColumn(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public ColumnVector multiply(BigDecimal scalar) {
		Matrix superC = super.multiply(scalar);
		ColumnVector result = new ColumnVector(this.m());
		try {
			result.setColumn(0, superC.getColumn(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public RowVector T() {
		RowVector result = new RowVector(this.m());
		
		try {
			result.setRow(0, this.getColumn(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ColumnVector add(ColumnVector vect2) {
		try {
			super.verifySameShape(vect2);
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		ColumnVector result = new ColumnVector(this.m());
		try {
			result.setColumn(0, this.add(vect2).getColumn(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public ColumnVector subtract(ColumnVector vect2) {
		try {
			super.verifySameShape(vect2);
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		ColumnVector result = new ColumnVector(this.m());
		try {
			result.setColumn(0, this.subtract(vect2).getColumn(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public ColumnVector elementsWizeMultiplication(ColumnVector vect2) throws WrongShapeException {
		this.verifySameShape(vect2);
		
		ColumnVector result = new ColumnVector(this.n());
		
		//for each rows
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns
			for (int j = 0 ; j < this.n() ; j++) {
				result.setValueAt(i, j, this.getValueAt(i, j).multiply(vect2.getValueAt(i, j)));
			}
		}
		return result;
	}
	
	
}
