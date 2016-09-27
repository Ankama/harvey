/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateContinuousLongSet
extends IDegenerateContinuousSet<IContinuousLongBound, IContinuousLongSet, ISimpleContinuousLongSet, IElementaryContinuousLongSet, IContinuousLongInterval, IDegenerateContinuousLongSet>,
IContinuousLongInterval
{
	long getValue();

	@Override
	Iterator<? extends IDegenerateContinuousLongSet> iterator();

	@Override
	IDegenerateContinuousLongSet getSimpleSet();
}