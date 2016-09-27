/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IILongSet
<
	Set extends ISortedSet<ILongBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<ILongBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<ILongBound, Set, SimpleSet, ElementarySet>
>
extends
ICommonLongSet<ILongBound, Set, SimpleSet, ElementarySet>,
ISortedSet<ILongBound, Set, SimpleSet, ElementarySet>
{
	Iterator<Long> getReverseDataIterator();

	/**
	 * @return An iterator on set's data
	 */
	Iterator<Long> getDataIterator();

	/**
	 * Used to get a number of sample of the set data
	 * @OfSample the number of sample asked
	 * @return samples of the set data
	 */
	List<Long> sample(int numberOfSample);

	/** 	way to get samples of the set data
	 * @return samples of the set data
	 */
	List<Long> sample();
}
