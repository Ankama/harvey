/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateLongSet
extends
IDegenerateSortedSet<ILongBound, ILongSet, ISimpleLongSet, IElementaryLongSet, ILongInterval, IDegenerateLongSet>,
ILongInterval
{
	long getValue();

	@Override
	Iterator<? extends IDegenerateLongSet> iterator();

	@Override
	IDegenerateLongSet getSimpleSet();
}