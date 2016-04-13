/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateContinuousInterval
<
	Set extends IOrderedSet<Set>,
	BoundType extends AbstractBridgedContinuousBound<Set, ? extends AbstractDegenerateContinuousInterval<Set, BoundType>>
>
	extends AbstractDegenerateInterval<Set, BoundType>
	implements IDegenerateInterval<Set>, IContinuousInterval<Set>
{
	public AbstractDegenerateContinuousInterval(final BoundType bound)
	{
		super(bound);
	}
}