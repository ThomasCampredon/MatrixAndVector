package vector;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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

//================INFORMATIONS===============
	
	@Override
	public String toString() {
		String info ="| ";
		DecimalFormat df = new DecimalFormat("###.########");
		info += df.format(this.getValueAt(0, 0));
		
		for (int i = 1 ; i < this.n() ; i++) {
			info +=  " " + df.format(this.getValueAt(0, i));
		}
		info += " |";
		
		return info;
	}
	
	public int length() {
		return this.n();
	}
	
	
//==================OPERATIONS================
	
	@Override
	public RowVector multiply(int scalar) {
		Matrix superC = super.multiply(scalar);
		RowVector result = new RowVector(this.n());
		try {
			result.setRow(0, superC.getRow(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public RowVector multiply(double scalar) {
		Matrix superC = super.multiply(scalar);
		RowVector result = new RowVector(this.n());
		try {
			result.setRow(0, superC.getRow(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public RowVector multiply(BigDecimal scalar) {
		Matrix superC = super.multiply(scalar);
		RowVector result = new RowVector(this.n());
		try {
			result.setRow(0, superC.getRow(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public ColumnVector T() {
		ColumnVector result = new ColumnVector(this.n());
		
		try {
			result.setColumn(0, this.getRow(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public RowVector add(RowVector vect2) {
		try {
			super.verifySameShape(vect2);
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		RowVector result = new RowVector(this.n());
		try {
			result.setRow(0, this.add(vect2).getRow(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public RowVector subtract(RowVector vect2) {
		try {
			super.verifySameShape(vect2);
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		RowVector result = new RowVector(this.n());
		try {
			result.setRow(0, this.subtract(vect2).getRow(0));
		} catch (WrongShapeException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public RowVector elementsWizeMultiplication(RowVector vect2) throws WrongShapeException {
		this.verifySameShape(vect2);
		
		RowVector result = new RowVector(this.n());
		
		//for each rows
		for (int i = 0 ; i < this.m(); i++) {
			// for each columns
			for (int j = 0 ; j < this.n() ; j++) {
				result.setValueAt(i, j, this.getValueAt(i, j).multiply(vect2.getValueAt(i, j)));
			}
		}
		return result;
	}
	
	public RowVector powOnElements(int power) {
		RowVector powered = this;
		
		for (int i = 0 ; i < this.length() ; i++) {
			powered.setValueAt(0, i, this.getValueAt(0, i).pow(power));
		}
		return powered;
	}
	
	
}























