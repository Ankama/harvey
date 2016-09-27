/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Arrays;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractIntervalBoundBridge;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultContinuousComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.ContinuousGenericBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.ContinuousGenericIntervalBridge;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class ContinuousGenericInterval<Data>
extends AbstractContinuousInterval<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>>
implements IContinuousGenericInterval<Data>
{
	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound,
		@Nullable final Data upperBound,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBound, comparator), ContinuousGenericBound.makeBound(false, upperBound, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, @Nullable final Data upperBound,
		final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBound, comparator), ContinuousGenericBound.makeBound(false, upperBound, comparator), comparator, splitter);
	}

	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound,
		final boolean lowerBoundClosed, @Nullable final Data upperBound, final boolean upperBoundClosed, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBoundClosed, lowerBound, comparator), ContinuousGenericBound.makeBound(false, upperBoundClosed, upperBound, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeInterval(@Nullable final Data lowerBound, final boolean lowerBoundClosed, @Nullable final Data upperBound,
		final boolean upperBoundClosed, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBoundClosed, lowerBound, comparator), ContinuousGenericBound.makeBound(false, upperBoundClosed, upperBound, comparator), comparator, splitter);
	}

	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeLeftBoundedInterval(@Nullable final Data lowerBound,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBound, comparator), ContinuousGenericBound.makeBound(false, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeLeftBoundedInterval(@Nullable final Data lowerBound, final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBound, comparator), ContinuousGenericBound.makeBound(false, comparator), comparator, splitter);
	}

	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeLeftBoundedInterval(@Nullable final Data lowerBound, final boolean lowerBoundClosed,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBoundClosed, lowerBound, comparator), ContinuousGenericBound.makeBound(false, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeLeftBoundedInterval(@Nullable final Data lowerBound, final boolean lowerBoundClosed, final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, lowerBoundClosed, lowerBound, comparator), ContinuousGenericBound.makeBound(false, comparator), comparator, splitter);
	}

	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeRightBoundedInterval(@Nullable final Data upperBound,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, comparator), ContinuousGenericBound.makeBound(false, upperBound, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeRightBoundedInterval(@Nullable final Data upperBound, final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, comparator), ContinuousGenericBound.makeBound(false, upperBound, comparator), comparator, splitter);
	}

	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeRightBoundedInterval(@Nullable final Data upperBound, final boolean upperBoundClosed,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, comparator), ContinuousGenericBound.makeBound(false, upperBoundClosed, upperBound, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeRightBoundedInterval(@Nullable final Data upperBound, final boolean upperBoundClosed, final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, comparator), ContinuousGenericBound.makeBound(false, upperBoundClosed, upperBound, comparator), comparator, splitter);
	}

	static public <Data extends ContinuousComparable<? super Data>> ContinuousGenericInterval<Data> makeUniverse(
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<? super Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, comparator), ContinuousGenericBound.makeBound(false, comparator), comparator, splitter);
	}

	static public <Data> ContinuousGenericInterval<Data> makeUniverse(final ContinuousComparator<? super Data> comparator,
		final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new ContinuousGenericInterval<Data>(ContinuousGenericBound.makeBound(true, comparator), ContinuousGenericBound.makeBound(false, comparator), comparator, splitter);
	}

	protected ContinuousGenericIntervalBridge<Data, ContinuousGenericInterval<Data>> _bridge;
	protected AbstractIntervalBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, ContinuousGenericInterval<Data>> _boundBridge;
	protected ContinuousComparator<? super Data> _comparator;
	protected IContinuousGenericBound<Data>																														_lowerBound;
	protected IContinuousGenericBound<Data>																														_upperBound;

	private ContinuousGenericInterval(final IContinuousGenericBound<Data> lowerBound, final IContinuousGenericBound<Data> upperBound,
		final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_comparator = comparator;
		_bridge = new ContinuousGenericIntervalBridge<Data, ContinuousGenericInterval<Data>>(this, comparator, splitter);
		_boundBridge = new AbstractIntervalBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, ContinuousGenericInterval<Data>>(this);
	}

	@Override
	protected ContinuousGenericIntervalBridge<Data, ContinuousGenericInterval<Data>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractIntervalBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, ? extends AbstractInterval<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public IContinuousGenericSet<Data> getAsSet()
	{
		return this;
	}

	@Override
	public ISimpleContinuousGenericSet<Data> getAsSimpleSet()
	{
		return this;
	}

	@Override
	public IElementaryContinuousGenericSet<Data> getAsElementarySet()
	{
		return this;
	}

	@Override
	public IContinuousGenericInterval<Data> getAsInterval()
	{
		return this;
	}

	@Override
	public boolean isLowerBoundInfinity()
	{
		return _lowerBound.isInfinity();
	}

	@Override
	public boolean isUpperBoundInfinity()
	{
		return _upperBound.isInfinity();
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		final IContinuousGenericBound<Data> lowerBound = getLowerBound();
		if (lowerBound != null)
			return _lowerBound.isClosed();
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		final IContinuousGenericBound<Data> upperBound = getUpperBound();
		if (upperBound != null)
			return _upperBound.isClosed();
		return false;
	}

	@Override
	public boolean isPreceding(final IContinuousGenericSet<Data> set)
	{
		if(isUpperBoundInfinity() || set.isLowerBoundInfinity())
			return false;
		final IContinuousGenericBound<Data> upperBound = getUpperBound();
		final IContinuousGenericBound<Data> otherLowerBound = set.getLowerBound();
		if(upperBound == null || otherLowerBound == null)
			return false;
		final Data upperBoundValue = upperBound.getValue();
		if(upperBoundValue!=null)
		{
			return upperBoundValue.equals(otherLowerBound.getValue()) && isUpperBoundClosed()!=set.isLowerBoundClosed();
		}
		return otherLowerBound.getValue() == null && isUpperBoundClosed()!=set.isLowerBoundClosed();
	}

	@Override
	public boolean isSucceeding(final IContinuousGenericSet<Data> set)
	{
		if(isLowerBoundInfinity() || set.isUpperBoundInfinity())
			return false;
		if(isEmpty()||set.isEmpty())
			return false;
		final IContinuousGenericBound<Data> lowerBound = getLowerBound();
		final IContinuousGenericBound<Data> otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final Data lowerBoundValue = lowerBound.getValue();
		if(lowerBoundValue!=null)
		{
			return lowerBoundValue.equals(otherUpperBound.getValue()) && isLowerBoundClosed()!=set.isUpperBoundClosed();
		}
		return otherUpperBound.getValue() == null && isLowerBoundClosed()!=set.isUpperBoundClosed();
	}

	@Override
	public boolean isEmpty()
	{
		if(isLowerBoundInfinity() || isUpperBoundInfinity()) // case where the interval is Universe/LeftBounded/RightBounded
			return false;
		return super.isEmpty();
	}

	@Override
	public double size()
	{
		final IContinuousGenericBound<Data> lowerBound = getLowerBound();
		final IContinuousGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return 0;
		if(isLowerBoundInfinity() || isUpperBoundInfinity())
			return Double.POSITIVE_INFINITY;
		return _comparator.compareContinuous(upperBound.getValue(), lowerBound.getValue());
	}

	@Override
	public @Nullable IContinuousGenericBound<Data> getLowerBound()
	{
		final IContinuousGenericBound<Data> lowerBound = _lowerBound;
		final IContinuousGenericBound<Data> upperBound = _upperBound;

		if(isLowerBoundInfinity() || isUpperBoundInfinity())
			return lowerBound;
		final double compare = _comparator.compareContinuous(lowerBound.getValue(), upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return lowerBound;
		return null;
	}

	@Override
	public @Nullable IContinuousGenericBound<Data> getUpperBound()
	{
		final IContinuousGenericBound<Data> lowerBound = _lowerBound;
		final IContinuousGenericBound<Data> upperBound = _upperBound;

		if((isUpperBoundInfinity() || isLowerBoundInfinity()))
			return upperBound;
		final double compare = _comparator.compareContinuous(lowerBound.getValue(), upperBound.getValue());
		if((compare<0) || (compare == 0 && lowerBound.isClosed() && upperBound.isClosed()))
			return upperBound;
		return null;
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		final IContinuousGenericBound<Data> lowerBound = getLowerBound();
		final IContinuousGenericBound<Data> upperBound = getUpperBound();
		if(lowerBound == null || upperBound == null)
			return false;
		if(!isLowerBoundInfinity())
			if(!isUpperBoundInfinity())
			{
				final double compareLB = _comparator.compareContinuous(lowerBound.getValue(), value);
				final double compareUB = _comparator.compareContinuous(upperBound.getValue(), value);
				return (compareLB<0 || (isLowerBoundClosed() && compareLB == 0)) &&
						(compareUB>0 || (isUpperBoundClosed() && compareUB==0));
			}
			else
			{
				final double compare = _comparator.compareContinuous(lowerBound.getValue(), value);
				return compare < 0 || (isLowerBoundClosed() && compare == 0);
			}
		if(!isUpperBoundInfinity())
		{
			final double compare = _comparator.compareContinuous(upperBound.getValue(), value);
			return compare > 0 || (isUpperBoundClosed() && compare == 0);
		}
		return true;
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return getBridge().split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data... values)
	{
		final boolean[] inNextInterval = new boolean[values.length];
		Arrays.fill(inNextInterval, true);
		return split(values, inNextInterval);
	}

	@Override
	public IContinuousGenericInterval<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public String toString()
	{
		final IContinuousGenericBound<Data> lowerBound = getLowerBound();
		final IContinuousGenericBound<Data> upperBound = getUpperBound();
		if (lowerBound == null || upperBound == null)
			return "∅";
		if(!isLowerBoundInfinity())
			if(!isUpperBoundInfinity())
			{
				if(isDegenerate())
					return "["+lowerBound.getValue()+"]";
				return (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
			}
			else
				return  (isLowerBoundClosed()?"[":"]")+lowerBound.getValue()+";+∞[";
		if(!isUpperBoundInfinity())
			return "]-∞;"+upperBound.getValue()+(isUpperBoundClosed()?"]":"[");
		return "Ω";
	}
}