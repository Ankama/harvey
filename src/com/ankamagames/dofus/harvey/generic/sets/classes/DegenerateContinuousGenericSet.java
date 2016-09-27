/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.classes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparable;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultContinuousComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.CommonDegenerateContinuousGenericSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.ContinuousGenericBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.DegenerateContinuousGenericSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBoundFactory;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class DegenerateContinuousGenericSet<Data>
extends AbstractDegenerateContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>>
implements IDegenerateContinuousGenericSet<Data>
{
	protected ContinuousComparator<? super Data> _comparator;
	protected DegenerateContinuousGenericSetBridge<Data, DegenerateContinuousGenericSet<Data>> _bridge;
	protected AbstractDegenerateBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>, DegenerateContinuousGenericSet<Data>> _boundBridge;
	CommonDegenerateContinuousGenericSetBridge<Data, IContinuousGenericBound<Data>, IContinuousGenericSet<Data>> _degenerateBridge;

	public static <Data extends ContinuousComparable<? super Data>> DegenerateContinuousGenericSet<Data> makeSet(@Nullable final Data value, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		final ContinuousComparator<Data> comparator = DefaultContinuousComparableComparator.getInstance();
		return makeSet(value, comparator, splitter);
	}

	public static <Data> DegenerateContinuousGenericSet<Data> makeSet(@Nullable final Data value, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		return new DegenerateContinuousGenericSet<Data>(value, comparator, splitter);
	}

	private DegenerateContinuousGenericSet(@Nullable final Data value, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		_comparator = comparator;
		_bridge = new DegenerateContinuousGenericSetBridge<Data, DegenerateContinuousGenericSet<Data>>(this,  comparator, splitter);
		final EmptyContinuousGenericSet<Data> empty = EmptyContinuousGenericSet.getInstance();
		_degenerateBridge = new CommonDegenerateContinuousGenericSetBridge<Data, IContinuousGenericBound<Data>, IContinuousGenericSet<Data>>(
				value,
				new IIGenericBoundFactory<Data, IContinuousGenericBound<Data>>()
				{
					@Override
					public ContinuousGenericBound<Data> makeBound(final boolean isLowerBound, final @Nullable Data value)
					{
						return ContinuousGenericBound.makeBound(isLowerBound, value, comparator);
					}
				},
				new Comparator<Data>()
					{
						@Override
						public int compare(final @Nullable Data o1, final @Nullable Data o2)
						{
							return (int)Math.signum(comparator.compareContinuous(o1, o2));
						}},
				empty,
				this);
		_boundBridge = new AbstractDegenerateBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>, DegenerateContinuousGenericSet<Data>>(this);
	}

	@Override
	protected DegenerateContinuousGenericSetBridge<Data, DegenerateContinuousGenericSet<Data>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractDegenerateBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>, ? extends AbstractDegenerateSortedSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>>> getBoundBridge()
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
	public IDegenerateContinuousGenericSet<Data> getAsDegenerateSet()
	{
		return this;
	}

	@Override
	@Nullable
	public Data getValue()
	{
		return _degenerateBridge.getValue();
	}

	@Override
	public @NonNull IContinuousGenericBound<Data> getLowerBound()
	{
		return _degenerateBridge.getLowerBound();
	}

	@Override
	public @NonNull IContinuousGenericBound<Data> getUpperBound()
	{
		return _degenerateBridge.getUpperBound();
	}

	@Override
	public DegenerateContinuousGenericSet<Data> getSimpleSet()
	{
		return this;
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return _degenerateBridge.contains(value);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data[] values, final boolean[] isIntervalStart)
	{
		return _degenerateBridge.split(values, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data... values)
	{
		return _degenerateBridge.split(values);
	}

	@Override
	public boolean isPreceding(final IContinuousGenericSet<Data> set)
	{
		final IContinuousGenericBound<Data> upperBound = getUpperBound();
		final IContinuousGenericBound<Data> otherLowerBound = set.getLowerBound();
		if((upperBound == null) || (otherLowerBound == null))
			return false;
		final @Nullable Data upperBoundValue = upperBound.getValue();
		if(upperBoundValue != null)
			return upperBoundValue.equals(otherLowerBound.getValue()) && (set.isLowerBoundClosed()!=isUpperBoundClosed());
		return (otherLowerBound.getValue()==null) && (set.isLowerBoundClosed()!=isUpperBoundClosed());
	}

	@Override
	public boolean isSucceeding(final IContinuousGenericSet<Data> set)
	{
		final IContinuousGenericBound<Data> lowerBound = getLowerBound();
		final IContinuousGenericBound<Data> otherUpperBound = set.getUpperBound();
		if(lowerBound == null || otherUpperBound == null)
			return false;
		final @Nullable Data lowerBoundValue = lowerBound.getValue();
		if(lowerBoundValue != null)
			return lowerBoundValue.equals(otherUpperBound.getValue()) && (set.isUpperBoundClosed()!=isLowerBoundClosed());
		return (otherUpperBound.getValue()==null) && (set.isUpperBoundClosed()!=isLowerBoundClosed());
	}

	@Override
	public String toString()
	{
		return _degenerateBridge.toString();
	}

	@Override
	public Iterator<IContinuousGenericBound<Data>> getBoundIterator()
	{
		return new TwoValueIterator<IContinuousGenericBound<Data>>(getLowerBound(), getUpperBound());
	}
}