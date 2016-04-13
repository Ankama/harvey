/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeContinuousSet
<
	Set extends ISet<Set>,
	ChildType extends IContinuousSet<Set>,
	Bridged extends IContinuousSet<Set>
>
extends AbstractBridgedCompositeOrderedSet<Set, ChildType, Bridged>
{}