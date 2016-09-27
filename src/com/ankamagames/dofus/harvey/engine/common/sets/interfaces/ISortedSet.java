/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedSet
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>
>
extends ISet<Set, SimpleSet, ElementarySet>
{
	public static enum SetCoveringMask
	{
		EMPTY(0), // 0b0000
		GREATER(1), // 0b0001
		LOWER(2), // 0b0010
		OUT_OF_RANGE(3), // 0b0011
		IN_RANGE_BUT_NOT_EQUAL(4), // 0b0100
		GREATER_OR_IN_RANGE_BUT_NOT_EQUAL(5), // 0b0101
		LOWER_OR_IN_RANGE_BUT_NOT_EQUAL(6), // 0b0110
		NOT_EQUAL(7), // 0b0111
		EQUAL(8), // 0b1000
		GREATER_OR_EQUAL(9), // 0b1001
		LOWER_OR_EQUAL(10), // 0b1010
		OUT_OF_RANGE_OR_EQUAL(11), // 0b1011
		IN_RANGE(12), // 0b1100
		GREATER_OR_IN_RANGE(13), // 0b1101
		LOWER_OR_IN_RANGE(14), // 0b1110
		ALL(15); // 0b1111

		protected int _mask;

		private SetCoveringMask(final int mask)
		{
			_mask = mask;
		}

		public int getMask()
		{
			return _mask;
		}
	}

	/**
	 * @return the Bound object containing the lowest value held by this set
	 */
	@Nullable Bound getLowerBound();

	/**
	 * @return the Bound object containing the highest value held by this set
	 */
	@Nullable Bound getUpperBound();

	/**
	 *
	 * @return an iterator of bound that skip contiguous bounds
	 */
	Iterator<Bound> getBoundIterator();

	/**
	 *
	 * @return the number of non contiguous Bound
	 */
	int getBoundCount();

	/**
	 * Are all the values held by this set contiguous ?
	 * @return true if all values held by this set are (considered) contiguous
	 */
	boolean isInterval();

	/**
	 * Is upperBound contiguous with set lowerbound ?
	 * @param set the set that should be just after this
	 * @return true if this is preceding set, false if there is a value between them, of if they intersects
	 */
	boolean isPreceding(Set set);

	/**
	 * Is lowerbound contiguous with set upperBound ?
	 * @param set the set that should be just before this
	 * @return true if this is succeeding set, false if there is a value between them, of if they intersects
	 */
	boolean isSucceeding(Set set);

	/**
	 * Is this preceding or succeeding set
	 * @param set the set that should be preceding of succeeding
	 * @return
	 */
	boolean hasContiguityWith(Set set);

	boolean is(SetCoveringMask mask, Set set);

	boolean has(SetCoveringMask mask, Set set);

	/**
	 * All values of this set are at the same time lower than the highest value of the given set and greater than its
	 * lowest value.
	 *
	 * if this set is empty, isInRange returns true.
	 *
	 * It is equivalent to making an interval with the bounds of set and test isContained on it.
	 *
	 * @param set
	 * @return
	 */
	boolean isInRange(Set set);

	/**
	 * Some values of this set areat the same time lower than the highest value of the given set and greater than its
	 * lowest value.
	 *
	 * if this set is empty, hasValueInRange returns false, because it has no values.
	 *
	 * @param set
	 * @return
	 */
	boolean hasValueInRange(Set set);

	/**
	 * All values of this set are greater than the highest value of the given set
	 *
	 * @param set
	 * @return
	 */
	boolean isGreaterThan(Set set);

	/**
	 * All values of this set are greater than the highest value of the given set or equal to any value of the given set
	 *
	 * @param set
	 * @return
	 */
	boolean isGreaterThanOrEqualTo(Set set);

	/**
	 * Some values of this set are lower than the lowest value of the given set
	 *
	 * @param set
	 * @return
	 */
	boolean hasValueLowerThan(Set set);

	/**
	 * All values of this set are lower than the lowest value of the given set
	 *
	 * @param set
	 * @return
	 */
	boolean isLowerThan(Set set);

	/**
	 * All values of this set are lower than the lowest value of the given set or equal to any value of the given set
	 *
	 * @param set
	 * @return
	 */
	boolean isLowerThanOrEqualTo(Set set);

	/**
	 * Some values of this set are greater than the highest value of the given set
	 *
	 * @param set
	 * @return
	 */
	boolean hasValueGreaterThan(Set set);

	/**
	 *
	 *
	 * @param set
	 * @return a list of 3 elements : at index 0 is the set for the values of this set that are lower than the values of
	 *         the given set, at index 1 is the set for the values of this set that are in the range of the given set
	 *         and at index 2 is the set for the values of this set that are greater than the values of the given set.
	 *         Each value can be EmptySet;
	 */
	List<? extends Set> splitOnRange(Set set);

	@Override
	Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> iterator();

	/**
	 * @return an iterator of elementarySet from the greater to the lower
	 */
	Iterator<? extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>> reverseIterator();

	@Override
	ISortedSet<Bound, Set, SimpleSet, ElementarySet> unite(Set set);

	@Override
	ISortedSet<Bound, Set, SimpleSet, ElementarySet> intersect(Set set);

	@Override
	ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet();
}