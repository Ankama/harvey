/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIByteSet
<
	Set extends ISortedSet<IByteBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IByteBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IByteBound, Set, SimpleSet, ElementarySet>
>
extends
ICommonByteSet<IByteBound, Set, SimpleSet, ElementarySet>,
ISortedSet<IByteBound, Set, SimpleSet, ElementarySet>
{
	Iterator<Byte> getReverseDataIterator();

	/**
	 * @return An iterator on set's data
	 */
	Iterator<Byte> getDataIterator();

	/**
	 * Used to get a number of sample of the set data
	 * @OfSample the number of sample asked
	 * @return samples of the set data
	 */
	List<Byte> sample(int numberOfSample);

	/** 	way to get samples of the set data
	 * @return samples of the set data
	 */
	List<Byte> sample();
}
