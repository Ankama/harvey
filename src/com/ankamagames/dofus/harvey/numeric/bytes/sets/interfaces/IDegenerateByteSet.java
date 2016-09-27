/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateByteSet
extends
IDegenerateSortedSet<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IByteInterval, IDegenerateByteSet>,
IByteInterval
{
	byte getValue();

	@Override
	Iterator<? extends IDegenerateByteSet> iterator();

	@Override
	IDegenerateByteSet getSimpleSet();
}