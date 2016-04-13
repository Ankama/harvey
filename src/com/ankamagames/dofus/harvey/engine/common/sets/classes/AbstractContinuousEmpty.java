/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractContinuousEmpty<Set extends IContinuousSet<Set>>
	extends AbstractOrderedEmpty<Set>
	implements IContinuousInterval<Set>
{
	@Override
	public boolean isLowerBoundClosed()
	{
		return false;
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		return false;
	}
}