/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeContinuousSet<Set extends ISet<Set>, ChildType extends IContinuousSet<Set>>
	extends IContinuousSet<Set>, ICompositeOrderedSet<Set, ChildType>
{}