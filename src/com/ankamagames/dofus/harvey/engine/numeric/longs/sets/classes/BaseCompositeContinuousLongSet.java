/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.comparators.ContinuousLongSplitter;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ICompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeContinuousLongSet<ChildType extends IContinuousLongSet>
extends	AbstractCompositeContinuousSet
<
	IContinuousLongBound,
	IContinuousLongSet,
	ISimpleContinuousLongSet,
	IElementaryContinuousLongSet,
	ICompositeContinuousLongSet<?>,
	ChildType,
	SortedSet<ChildType>
>
implements ICompositeContinuousLongSet<ChildType>, IContinuousLongSet
{
	protected CompositeContinuousLongSetBridge<ChildType, BaseCompositeContinuousLongSet<ChildType>>	_bridge;
	CommonCompositeContinuousLongSetBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>> _compositeBridge;

	protected AbstractCompositeContinuousBoundBridge<IContinuousLongBound, IContinuousLongSet,ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType,  BaseCompositeContinuousLongSet<ChildType>> _boundBridge;

	protected ContinuousLongSplitter _splitter;

	protected BaseCompositeContinuousLongSet()
	{
		_splitter = ContinuousLongSplitter.getInstance();
		_bridge = new CompositeContinuousLongSetBridge<ChildType, BaseCompositeContinuousLongSet<ChildType>>(this);
		_compositeBridge = new CommonCompositeContinuousLongSetBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>>(this);
	}

	protected BaseCompositeContinuousLongSet(final ChildType element)
	{
		_splitter = ContinuousLongSplitter.getInstance();
		_bridge = new CompositeContinuousLongSetBridge<ChildType, BaseCompositeContinuousLongSet<ChildType>>(this, element);
		_compositeBridge = new CommonCompositeContinuousLongSetBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>>(this);

	}

	protected BaseCompositeContinuousLongSet(final Iterable<ChildType> collection)
	{
		_splitter = ContinuousLongSplitter.getInstance();
		_bridge = new CompositeContinuousLongSetBridge<ChildType, BaseCompositeContinuousLongSet<ChildType>>(this, collection);
		_compositeBridge = new CommonCompositeContinuousLongSetBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>>(this);

	}

	@Override
	protected CompositeContinuousLongSetBridge<ChildType, BaseCompositeContinuousLongSet<ChildType>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeContinuousBoundBridge<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, ICompositeContinuousLongSet<?>, ChildType, BaseCompositeContinuousLongSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousLongSet> split(final long... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IContinuousLongSet> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IContinuousLongSet> splitOnRange(final IContinuousLongSet set)
	{
		return _bridge.splitOnRange(set);
	}

	@Override
	public @Nullable IContinuousLongBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IContinuousLongBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final long value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleContinuousLongSet getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeContinuousLongSet<?> getAsComposite()
	{
		return this;
	}

	@Override
	public IContinuousLongSet getAsSet()
	{
		return this;
	}
}