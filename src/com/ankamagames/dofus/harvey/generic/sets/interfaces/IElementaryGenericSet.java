/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryGenericSet<Data>
extends IElementarySet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>, IGenericSet<Data>, ISimpleGenericSet<Data>
{
	@Override
	IElementaryGenericSet<Data> getSimpleSet();
}