/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeContinuousSet
<
	Set extends IContinuousSet<Set>,
	ChildType extends IContinuousSet<Set>,
	BridgedCompositeType extends AbstractBridgedCompositeOrderedSet<Set, ChildType, ? extends AbstractCompositeOrderedSet<Set, ChildType, ?>>
>
	extends AbstractCompositeOrderedSet<Set, ChildType, BridgedCompositeType>
	implements IContinuousSet<Set>
{}