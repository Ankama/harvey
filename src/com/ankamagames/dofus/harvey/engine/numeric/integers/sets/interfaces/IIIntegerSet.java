/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIIntegerSet
<
	Set extends ISortedSet<IIntegerBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IIntegerBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IIntegerBound, Set, SimpleSet, ElementarySet>
>
extends
ICommonIntegerSet<IIntegerBound, Set, SimpleSet, ElementarySet>,
ISortedSet<IIntegerBound, Set, SimpleSet, ElementarySet>
{
	Iterator<Integer> getReverseDataIterator();

	/**
	 * @return An iterator on set's data
	 */
	Iterator<Integer> getDataIterator();

	/**
	 * Used to get a number of sample of the set data
	 * @OfSample the number of sample asked
	 * @return samples of the set data
	 */
	List<Integer> sample(int numberOfSample);

	/** 	way to get samples of the set data
	 * @return samples of the set data
	 */
	List<Integer> sample();
}
