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
public interface ICompositeSet<Set extends ISet<Set>, ChildType extends ISet<Set>>
	extends ISet<Set>
{
	java.util.Set<ChildType> getChildren();
}