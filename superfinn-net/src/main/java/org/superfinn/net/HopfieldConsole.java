package org.superfinn.net;

import org.apache.log4j.Logger;

public class HopfieldConsole {

	final static Logger log = Logger.getLogger("Hopfield");

	public static String formatBoolean(final boolean b[]) {
		final StringBuilder result = new StringBuilder();
		result.append('[');
		for (int i = 0; i < b.length; i++) {
			if (b[i]) {
				result.append("T");
			} else {
				result.append("F");
			}
			if (i != b.length - 1) {
				result.append(",");
			}
		}
		result.append(']');
		return (result.toString());
	}
	
	public static void main(String [] args){
		HopfieldNetwork hopfield=new HopfieldNetwork(4);
		boolean [] pattern1={ true, true, false, false };
		boolean[] pattern2 = { true, false, false, false };
		boolean[] result;
		log.info("Training Hopfield network with: "
				+ formatBoolean(pattern1));
		hopfield.train(pattern1);
		result=hopfield.tryMatch(pattern1);
		log.info("Presenting pattern:" + formatBoolean(pattern1)
				+ ", and got " + formatBoolean(result));
		// Present pattern2, which is similar to pattern 1. Pattern 1
		// should be recalled.
		result = hopfield.tryMatch(pattern2);
		log.info("Presenting pattern:" + formatBoolean(pattern2)
				+ ", and got " + formatBoolean(result));
	}
}
