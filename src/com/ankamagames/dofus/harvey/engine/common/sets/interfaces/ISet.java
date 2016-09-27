/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>
>
{
	/**
	 * The ISet has to be a Set itself
	 * This method insures this.
	 *
	 * @return
	 */
	Set getAsSet();

	/**
	 * Set is empty
	 * All empty set are equals
	 *
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Set contains only one value/element of the class
	 *
	 * @return
	 */
	boolean isDegenerate();

	/**
	 * Set holds all elements it contains directly
	 * it is not "composite"
	 *
	 * @return
	 */
	boolean isElementary();

	/**
	 * Set holds all elements it contains or is a composite of Elementary subsets
	 *
	 * @return true if the set is elementary or is a composite of elementary subsets.
	 * Or false if the set is a composite of composite sets
	 */
	boolean isSimple();

	/**
	 * The choice of a double as a return type has been made so that this value
	 * can hold the large values of a long as well as the "non integer" values of
	 * a float
	 *
	 * I'm not sure whether it will cause significant loss of accuracy, but I think
	 * it should not
	 *
	 * @return
	 */
	double size();

	/**
	 * Do both sets (this and given set) hold exactly the same elements (no more, no less).
	 *
	 * @param set
	 * @return
	 */
	boolean equals(Set set);

	/**
	* All values of the given set exist in this set
	*
	*/
	boolean contains(Set set);

	/**
	* All values of this set exist in the given set
	*
	*/
	boolean isContainedBy(Set set);

	/**
	 * Some values of this set exist in the given set
	 *
	 */
	boolean isIntersecting(Set set);

	/**
	 * Gives an iterator on the elementary parts forming the set
	 * It provides no guarantee that the parts don't overlap
	 */
	public Iterator<? extends IElementarySet<Set, SimpleSet, ElementarySet>> iterator();

	/**
	 * @param set
	 * @return a set that is the union of the current set and the given set
	 */
	ISet<Set, SimpleSet, ElementarySet> unite(Set set);

	/**
	 * @param set
	 * @return a set that is the intersection of the current set and the given set in
	 * the simplest possible form
	 */
	ISet<Set, SimpleSet, ElementarySet> intersect(Set set);

	/**
	 * @param set
	 * @return a set that is the difference of the current set and the given set in
	 * the simplest possible form
	 */
	ISet<Set, SimpleSet, ElementarySet> subtract(Set set);

	/**
	 * @param set
	 * @return a set that is the symmetric difference of the current set and the given
	 * set in the simplest possible form
	 */
	ISet<Set, SimpleSet, ElementarySet> symmetricSubtract(Set set);

	/**
	 * Provides a "flattened" version of the set
	 * It has to be the minimal version of the current set, with everything merged when
	 * possible and no overlap
	 *
	 * @return
	 */
	ISimpleSet<Set, SimpleSet, ElementarySet> getSimpleSet();
}