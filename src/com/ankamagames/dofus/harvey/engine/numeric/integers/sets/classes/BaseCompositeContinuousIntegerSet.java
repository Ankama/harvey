/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.comparators.ContinuousIntegerSplitter;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ICompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeContinuousIntegerSet<ChildType extends IContinuousIntegerSet>
extends	AbstractCompositeContinuousSet
<
	IContinuousIntegerBound,
	IContinuousIntegerSet,
	ISimpleContinuousIntegerSet,
	IElementaryContinuousIntegerSet,
	ICompositeContinuousIntegerSet<?>,
	ChildType,
	SortedSet<ChildType>
>
implements ICompositeContinuousIntegerSet<ChildType>, IContinuousIntegerSet
{
	protected CompositeContinuousIntegerSetBridge<ChildType, BaseCompositeContinuousIntegerSet<ChildType>>	_bridge;
	CommonCompositeContinuousIntegerSetBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>> _compositeBridge;

	protected AbstractCompositeContinuousBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet,ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType,  BaseCompositeContinuousIntegerSet<ChildType>> _boundBridge;

	protected ContinuousIntegerSplitter _splitter;

	protected BaseCompositeContinuousIntegerSet()
	{
		_splitter = ContinuousIntegerSplitter.getInstance();
		_bridge = new CompositeContinuousIntegerSetBridge<ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(this);
		_compositeBridge = new CommonCompositeContinuousIntegerSetBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(this);
	}

	protected BaseCompositeContinuousIntegerSet(final ChildType element)
	{
		_splitter = ContinuousIntegerSplitter.getInstance();
		_bridge = new CompositeContinuousIntegerSetBridge<ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(this, element);
		_compositeBridge = new CommonCompositeContinuousIntegerSetBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(this);

	}

	protected BaseCompositeContinuousIntegerSet(final Iterable<ChildType> collection)
	{
		_splitter = ContinuousIntegerSplitter.getInstance();
		_bridge = new CompositeContinuousIntegerSetBridge<ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(this, collection);
		_compositeBridge = new CommonCompositeContinuousIntegerSetBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>>(this);

	}

	@Override
	protected CompositeContinuousIntegerSetBridge<ChildType, BaseCompositeContinuousIntegerSet<ChildType>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeContinuousBoundBridge<IContinuousIntegerBound, IContinuousIntegerSet, ISimpleContinuousIntegerSet, IElementaryContinuousIntegerSet, ICompositeContinuousIntegerSet<?>, ChildType, BaseCompositeContinuousIntegerSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousIntegerSet> split(final int... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IContinuousIntegerSet> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IContinuousIntegerSet> splitOnRange(final IContinuousIntegerSet set)
	{
		return _bridge.splitOnRange(set);
	}

	@Override
	public @Nullable IContinuousIntegerBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IContinuousIntegerBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final int value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleContinuousIntegerSet getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeContinuousIntegerSet<?> getAsComposite()
	{
		return this;
	}

	@Override
	public IContinuousIntegerSet getAsSet()
	{
		return this;
	}
}