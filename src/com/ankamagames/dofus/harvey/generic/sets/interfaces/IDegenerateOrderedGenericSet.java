/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateOrderedGenericSet<Data>
	extends IOrderedGenericSet<Data>, IDegenerateOrderedSet<IOrderedGenericSet<Data>>
{
	@Nullable Data getValue();
}