/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousLongBound
extends AbstractLongBound<IContinuousLongBound>
implements IContinuousLongBound
{

	public static  ContinuousLongBound makeBound(final boolean isLowerBound, final long value)
	{
		return new ContinuousLongBound(isLowerBound, value);
	}

	public static  ContinuousLongBound makeBound(final boolean isLowerBound, final boolean isClosed, final long value)
	{
		return new ContinuousLongBound(isLowerBound, isClosed, value);
	}

	protected boolean _isLowerBound;
	protected boolean _isClosed;


	protected ContinuousLongBound(final boolean isLowerBound, final long value)
	{
		super(value);
		_isLowerBound = isLowerBound;
		_isClosed = true;
	}

	protected ContinuousLongBound(final boolean isLowerBound, final boolean isClosed, final long value)
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
	public long getValue()
	{
		return _value;
	}

	@Override
	public boolean isLowerBound()
	{
		return _isLowerBound;
	}

	@Override
	public boolean isPreceding(final IContinuousLongBound bound)
	{
		final long value = bound.getValue();
		return (!isLowerBound() && bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public boolean isSucceeding(final IContinuousLongBound bound)
	{
		final long value = bound.getValue();
		return (isLowerBound() && !bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public double compareToContinuous(@Nullable final IContinuousLongBound o)
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
	public int compareTo(@Nullable final IContinuousLongBound  o)
	{
		return (int) compareToContinuous(o);
	}

	@Override
	public String toString()
	{
		final String ret = ((Long)_value).toString();
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