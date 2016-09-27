/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousIntegerBound
extends AbstractIntegerBound<IContinuousIntegerBound>
implements IContinuousIntegerBound
{

	public static  ContinuousIntegerBound makeBound(final boolean isLowerBound, final int value)
	{
		return new ContinuousIntegerBound(isLowerBound, value);
	}

	public static  ContinuousIntegerBound makeBound(final boolean isLowerBound, final boolean isClosed, final int value)
	{
		return new ContinuousIntegerBound(isLowerBound, isClosed, value);
	}

	protected boolean _isLowerBound;
	protected boolean _isClosed;


	protected ContinuousIntegerBound(final boolean isLowerBound, final int value)
	{
		super(value);
		_isLowerBound = isLowerBound;
		_isClosed = true;
	}

	protected ContinuousIntegerBound(final boolean isLowerBound, final boolean isClosed, final int value)
	{
		super(value);
		_isLowerBound = isLowerBound;
		_isClosed = isClosed;
	}

	@Override
	public boolean isClosed()
	{
		return _isClosed;
	}

	@Override
	public int getValue()
	{
		return _value;
	}

	@Override
	public boolean isLowerBound()
	{
		return _isLowerBound;
	}

	@Override
	public boolean isPreceding(final IContinuousIntegerBound bound)
	{
		final int value = bound.getValue();
		return (!isLowerBound() && bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public boolean isSucceeding(final IContinuousIntegerBound bound)
	{
		final int value = bound.getValue();
		return (isLowerBound() && !bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public int compareTo(@Nullable final IContinuousIntegerBound otherBound)
	{
		return (int) compareToContinuous(otherBound);
	}

	@Override
	public double compareToContinuous(@Nullable final IContinuousIntegerBound  o)
	{
		if(o == null)
			return 1;
		final double compare = (_value - o.getValue());
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
	public String toString()
	{
		final String ret = ((Integer)_value).toString();
		if(isLowerBound())
		{
			if(isClosed())
				return "["+ret;
			else
				return "]"+ret;
		}
		else
		{
			if(isClosed())
				return ret+"]";
			else
				return ret+"[";
		}
	}

	@Override
	public boolean isInfinity()
	{
		return false;
	}
}