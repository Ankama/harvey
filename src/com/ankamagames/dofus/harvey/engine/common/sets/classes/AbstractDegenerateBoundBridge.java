/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSortedSet;
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
public class AbstractDegenerateBoundBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	DegenerateSet extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>,
	Bridged extends IDegenerateSortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, DegenerateSet>
>
extends AbstractBoundBridge<Bound, Set, SimpleSet, ElementarySet, Bridged> {

	public AbstractDegenerateBoundBridge(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public Iterator<Bound> getBoundIterator()
	{
		return new TwoValueIterator<Bound>(_bridged.getLowerBound(), _bridged.getUpperBound());
	}

	@Override
	public int getBoundCount()
	{
		return 2;
	}

}
