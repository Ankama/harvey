/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.comparators.ContinuousByteSplitter;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ICompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseCompositeContinuousByteSet<ChildType extends IContinuousByteSet>
extends	AbstractCompositeContinuousSet
<
	IContinuousByteBound,
	IContinuousByteSet,
	ISimpleContinuousByteSet,
	IElementaryContinuousByteSet,
	ICompositeContinuousByteSet<?>,
	ChildType,
	SortedSet<ChildType>
>
implements ICompositeContinuousByteSet<ChildType>, IContinuousByteSet
{
	protected CompositeContinuousByteSetBridge<ChildType, BaseCompositeContinuousByteSet<ChildType>>	_bridge;
	CommonCompositeContinuousByteSetBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>> _compositeBridge;

	protected AbstractCompositeContinuousBoundBridge<IContinuousByteBound, IContinuousByteSet,ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType,  BaseCompositeContinuousByteSet<ChildType>> _boundBridge;

	protected ContinuousByteSplitter _splitter;

	protected BaseCompositeContinuousByteSet()
	{
		_splitter = ContinuousByteSplitter.getInstance();
		_bridge = new CompositeContinuousByteSetBridge<ChildType, BaseCompositeContinuousByteSet<ChildType>>(this);
		_compositeBridge = new CommonCompositeContinuousByteSetBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>>(this);
	}

	protected BaseCompositeContinuousByteSet(final ChildType element)
	{
		_splitter = ContinuousByteSplitter.getInstance();
		_bridge = new CompositeContinuousByteSetBridge<ChildType, BaseCompositeContinuousByteSet<ChildType>>(this, element);
		_compositeBridge = new CommonCompositeContinuousByteSetBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>>(this);

	}

	protected BaseCompositeContinuousByteSet(final Iterable<ChildType> collection)
	{
		_splitter = ContinuousByteSplitter.getInstance();
		_bridge = new CompositeContinuousByteSetBridge<ChildType, BaseCompositeContinuousByteSet<ChildType>>(this, collection);
		_compositeBridge = new CommonCompositeContinuousByteSetBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>>(this);

	}

	@Override
	protected CompositeContinuousByteSetBridge<ChildType, BaseCompositeContinuousByteSet<ChildType>> getBridge()
	{
		return _bridge;
	}

	@Override
	protected AbstractCompositeContinuousBoundBridge<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, ICompositeContinuousByteSet<?>, ChildType, BaseCompositeContinuousByteSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IContinuousByteSet> split(final byte... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IContinuousByteSet> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IContinuousByteSet> splitOnRange(final IContinuousByteSet set)
	{
		return _bridge.splitOnRange(set);
	}

	@Override
	public @Nullable IContinuousByteBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IContinuousByteBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final byte value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleContinuousByteSet getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeContinuousByteSet<?> getAsComposite()
	{
		return this;
	}

	@Override
	public IContinuousByteSet getAsSet()
	{
		return this;
	}
}