/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousShortBound
extends AbstractShortBound<IContinuousShortBound>
implements IContinuousShortBound
{

	public static  ContinuousShortBound makeBound(final boolean isLowerBound, final short value)
	{
		return new ContinuousShortBound(isLowerBound, value);
	}

	public static  ContinuousShortBound makeBound(final boolean isLowerBound, final boolean isClosed, final short value)
	{
		return new ContinuousShortBound(isLowerBound, isClosed, value);
	}

	protected boolean _isLowerBound;
	protected boolean _isClosed;


	protected ContinuousShortBound(final boolean isLowerBound, final short value)
	{
		super(value);
		_isLowerBound = isLowerBound;
		_isClosed = true;
	}

	protected ContinuousShortBound(final boolean isLowerBound, final boolean isClosed, final short value)
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
	public short getValue()
	{
		return _value;
	}

	@Override
	public boolean isLowerBound()
	{
		return _isLowerBound;
	}

	@Override
	public boolean isPreceding(final IContinuousShortBound bound)
	{
		final short value = bound.getValue();
		return (!isLowerBound() && bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public boolean isSucceeding(final IContinuousShortBound bound)
	{
		final short value = bound.getValue();
		return (isLowerBound() && !bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public double compareToContinuous(@Nullable final IContinuousShortBound otherBound)
	{
		return compareTo(otherBound);
	}

	@Override
	public int compareTo(@Nullable final IContinuousShortBound  o)
	{
		if(o == null)
			return 1;
		final int compare = (_value - o.getValue());
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
		final String ret = ((Short)_value).toString();
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