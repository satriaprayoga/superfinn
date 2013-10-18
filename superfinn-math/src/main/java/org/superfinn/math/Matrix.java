/**
 * 
 */
package org.superfinn.math;

import java.io.Serializable;

/**
 * @author satriaprayoga
 *
 */
public class Matrix implements Cloneable,Serializable{

	private static final long serialVersionUID = -7595853365092410844L;
	
	private double [][] matrix;
	
	public Matrix(final double m[][]) {
		matrix=new double[m.length][m[0].length];
		for(int i=0;i<row();i++){
			for(int j=0;j<col();j++){
				set(i, j, m[i][j]);
			}
		}
	}
	
	public Matrix(final boolean m[][]){
		matrix=new double[m.length][m[0].length];
		for(int i=0;i<row();i++){
			for(int j=0;j<col();j++){
				if(m[i][j]){
					set(i, j, 1);
				}else{
					set(i, j, 0);
				}
			}
		}
	}
	
	public static Matrix createColumnMatrix(final double [] input){
		final double [][] m=new double[input.length][1];
		for(int i=0;i<m.length;i++){
			m[i][0]=input[i];
		}
		return new Matrix(m);
	}
	
	public static Matrix createRowMatrix(final double [] input){
		final double d[][] = new double[1][input.length];
		System.arraycopy(input, 0, d[0], 0, input.length);
		return new Matrix(d);
	}
	
	public Matrix getRow(final int r){
		if(r>row()){
			throw new MatrixException("invalid row #" + r);
		}
		final double row[][]=new double[1][col()];
		for(int i=0;i<col();i++){
			row[0][i]=matrix[r][i];
		}
		return new Matrix(row);
	}
	
	public Matrix getCol(final int c){
		if(c>col()){
			throw new MatrixException("invalid column #" + c);
		}
		final double col[][]=new double[row()][1];
		for(int i=0;i<row();i++){
			col[i][0]=matrix[i][c];
		}
		return new Matrix(col);
	}
	
	public void set(final int i,final int j,final double v){
		validate(i, j);
		matrix[i][j]=v;
	}
	
	public double get(final int i,final int j){
		validate(i, j);
		return matrix[i][j];
	}
	
	public void clear(){
		for(int i=0;i<row();i++){
			for(int j=0;j<col();j++){
				set(i, j, 0);
			}
		}
	}
	
	public int row(){
		return matrix.length;
	}
	
	public int col(){
		return matrix[0].length;
	}
	
	public int size(){
		return matrix.length*matrix[0].length;
	}
	
	public boolean isVector(){
		if(row()==1){
			return true;
		}else{
			return col()==1;
		}
	}
	
	public boolean isZeros(){
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				if(matrix[i][j]!=0){
					return false;
				}
			}
		}
		return true;
	}
	
	public void random(final double max,final double min){
		for(int i=0;i<row();i++){
			for(int j=0;j<col();j++){
				matrix[i][j]=(Math.random()*(max-min))+min;
			}
		}
	}
	
	public double [] toArray(){
		final double array[]=new double[row()*col()];
		int index=0;
		for(int i=0;i<row();i++){
			for(int j=0;j<col();j++){
				array[index++]=matrix[i][j];
			}
		}
		return array;
	}
	
	private void validate(int r,int c){
		if(r>=row()|| r<0){
			throw new MatrixException("the row: "+r+" is out of range: "+row());
		}
		if(c>=col()||c<0){
			throw new MatrixException("the row: "+c+" is out of range: "+col());
		}
	}
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Matrix(matrix);
	}

}
