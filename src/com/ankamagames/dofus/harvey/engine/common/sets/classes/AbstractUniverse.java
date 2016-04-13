/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractUniverse<Set extends IOrderedSet<Set>>
implements IContinuousInterval<Set>
{
	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return false;
	}
	
	@Override
	public boolean isDegenerate()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet#contains(com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet)
	 */
	@Override
	public boolean contains(final Set set)
	{
		return true;
	}

	@Override
	public abstract boolean isContainedBy(Set set);

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet#intersects(com.ankamagames.dofus.harvey.engine.common.interfaces.sets.ISet)
	 */
	@Override
	public boolean intersects(final Set set)
	{
		return true;
	}
	
	@Override
	public String toString() {
		return "Ω";
	}
}