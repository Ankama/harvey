/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateSortedGenericSet<Data>
extends
IDegenerateSortedSet<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>>,
IGenericInterval<Data>
{
	@Nullable Data getValue();

	@Override
	Iterator<? extends IDegenerateSortedGenericSet<Data>> iterator();

	@Override
	IDegenerateSortedGenericSet<Data> getSimpleSet();
}