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
	
	public static void copy(final Matrix source,final Matrix target){
		for(int i=0;i<target.row();i++){
			for(int j=0;j<target.col();j++){
				target.set(i, j, source.get(i, j));
			}
		}
	}
	
	private static void validateRowCol(final Matrix a,final Matrix b){
		if(a.row()!=b.row()){
			throw new MatrixException("matrix row mismatch: "+a.row()+" and "+b.row());
		}
		if(a.col()!=b.col()){
			throw new MatrixException("matrix column mismatch: "+a.col()+" and "+b.col());
		}
	}
	
}
