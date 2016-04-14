/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.floats.sets.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractContinuousEmpty;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.BaseFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public final class EmptyFloatSet
	extends AbstractContinuousEmpty<IFloatSet>
	implements IFloatSet
{
	private static EmptyFloatSet _instance = new EmptyFloatSet();
	
	public static EmptyFloatSet getInstance()
	{
		return _instance;
	}
	
	private EmptyFloatSet()
	{}
	
	@Override
	protected IFloatSet getThis()
	{
		return this;
	}

	@Override
	public float getLowerBound()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public float getUpperBound()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(final float value)
	{
		return false;
	}

	@Override
	public List<? extends IFloatSet> split(final float[] values, final boolean[] isIntervalStart)
	{
		final ArrayList<EmptyFloatSet> r = new ArrayList<EmptyFloatSet>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public List<? extends IFloatSet> split(final float... values)
	{
		final ArrayList<EmptyFloatSet> r = new ArrayList<EmptyFloatSet>(values.length+1);
		Collections.fill(r, this);
		return r;
	}

	@Override
	public BaseFloatSet<EmptyFloatSet> getMergedSet()
	{
		return BaseFloatSet.makeSet();
	}
}