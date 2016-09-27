/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IElementaryContinuousGenericSet<Data>
extends IElementaryContinuousSet<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>
{
	@Override
	IElementaryContinuousGenericSet<Data> getSimpleSet();
}