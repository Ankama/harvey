/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousGenericInterval<Data>
extends IContinuousGenericSet<Data>,
IContinuousInterval<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>>,
IElementaryContinuousGenericSet<Data>
{
	@Override
	IContinuousGenericInterval<Data> getSimpleSet();
}