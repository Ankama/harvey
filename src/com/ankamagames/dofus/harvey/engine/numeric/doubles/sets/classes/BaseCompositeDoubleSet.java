/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousBoundBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ICompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class BaseCompositeDoubleSet<ChildType extends IDoubleSet>
extends	AbstractCompositeContinuousSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, SortedSet<ChildType>>
implements ICompositeDoubleSet<ChildType>, IDoubleSet
{

	protected CompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>	_bridge;
	protected CommonCompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>> _compositeBridge;
	protected AbstractCompositeContinuousBoundBridge<IDoubleBound, IDoubleSet,ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType,  BaseCompositeDoubleSet<ChildType>> _boundBridge;

	protected DoubleSplitter _splitter;


	protected BaseCompositeDoubleSet()
	{
		_splitter = DoubleSplitter.getInstance();
		_bridge = new CompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>(this);
		_compositeBridge = new CommonCompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, BaseCompositeDoubleSet<ChildType>>(this);
	}

	protected BaseCompositeDoubleSet(final ChildType element)
	{
		_splitter = DoubleSplitter.getInstance();
		_bridge = new CompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>(this, element);
		_compositeBridge = new CommonCompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, BaseCompositeDoubleSet<ChildType>>(this);

	}

	protected BaseCompositeDoubleSet(final Iterable<ChildType> collection)
	{
		_splitter = DoubleSplitter.getInstance();
		_bridge = new CompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>(this, collection);
		_compositeBridge = new CommonCompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>>(
				this,
				_bridge.getSetCreationToolbox());
		_boundBridge = new AbstractCompositeContinuousBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, BaseCompositeDoubleSet<ChildType>>(this);

	}


	@Override
	protected AbstractCompositeContinuousBoundBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, BaseCompositeDoubleSet<ChildType>> getBoundBridge()
	{
		return _boundBridge;
	}

	@Override
	public List<? extends IDoubleSet> split(final double[] datas, final boolean[] isIntervalStart)
	{
		return _compositeBridge.split(datas, isIntervalStart);
	}

	@Override
	public List<? extends IDoubleSet> split(final double... datas)
	{
		final boolean[] inNextInterval = new boolean[datas.length];
		Arrays.fill(inNextInterval, true);
		return split(datas, inNextInterval);
	}

	@Override
	public List<? extends IDoubleSet> splitInParts(final int parts)
	{
		return _splitter.split(getAsSet(), parts);
	}

	@Override
	public List<? extends IDoubleSet> splitOnRange(final IDoubleSet set)
	{
		return _bridge.splitOnRange(set);
	}

	@Override
	public @Nullable IDoubleBound getLowerBound()
	{
		return _compositeBridge.getLowerBound();
	}

	@Override
	public @Nullable IDoubleBound getUpperBound()
	{
		return _compositeBridge.getUpperBound();
	}

	@Override
	public boolean contains(final double value)
	{
		return _compositeBridge.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridge.isInterval();
	}

	@Override
	public ISimpleDoubleSet getSimpleSet()
	{
		return _bridge.getSimpleSet();
	}

	@Override
	public String toString()
	{
		return _bridge.toString();
	}

	@Override
	public ICompositeDoubleSet<?> getAsComposite()
	{
		return this;
	}

	@Override
	public IDoubleSet getAsSet()
	{
		return this;
	}

//	@Override
//	protected AbstractCompositeSortedSetBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, ? extends AbstractCompositeSortedSet<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, ICompositeDoubleSet<?>, ChildType, SortedSet<ChildType>>, SortedSet<ChildType>> getBridge() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	protected CompositeDoubleSetBridge<ChildType, BaseCompositeDoubleSet<ChildType>> getBridge()
	{
		return _bridge;
	}

//	@Override
//	public CompositeDoubleSetBridge<ChildType, ICompositeDoubleSet<ChildType>> getBridge()
//	{
//		return _bridge;
//	}



}
