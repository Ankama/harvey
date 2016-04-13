/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeOrderedSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ICompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseFloatSet<ChildType extends IFloatSet>
	extends
	AbstractCompositeOrderedSet<IFloatSet, ChildType, BridgedCompositeFloatSet<ChildType, BaseFloatSet<ChildType>>>
	implements ICompositeFloatSet<ChildType>
{
	protected BridgedCompositeFloatSet<ChildType, BaseFloatSet<ChildType>>	_bridgedComposite;

	public static <ChildType extends IFloatSet> BaseFloatSet<ChildType> makeSet()
	{
		return new BaseFloatSet<ChildType>();
	}

	public static <ChildType extends IFloatSet> BaseFloatSet<ChildType> makeSet(final ChildType element)
	{
		return new BaseFloatSet<ChildType>(element);
	}

	public static <ChildType extends IFloatSet> BaseFloatSet<ChildType> makeSet(final Collection<? extends ChildType> collection)
	{
		return new BaseFloatSet<ChildType>(collection);
	}

	private BaseFloatSet()
	{
		_bridgedComposite = new BridgedCompositeFloatSet<ChildType, BaseFloatSet<ChildType>>(this);
	}

	private BaseFloatSet(final ChildType element)
	{
		_bridgedComposite = new BridgedCompositeFloatSet<ChildType, BaseFloatSet<ChildType>>(this, element);
	}

	private BaseFloatSet(final Collection<? extends ChildType> collection)
	{
		_bridgedComposite = new BridgedCompositeFloatSet<ChildType, BaseFloatSet<ChildType>>(this, collection);
	}

	@Override
	protected IFloatSet getThis()
	{
		return this;
	}

	@Override
	protected BridgedCompositeFloatSet<ChildType, BaseFloatSet<ChildType>> getBridgedComposite()
	{
		return _bridgedComposite;
	}

	@Override
	public List<? extends IFloatSet> split(final float[] floats, final boolean[] isIntervalStart)
	{
		return _bridgedComposite.split(floats, isIntervalStart);
	}

	@Override
	public List<? extends IFloatSet> split(final float... floats)
	{
		final boolean[] inNextInterval = new boolean[floats.length];
		Arrays.fill(inNextInterval, true);
		return split(floats, inNextInterval);
	}

	@Override
	public List<? extends IFloatSet> splitOnRange(final IFloatSet set)
	{
		return _bridgedComposite.splitOnRange(set);
	}

	@Override
	public float getLowerBound()
	{
		return _bridgedComposite.getLowerBound();
	}

	@Override
	public float getUpperBound()
	{
		return _bridgedComposite.getUpperBound();
	}

	@Override
	public boolean isLowerBoundClosed()
	{
		return _bridgedComposite.isLowerBoundClosed();
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return _bridgedComposite.isUpperBoundClosed();
	}

	@Override
	public boolean contains(final float value)
	{
		return _bridgedComposite.contains(value);
	}

	@Override
	public boolean isInterval()
	{
		return _bridgedComposite.isInterval();
	}

	@Override
	public boolean containsAllValuesInRange(final IFloatSet set)
	{
		return _bridgedComposite.containsAllValuesInRange(set);
	}
	
	@Override
	public BaseFloatSet<?> getMergedSet()
	{
		return _bridgedComposite.getMergedSet();
	}
	
	@Override
	public String toString()
	{
		return _bridgedComposite.toString();
	}
}