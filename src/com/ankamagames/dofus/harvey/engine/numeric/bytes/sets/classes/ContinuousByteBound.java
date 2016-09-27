/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousByteBound
extends AbstractByteBound<IContinuousByteBound>
implements IContinuousByteBound
{

	public static  ContinuousByteBound makeBound(final boolean isLowerBound, final byte value)
	{
		return new ContinuousByteBound(isLowerBound, value);
	}

	public static  ContinuousByteBound makeBound(final boolean isLowerBound, final boolean isClosed, final byte value)
	{
		return new ContinuousByteBound(isLowerBound, isClosed, value);
	}

	protected boolean _isLowerBound;
	protected boolean _isClosed;


	protected ContinuousByteBound(final boolean isLowerBound, final byte value)
	{
		super(value);
		_isLowerBound = isLowerBound;
		_isClosed = true;
	}

	protected ContinuousByteBound(final boolean isLowerBound, final boolean isClosed, final byte value)
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
	public byte getValue()
	{
		return _value;
	}

	@Override
	public boolean isLowerBound()
	{
		return _isLowerBound;
	}

	@Override
	public boolean isPreceding(final IContinuousByteBound bound)
	{
		final byte value = bound.getValue();
		return (!isLowerBound() && bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public boolean isSucceeding(final IContinuousByteBound bound)
	{
		final byte value = bound.getValue();
		return (isLowerBound() && !bound.isLowerBound() && isClosed() != bound.isClosed() && value == (getValue()));
	}

	@Override
	public int compareTo(@Nullable final IContinuousByteBound otherBound)
	{
		return (int) compareToContinuous(otherBound);
	}

	@Override
	public double compareToContinuous(@Nullable final IContinuousByteBound  o)
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
		final String ret = ((Byte)_value).toString();
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