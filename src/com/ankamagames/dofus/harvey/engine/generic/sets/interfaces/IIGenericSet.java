/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import java.util.Iterator;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIGenericSet
<
	Data,
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>
>
extends ICommonGenericSet<Data, Set, SimpleSet, ElementarySet>
{
	/**
	 * @return An iterator on set's data
	 */
	Iterator<Data> getDataIterator();

	/**
	 * Used to get a number of sample of the set data
	 * @param numberOfSample the number of sample asked
	 * @return samples of the set data
	 */
	List<Data> sample(int numberOfSample);

	/**
	 * Default way to get samples of the set data
	 * @return samples of the set data
	 */
	List<Data> sample();
}