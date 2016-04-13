/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEmpty<Set extends ISet<Set>>
extends AbstractSet<Set>
implements ISet<Set>
{
	protected AbstractEmpty()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return true;
	}
	
	@Override
	public double size()
	{
		return 0;
	};

	@Override
	public boolean isDegenerate()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet#contains(
	 * com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet)
	 */
	@Override
	public boolean contains(final Set set)
	{
		if(set.isEmpty())
			return true;
		return false;
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet#
	 * intersects(com.ankamagames.dofus.harvey.engine.common.interfaces.sets. ISet)
	 */
	@Override
	public boolean intersects(final Set set)
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "∅";
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(!(obj instanceof ISet))
			return false;
		return ((ISet<?>)obj).isEmpty();
	}
}