/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateIntegerSet
extends
IDegenerateSortedSet<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval, IDegenerateIntegerSet>,
IIntegerInterval
{
	int getValue();

	@Override
	Iterator<? extends IDegenerateIntegerSet> iterator();

	@Override
	IDegenerateIntegerSet getSimpleSet();
}