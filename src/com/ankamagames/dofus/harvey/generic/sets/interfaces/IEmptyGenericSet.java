/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptyGenericSet<Data>
extends
IEmptySet<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IEmptyGenericSet<Data>>,
IGenericSet<Data>,
IElementaryGenericSet<Data>
{
	@Override
	IEmptyGenericSet<Data> intersect(IGenericSet<Data> set);

	@Override
	IEmptyGenericSet<Data> subtract(IGenericSet<Data> set);

	@Override
	IEmptyGenericSet<Data> getSimpleSet();
}