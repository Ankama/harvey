/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateShortSet
extends
IDegenerateSortedSet<IShortBound, IShortSet, ISimpleShortSet, IElementaryShortSet, IShortInterval, IDegenerateShortSet>,
IShortInterval
{
	short getValue();

	@Override
	Iterator<? extends IDegenerateShortSet> iterator();

	@Override
	IDegenerateShortSet getSimpleSet();
}