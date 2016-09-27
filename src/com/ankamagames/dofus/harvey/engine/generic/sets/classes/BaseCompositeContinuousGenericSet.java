/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeContinuousGenericSet;
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
public class BaseCompositeContinuousGenericSet<Data, ChildType extends IContinuousGenericSet<Data>>
extends	AbstractCompositeContinuousSet
<
	IContinuousGenericBound<Data>,
	IContinuousGenericSet<Data>,
	ISimpleContinuousGenericSet<Data>,
	IElementaryContinuousGenericSet<Data>,
	ICompositeContinuousGenericSet<Data, ?>,
	ChildType,
	SortedSet<ChildType>
>
implements ICompositeContinuousGenericSet<Data, ChildType>, IContinuousGenericSet<Data>
{
	protected CompositeContinuousGenericSetBridge<Data, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>>	_bridge;
	CommonCompositeContinuousGenericSetBridge<Data, IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data, ?>, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>> _compositeBridge;

	protected AbstractCompositeContinuousBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>,ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data, ?>, ChildType,  BaseCompositeContinuousGenericSet<Data, ChildType>> _boundBridge;

	protected ContinuousComparator<? super Data> _comparator;

	protected Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> _splitter;

	protected BaseCompositeContinuousGenericSet(final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		_comparator = comparator;
		_splitter = splitter;
		_bridge = new CompositeContinuousGenericSetBridge<Data, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>>(this, comparator, splitter);
		_compositeBridge = new CommonCompositeContinuousGenericSetBridge<Data, IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data,?>, ChildType, BaseCompositeContinuousGenericSet<Data,ChildType>>(
				this,
				new Comparator<Data>()
					{
						@Override
						public int compare(@Nullable final Data o1, @Nullable final Data o2)
						{
							return (int)Math.signum(comparator.compare(o1, o2));
						}
						},
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data,?>, ChildType, BaseCompositeContinuousGenericSet<Data,ChildType>>(this);
	}

	protected BaseCompositeContinuousGenericSet(final ChildType element, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		_comparator = comparator;
		_splitter = splitter;
		_bridge = new CompositeContinuousGenericSetBridge<Data, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>>(this, comparator, splitter, element);
		_compositeBridge = new CommonCompositeContinuousGenericSetBridge<Data, IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data,?>, ChildType, BaseCompositeContinuousGenericSet<Data,ChildType>>(
				this,
				new Comparator<Data>()
					{

						@Override
						public int compare(@Nullable final Data o1, @Nullable final Data o2)
						{
							return (int)Math.signum(comparator.compare(o1, o2));
						}
						},
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data,?>, ChildType, BaseCompositeContinuousGenericSet<Data,ChildType>>(this);

	}

	protected BaseCompositeContinuousGenericSet(final Iterable<ChildType> collection, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		_comparator = comparator;
		_splitter = splitter;
		_bridge = new CompositeContinuousGenericSetBridge<Data, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>>(this, comparator, splitter, collection);
		_compositeBridge = new CommonCompositeContinuousGenericSetBridge<Data, IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data,?>, ChildType, BaseCompositeContinuousGenericSet<Data,ChildType>>(
				this,
				new Comparator<Data>()
					{

						@Override
						public int compare(@Nullable final Data o1, @Nullable final Data o2)
						{
							return (int)Math.signum(comparator.compare(o1, o2));
						}
						},
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data,?>, ChildType, BaseCompositeContinuousGenericSet<Data,ChildType>>(this);

	}

	@Override
	protected CompositeContinuousGenericSetBridge<Data, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeContinuousBoundBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, ICompositeContinuousGenericSet<Data, ?>, ChildType, BaseCompositeContinuousGenericSet<Data, ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> split(final Data... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IContinuousGenericSet<Data>> splitOnRange(final IContinuousGenericSet<Data> set)
	{
		return _bridge.splitOnRange(set);
	}

	public ContinuousComparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public @Nullable IContinuousGenericBound<Data> getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IContinuousGenericBound<Data> getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final @Nullable Data value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleContinuousGenericSet<Data> getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeContinuousGenericSet<Data, ?> getAsComposite()
	{
		return this;
	}

	@Override
	public IContinuousGenericSet<Data> getAsSet()
	{
		return this;
	}
}