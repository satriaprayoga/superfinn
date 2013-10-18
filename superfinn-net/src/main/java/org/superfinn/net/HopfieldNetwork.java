/**
 * 
 */
package org.superfinn.net;

import org.superfinn.math.BipolarUtil;
import org.superfinn.math.Matrix;
import org.superfinn.math.MatrixOps;

/**
 * @author satriaprayoga
 *
 */
public class HopfieldNetwork {

	private Matrix weightMatrix;
	
	public HopfieldNetwork(final int size) {
		weightMatrix=new Matrix(size, size);
	}
	
	public void train(final boolean [] pattern){
		if(pattern.length!=weightMatrix.row()){
			throw new NeuralNetworkException("invalid pattern size. valid size is: "+weightMatrix.row());
		}
		final Matrix input=Matrix.createRowMatrix(BipolarUtil.bipolar2double(pattern));
		final Matrix transposeInput=MatrixOps.transpose(input);
		final Matrix connections=MatrixOps.multiply(transposeInput, input);
		final Matrix identity=MatrixOps.identity(connections.row());
		final Matrix connectionsResult=MatrixOps.subtract(connections, identity);
		weightMatrix=MatrixOps.add(weightMatrix, connectionsResult);
	}
	
	public boolean [] tryMatch(final boolean [] pattern){
		final boolean[] output=new boolean[pattern.length];
		final Matrix input=Matrix.createRowMatrix(BipolarUtil.bipolar2double(pattern));
		for(int i=0;i<pattern.length;i++){
			Matrix colMatrix=weightMatrix.getCol(i);
			colMatrix=MatrixOps.transpose(colMatrix);
			final double product=MatrixOps.dotProduct(input, colMatrix);
			if(product>0){
				output[i]=true;
			}else{
				output[i]=false;
			}
		}
		return output;
	}
	
	public Matrix getWeightMatrix() {
		return weightMatrix;
	}
}
