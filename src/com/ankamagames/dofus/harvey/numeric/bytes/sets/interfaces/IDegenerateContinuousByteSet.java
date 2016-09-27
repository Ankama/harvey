/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousByteSet
extends IDegenerateContinuousSet<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IContinuousByteInterval, IDegenerateContinuousByteSet>,
IContinuousByteInterval
{
	byte getValue();

	@Override
	Iterator<? extends IDegenerateContinuousByteSet> iterator();

	@Override
	IDegenerateContinuousByteSet getSimpleSet();
}