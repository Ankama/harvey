/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateInterval
<
	Set extends IOrderedSet<Set>,
	BoundType extends AbstractBridgedBound<Set, ? extends AbstractDegenerateInterval<Set, BoundType>>
>
extends AbstractDegenerateOrderedSet<Set>
implements IDegenerateInterval<Set>
{
	protected BoundType _bound;

	public AbstractDegenerateInterval(final BoundType bound)
	{
		super();
		_bound = bound;
	}
	
	@Override
	public boolean isInterval()
	{
		return true;
	}
	
	@Override
	public boolean containsAllValuesInRange(final Set set)
	{
		return set.isDegenerate()&&equals(set);
	}
}