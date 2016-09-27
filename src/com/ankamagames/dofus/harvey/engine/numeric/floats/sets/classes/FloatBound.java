/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class FloatBound
implements IFloatBound
{
	private final float _value;
	private final boolean _isLowerBound;
	private final boolean _isClosed;

	public static FloatBound makeBound(final boolean isLowerBound)
	{
		return new FloatBound(isLowerBound);
	}

	public static FloatBound makeBound(final boolean isLowerBound, final float value)
	{
		return new FloatBound(isLowerBound, value);
	}

	public static FloatBound makeBound(final boolean isLowerBound, final boolean isClosed, final float value)
	{
		return new FloatBound(isLowerBound, isClosed, value);
	}

	protected FloatBound(final boolean isLowerBound)
	{
		_isLowerBound = isLowerBound;
		_isClosed = false;
		_value = isLowerBound?Float.NEGATIVE_INFINITY:Float.POSITIVE_INFINITY;
	}

	protected FloatBound(final boolean isLowerBound, final float value)
	{
		_isLowerBound = isLowerBound;
		_value = value;
		_isClosed = !isInfinity();
	}

	protected FloatBound(final boolean isLowerBound, final boolean isClosed, final float value)
	{
		_isLowerBound = isLowerBound;
		_value = value;
		_isClosed = isClosed && !isInfinity();
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final @Nullable IFloatBound o)
	{
		final double compareToContinuous = compareToContinuous(o);
		int compare;
		if(compareToContinuous>0)
			compare = 1;
		else if(compareToContinuous < 0)
			compare =  -1;
		else
			compare = 0;

		if(o == null)
			return 1;
		if(compare == 0)
		{
			// (!isClosed() && !isLowerBound()) --> X-
			// (!isClosed() && isLowerBound()) --> X+
			// (isClosed() && !isLowerBound()) --> X
			// (isClosed() && isLowerBound()) --> X
			if(isClosed() == o.isClosed() && isLowerBound() == o.isLowerBound()) // this == o
				return 0;
			if(!o.isClosed() && o.isLowerBound()) // o --> X+
				return -1;
			if(!o.isClosed() && !o.isLowerBound()) // o --> X-
				return 1;
			if(!isClosed() && isLowerBound()) // this --> X+
				return 1;
			if(!isClosed() && !isLowerBound()) // this --> X-
				return -1;
			return 0;
		}
		return compare;
	}

	@Override
	public double compareToContinuous(final @Nullable IFloatBound o) {
		if(o != null)
			return _value - o.getValue();
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound#isPreceding(com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound)
	 */
	@Override
	public boolean isPreceding(final IFloatBound bound)
	{
		return (
				!(isInfinity() || bound.isInfinity()) &&
				(!isLowerBound() &&
						bound.isLowerBound() &&
						isClosed() != bound.isClosed() &&
						bound.getValue() == getValue()
						)
				);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound#isSucceeding(com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound)
	 */
	@Override
	public boolean isSucceeding(final IFloatBound bound)
	{
		return (
				!(isInfinity() || bound.isInfinity()) &&
				(isLowerBound() &&
						!bound.isLowerBound() &&
						isClosed() != bound.isClosed() &&
						bound.getValue() == getValue()
						)
				);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IIFloatBound#getValue()
	 */
	@Override
	public float getValue()
	{
		return _value;
	}

	@Override
	public boolean isLowerBound()
	{
		return _isLowerBound;
	}

	@Override
	public boolean isInfinity()
	{
		return _value == Float.POSITIVE_INFINITY || _value == Float.NEGATIVE_INFINITY;
	}

	@Override
	public boolean isClosed()
	{
		return _isClosed && !isInfinity();
	}

	@Override
	public String toString()
	{
		String ret = "";
		if(isLowerBound())
		{
			if(isClosed())
				ret += "[";
			else
				ret += "]";
			ret += getValue();
		}
		else
		{
			if(isClosed())
				ret += getValue()+"]";
			else
				ret += getValue()+"[";
		}
		return ret;
	}

}
