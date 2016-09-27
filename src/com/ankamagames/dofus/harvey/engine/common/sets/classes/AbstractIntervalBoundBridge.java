/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class AbstractIntervalBoundBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	Bridged extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends AbstractBoundBridge<Bound, Set, SimpleSet, ElementarySet, Bridged> {

	public AbstractIntervalBoundBridge(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public Iterator<Bound> getBoundIterator()
	{
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound upperBound = _bridged.getUpperBound();
		if(lowerBound == null || upperBound == null)
			return EmptyIterator.getInstance();
		return new TwoValueIterator<Bound>(lowerBound, upperBound);
	}

	@Override
	public int getBoundCount()
	{
		return _bridged.isEmpty()?0:2;
	}

}
