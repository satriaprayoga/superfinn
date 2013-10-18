/**
 * 
 */
package org.superfinn.math;

/**
 * @author satriaprayoga
 *
 */
public class MatrixOps {

	public static Matrix add(final Matrix a,final Matrix b){
		validateRowCol(a, b);
		final double result[][]=new double[a.row()][a.col()];
		for(int i=0;i<a.row();i++){
			for(int j=0;j<a.col();j++){
				result[i][j]=a.get(i, j)+b.get(i, j);
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix subtract(final Matrix a,final Matrix b){
		validateRowCol(a, b);
		final double result[][]=new double[a.row()][a.col()];
		for(int i=0;i<a.row();i++){
			for(int j=0;j<a.col();j++){
				result[i][j]=a.get(i, j)-b.get(i, j);
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix multiply(final Matrix source,final double v){
		final double result[][]=new double[source.row()][source.col()];
		for(int i=0;i<source.row();i++){
			for(int j=0;j<source.col();j++){
				result[i][j]=source.get(i, j)*v;
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix multiply(final Matrix a,final Matrix b){
		if(a.col()!=b.row()){
			throw new MatrixException("Multiplication fail. row & column mismatch: "+a.col()+" != "+b.row());
		}
		final double result[][]=new double[a.row()][b.col()];
		for(int i=0;i<a.row();i++){
			for(int j=0;j<b.col();j++){
				double value=0;
				for(int k=0;k<a.col();k++){
					value+=a.get(i, k)*b.get(k, j);
				}
				result[i][j]=value;
			}
		}
		return new Matrix(result);
	}
	
	public static double dotProduct(final Matrix a,final Matrix b){
		if(!a.isVector() || !b.isVector()){
			throw new MatrixException("dot product fail. both matrices must be a vectors.");
		}
		
		final double [] aArray=a.toArray();
		final double [] bArray=b.toArray();
		if(aArray.length!=bArray.length){
			throw new MatrixException("mismatch vector size");
		}
		
		double result=0;
		final int length=aArray.length;
		for(int i=0;i<length;i++){
			result+=aArray[i]*bArray[i];
		}
		return result;
	}
	
	public static void copy(final Matrix source,final Matrix target){
		for(int i=0;i<target.row();i++){
			for(int j=0;j<target.col();j++){
				target.set(i, j, source.get(i, j));
			}
		}
	}
	
	public static Matrix transpose(final Matrix source){
		final double transpose[][]=new double[source.col()][source.row()];
		for(int i=0;i<source.row();i++){
			for(int j=0;j<source.col();j++){
				transpose[j][i]=source.get(i, j);
			}
		}
		return new Matrix(transpose);
	}
	
	public static Matrix identity(final int size){
		if(size<1){
			throw new MatrixException("invalid size. size must be >= 1");
		}
		final Matrix identity=new Matrix(size, size);
		for(int i=0;i<size;i++){
			identity.set(i, i, 1);
		}
		return identity;
	}
	
	private static void validateRowCol(final Matrix a,final Matrix b){
		if(a.row()!=b.row()){
			throw new MatrixException("mismatch row: "+a.row()+" and "+b.row());
		}
		if(a.col()!=b.col()){
			throw new MatrixException("mismatch column: "+a.col()+" and "+b.col());
		}
	}
	
	private MatrixOps(){}
	
}
