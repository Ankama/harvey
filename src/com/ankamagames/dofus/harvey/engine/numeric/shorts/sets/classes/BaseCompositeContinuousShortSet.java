/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.comparators.ContinuousShortSplitter;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ICompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeContinuousShortSet<ChildType extends IContinuousShortSet>
extends	AbstractCompositeContinuousSet
<
	IContinuousShortBound,
	IContinuousShortSet,
	ISimpleContinuousShortSet,
	IElementaryContinuousShortSet,
	ICompositeContinuousShortSet<?>,
	ChildType,
	SortedSet<ChildType>
>
implements ICompositeContinuousShortSet<ChildType>, IContinuousShortSet
{
	protected CompositeContinuousShortSetBridge<ChildType, BaseCompositeContinuousShortSet<ChildType>>	_bridge;
	CommonCompositeContinuousShortSetBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>> _compositeBridge;

	protected AbstractCompositeContinuousBoundBridge<IContinuousShortBound, IContinuousShortSet,ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType,  BaseCompositeContinuousShortSet<ChildType>> _boundBridge;

	protected ContinuousShortSplitter _splitter;

	protected BaseCompositeContinuousShortSet()
	{
		_splitter = ContinuousShortSplitter.getInstance();
		_bridge = new CompositeContinuousShortSetBridge<ChildType, BaseCompositeContinuousShortSet<ChildType>>(this);
		_compositeBridge = new CommonCompositeContinuousShortSetBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>>(this);
	}

	protected BaseCompositeContinuousShortSet(final ChildType element)
	{
		_splitter = ContinuousShortSplitter.getInstance();
		_bridge = new CompositeContinuousShortSetBridge<ChildType, BaseCompositeContinuousShortSet<ChildType>>(this, element);
		_compositeBridge = new CommonCompositeContinuousShortSetBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>>(this);

	}

	protected BaseCompositeContinuousShortSet(final Iterable<ChildType> collection)
	{
		_splitter = ContinuousShortSplitter.getInstance();
		_bridge = new CompositeContinuousShortSetBridge<ChildType, BaseCompositeContinuousShortSet<ChildType>>(this, collection);
		_compositeBridge = new CommonCompositeContinuousShortSetBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>>(this);

	}

	@Override
	protected CompositeContinuousShortSetBridge<ChildType, BaseCompositeContinuousShortSet<ChildType>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeContinuousBoundBridge<IContinuousShortBound, IContinuousShortSet, ISimpleContinuousShortSet, IElementaryContinuousShortSet, ICompositeContinuousShortSet<?>, ChildType, BaseCompositeContinuousShortSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousShortSet> split(final short... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IContinuousShortSet> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IContinuousShortSet> splitOnRange(final IContinuousShortSet set)
	{
		return _bridge.splitOnRange(set);
	}

	@Override
	public @Nullable IContinuousShortBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IContinuousShortBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final short value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleContinuousShortSet getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeContinuousShortSet<?> getAsComposite()
	{
		return this;
	}

	@Override
	public IContinuousShortSet getAsSet()
	{
		return this;
	}
}