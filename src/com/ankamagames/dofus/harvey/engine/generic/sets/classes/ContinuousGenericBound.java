/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import com.ankamagames.dofus.harvey.engine.exceptions.OutOfBoundsException;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultContinuousComparableComparator;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousGenericBound<Data>
extends AbstractGenericBound<Data, IContinuousGenericBound<Data>>
implements IContinuousGenericBound<Data>
{
	public static <Data extends ContinuousComparable<Data>> ContinuousGenericBound<Data> makeBound(final boolean isLowerBound)
	{
		final DefaultContinuousComparableComparator<Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericBound<Data>(isLowerBound, comparator);
	}

	public static <Data> ContinuousGenericBound<Data> makeBound(final boolean isLowerBound, final ContinuousComparator<? super Data> comparator)
	{
		return new ContinuousGenericBound<Data>(isLowerBound, comparator);
	}

	public static <Data extends ContinuousComparable<Data>> ContinuousGenericBound<Data> makeBound(final boolean isLowerBound, final @Nullable Data value)
	{
		final DefaultContinuousComparableComparator<Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericBound<Data>(isLowerBound, value, comparator);
	}

	public static <Data> ContinuousGenericBound<Data> makeBound(final boolean isLowerBound, final @Nullable Data value, final ContinuousComparator<? super Data> comparator)
	{
		return new ContinuousGenericBound<Data>(isLowerBound, value, comparator);
	}

	public static <Data extends ContinuousComparable<Data>> ContinuousGenericBound<Data> makeBound(final boolean isLowerBound, final boolean isClosed, final @Nullable Data value)
	{
		final DefaultContinuousComparableComparator<Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericBound<Data>(isLowerBound, isClosed, value, comparator);
	}

	public static <Data> ContinuousGenericBound<Data> makeBound(final boolean isLowerBound, final boolean isClosed, final @Nullable Data value, final ContinuousComparator<? super Data> comparator)
	{
		return new ContinuousGenericBound<Data>(isLowerBound, isClosed, value, comparator);
	}

	protected ContinuousComparator<? super Data> _comparator;
	protected boolean _isLowerBound;
	protected boolean _isInfinity;
	protected boolean _isClosed;

	protected ContinuousGenericBound(final boolean isLowerBound, final ContinuousComparator<? super Data> comparator)
	{
		super(null);
		_comparator = comparator;
		_isLowerBound = isLowerBound;
		_isInfinity = true;
		_isClosed = false;
	}

	protected ContinuousGenericBound(final boolean isLowerBound, @Nullable final Data value, final ContinuousComparator<? super Data> comparator)
	{
		super(value);
		_comparator = comparator;
		_isLowerBound = isLowerBound;
		_isInfinity = false;
		_isClosed = true;
	}

	protected ContinuousGenericBound(final boolean isLowerBound, final boolean isClosed, @Nullable final Data value, final ContinuousComparator<? super Data> comparator)
	{
		super(value);
		_comparator = comparator;
		_isLowerBound = isLowerBound;
		_isInfinity = false;
		_isClosed = isClosed;
	}

	@Override
	protected ContinuousComparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public boolean isInfinity()
	{
		return _isInfinity;
	}

	@Override
	public boolean isClosed()
	{
		if(_isInfinity)
			return false;
		return _isClosed;
	}

	@Override
	public @Nullable Data getValue()
	{
		if(_isInfinity)
			throw new OutOfBoundsException();
		return _value;
	}

	@Override
	public boolean isLowerBound()
	{
		return _isLowerBound;
	}

	@Override
	public boolean isPreceding(final IContinuousGenericBound<Data> bound)
	{
		if(isInfinity() || bound.isInfinity())
			return false;
		final Data value = bound.getValue();
		if (value != null)
			return (!isLowerBound() && bound.isLowerBound() && isClosed() != bound.isClosed() && value.equals(getValue()));
		return (!isLowerBound() && bound.isLowerBound() && isClosed() != bound.isClosed() && getValue() == null);
	}

	@Override
	public boolean isSucceeding(final IContinuousGenericBound<Data> bound)
	{
		if(isInfinity() || bound.isInfinity())
			return false;
		final Data value = bound.getValue();
		if (value != null)
			return (isLowerBound() && !bound.isLowerBound() && isClosed() != bound.isClosed() && value.equals(getValue()));
		return (isLowerBound() && !bound.isLowerBound() && isClosed() != bound.isClosed() && getValue() == null);
	}

	@Override
	public double compareToContinuous(@Nullable final IContinuousGenericBound<Data> otherBound)
	{
		return getComparator().compareContinuous(this.getValue(), (otherBound != null)?otherBound.getValue():null);
	}

	@Override
	public int compareTo(@Nullable final IContinuousGenericBound<Data>  o)
	{
		final int compare = getComparator().compare(_value, (o!=null)?o.getValue():null);
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
	public String toString()
	{
		if(_value != null)
		{
			final String ret = _value.toString();
			if(isLowerBound())
			{
				if(isInfinity())
					return "]-∞";
				if(isClosed())
					return "["+ret;
				else
					return "]"+ret;
			}
			else
			{
				if(isInfinity())
					return "+∞[";
				if(isClosed())
					return ret+"]";
				else
					return ret+"[";
			}
		}
		return "null";
	}
}