/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ICompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class BaseCompositeFloatSet<ChildType extends IFloatSet>
extends	AbstractCompositeContinuousSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, SortedSet<ChildType>>
implements ICompositeFloatSet<ChildType>, IFloatSet
{

	protected CompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>	_bridge;
	protected CommonCompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>> _compositeBridge;
	protected AbstractCompositeContinuousBoundBridge<IFloatBound, IFloatSet,ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType,  BaseCompositeFloatSet<ChildType>> _boundBridge;

	protected FloatSplitter _splitter;


	protected BaseCompositeFloatSet()
	{
		_splitter = FloatSplitter.getInstance();
		_bridge = new CompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>(this);
		_compositeBridge = new CommonCompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, BaseCompositeFloatSet<ChildType>>(this);
	}

	protected BaseCompositeFloatSet(final ChildType element)
	{
		_splitter = FloatSplitter.getInstance();
		_bridge = new CompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>(this, element);
		_compositeBridge = new CommonCompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, BaseCompositeFloatSet<ChildType>>(this);

	}

	protected BaseCompositeFloatSet(final Iterable<ChildType> collection)
	{
		_splitter = FloatSplitter.getInstance();
		_bridge = new CompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>(this, collection);
		_compositeBridge = new CommonCompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, BaseCompositeFloatSet<ChildType>>(this);

	}


	@Override
	protected AbstractCompositeContinuousBoundBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, BaseCompositeFloatSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IFloatSet> split(final float[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IFloatSet> split(final float... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IFloatSet> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IFloatSet> splitOnRange(final IFloatSet set)
	{
		return _bridge.splitOnRange(set);
	}

	@Override
	public @Nullable IFloatBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IFloatBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final float value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleFloatSet getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeFloatSet<?> getAsComposite()
	{
		return this;
	}

	@Override
	public IFloatSet getAsSet()
	{
		return this;
	}

//	@Override
//	protected AbstractCompositeSortedSetBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, ? extends AbstractCompositeSortedSet<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, ICompositeFloatSet<?>, ChildType, SortedSet<ChildType>>, SortedSet<ChildType>> getBridge() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	protected CompositeFloatSetBridge<ChildType, BaseCompositeFloatSet<ChildType>> getBridge()
	{
		return _bridge;
	}

//	@Override
//	public CompositeFloatSetBridge<ChildType, ICompositeFloatSet<ChildType>> getBridge()
//	{
//		return _bridge;
//	}



}
