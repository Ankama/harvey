/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DoubleBound
implements IDoubleBound
{
	private static final double EQUALITY_THREASHOLD = 0.0001d;
	private final double _value;
	private final boolean _isLowerBound;
	private final boolean _isClosed;

	public static DoubleBound makeBound(final boolean isLowerBound)
	{
		return new DoubleBound(isLowerBound);
	}

	public static DoubleBound makeBound(final boolean isLowerBound, final double value)
	{
		return new DoubleBound(isLowerBound, value);
	}

	public static DoubleBound makeBound(final boolean isLowerBound, final boolean isClosed, final double value)
	{
		return new DoubleBound(isLowerBound, isClosed, value);
	}

	protected DoubleBound(final boolean isLowerBound)
	{
		_isLowerBound = isLowerBound;
		_isClosed = false;
		_value = isLowerBound?Double.NEGATIVE_INFINITY:Double.POSITIVE_INFINITY;
	}

	protected DoubleBound(final boolean isLowerBound, final double value)
	{
		_isLowerBound = isLowerBound;
		_value = value;
		_isClosed = !isInfinity();
	}

	protected DoubleBound(final boolean isLowerBound, final boolean isClosed, final double value)
	{
		_isLowerBound = isLowerBound;
		_value = value;
		_isClosed = isClosed && !isInfinity();
	}


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final @Nullable IDoubleBound o)
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
	public double compareToContinuous(final @Nullable IDoubleBound o)
	{
		double ret;
		if(o != null)
			ret = _value - o.getValue();
		else
			return -1;
		if(Math.abs(ret) < EQUALITY_THREASHOLD)
			return 0;
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound#isPreceding(com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound)
	 */
	@Override
	public boolean isPreceding(final IDoubleBound bound)
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
	public boolean isSucceeding(final IDoubleBound bound)
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
	 * @see com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IIDoubleBound#getValue()
	 */
	@Override
	public double getValue()
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
		return _value == Double.POSITIVE_INFINITY || _value == Double.NEGATIVE_INFINITY;
	}

	@Override
	public boolean isClosed()
	{
		return _isClosed && !isInfinity();
	}

}
