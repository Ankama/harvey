/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousGenericSet<Data>
extends IContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>>,
IIContinuousGenericSet<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>>
{
	@Override
	public IContinuousGenericSet<Data> unite(IContinuousGenericSet<Data> set);
	@Override
	public IContinuousGenericSet<Data> intersect(IContinuousGenericSet<Data> set);
	@Override
	ISimpleContinuousGenericSet<Data> getSimpleSet();
}