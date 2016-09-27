/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEmptySortedGenericSet<Data>
extends
IEmptySortedSet
<
	IGenericBound<Data>,
	ISortedGenericSet<Data>,
	ISimpleSortedGenericSet<Data>,
	IElementarySortedGenericSet<Data>,
	IGenericInterval<Data>,
	IEmptySortedGenericSet<Data>
>,
IGenericInterval<Data>
{
	@Override
	Iterator<? extends IEmptySortedGenericSet<Data>> iterator();

	@Override
	IEmptySortedGenericSet<Data> intersect(ISortedGenericSet<Data> set);

	@Override
	IEmptySortedGenericSet<Data> subtract(ISortedGenericSet<Data> set);

	@Override
	IEmptySortedGenericSet<Data> getSimpleSet();
}