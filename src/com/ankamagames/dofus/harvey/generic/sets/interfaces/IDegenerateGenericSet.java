/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateGenericSet<Data>
	extends IDegenerateSet<IGenericSet<Data>>, IGenericSet<Data>
{
	@Nullable Data getValue();
}