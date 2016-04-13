/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedDegenerateContinuousSetImplementation<Set extends IContinuousSet<Set>, BridgedType extends IDegenerateContinuousSet<Set>>
	extends AbstractBridgedDegenerateOrderedSetImplementation<Set, BridgedType>
{
	public AbstractBridgedDegenerateContinuousSetImplementation(final BridgedType bridged)
	{
		super(bridged);
	}
}