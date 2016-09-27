/**
 *
 */
package com.ankamagames.dofus.harvey;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class RandomVariableUtils
{
	public static final int CONVERTION_FACTOR = 1000000000;//(int) Float.intBitsToFloat(Float.floatToIntBits(Integer.MAX_VALUE)-1);
	public static final int ONE = CONVERTION_FACTOR;
	public static final int SMALLEST = 1;

	public static float convertToFloat(final int fixedPrecisionProbability)
	{
		return fixedPrecisionProbability/(float)CONVERTION_FACTOR;
	}

	public static double convertToDouble(final int fixedPrecisionProbability)
	{
		return fixedPrecisionProbability/(double)CONVERTION_FACTOR;
	}

	/**
	 * Don't forget that float have accuracy problems like :
	 * 0.8f actually is 0.800000011920929
	 * 0.9f actually is 0.8999999761581421
	 *
	 * @param floatingPointProbability
	 * @return
	 */
	public static int convertFromFloat(final float floatingPointProbability)
	{
		return (int) (floatingPointProbability*CONVERTION_FACTOR);
	}

	/**
	 * Don't forget that double have accuracy problems like :
	 * 0.8f actually is 0.800000011920929
	 * 0.9f actually is 0.8999999761581421
	 *
	 * @param floatingPointProbability
	 * @return
	 */
	public static int convertFromDouble(final double floatingPointProbability)
	{
		return (int) (floatingPointProbability*CONVERTION_FACTOR);
	}

	public static int multiplyFixedPrecision(final int p1, final int p2)
	{
		return (int) ((p1*(long)p2)/CONVERTION_FACTOR);
	}

	public static int divideFixedPrecision(final int p1, final int p2)
	{
		return (int) ((p1*(long)CONVERTION_FACTOR/p2));
	}
}
