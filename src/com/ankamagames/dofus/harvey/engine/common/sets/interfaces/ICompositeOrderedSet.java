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
public interface ICompositeOrderedSet<Set extends ISet<Set>, ChildType extends IOrderedSet<Set>>
	extends IOrderedSet<Set>, ICompositeSet<Set, ChildType>
{}