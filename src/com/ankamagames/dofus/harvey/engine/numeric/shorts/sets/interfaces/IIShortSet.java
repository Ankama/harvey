/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIShortSet
<
	Set extends ISortedSet<IShortBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IShortBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IShortBound, Set, SimpleSet, ElementarySet>
>
extends
ICommonShortSet<IShortBound, Set, SimpleSet, ElementarySet>,
ISortedSet<IShortBound, Set, SimpleSet, ElementarySet>
{
	Iterator<Short> getReverseDataIterator();

	/**
	 * @return An iterator on set's data
	 */
	Iterator<Short> getDataIterator();

	/**
	 * Used to get a number of sample of the set data
	 * @OfSample the number of sample asked
	 * @return samples of the set data
	 */
	List<Short> sample(int numberOfSample);

	/** 	way to get samples of the set data
	 * @return samples of the set data
	 */
	List<Short> sample();
}
