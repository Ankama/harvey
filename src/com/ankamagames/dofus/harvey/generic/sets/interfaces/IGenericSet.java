/**
 * 
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IGenericSet<Data>
	extends ISet<IGenericSet<Data>>, Iterable<Data>
{
	boolean contains(@Nullable Data value);
	@Override
	ICompositeGenericSet<Data, ?> getMergedSet();
}