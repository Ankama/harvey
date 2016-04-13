/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateSet<Set extends ISet<Set>>
extends AbstractSet<Set>
implements IDegenerateSet<Set>
{	
	@Override
	public boolean isEmpty()
	{
		return false;
	}
	
	@Override
	public double size()
	{
		return 1;
	}

	@Override
	public boolean isDegenerate()
	{
		return true;
	}

	@Override
	public boolean contains(final Set set)
	{
		if(set.isEmpty())
			return true;
		if(!set.isDegenerate())
			return false;
		return equals(set);
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return set.contains(_me);
	}

	@Override
	public boolean intersects(final Set set)
	{
		return isContainedBy(set);
	}
}