/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.ArrayList;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IIntervalCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class SplitOnRangeBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Interval extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>,
	EmptySet extends IEmptySortedSet<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet>,
	Bridged extends IInterval<Bound, Set, SimpleSet, ElementarySet, Interval>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, Bridged>
{
	protected EmptySet _emptySet;
	protected IIntervalCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet, Bridged> _creationToolbox;

	public SplitOnRangeBridge(final Bridged bridged, final IIntervalCreationToolbox<Bound, Set, SimpleSet, ElementarySet, Interval, EmptySet, Bridged> creationToolbox)
	{
		super(bridged);
		_emptySet = creationToolbox.makeEmptySet();
		_creationToolbox = creationToolbox;
	}


	public List<? extends Set> splitOnRange(final Set set)
	{
		final ArrayList<Set> r = new ArrayList<Set>(3);
		if(set.isEmpty() || _bridged.isEmpty())
		{
			r.add(_emptySet.getAsSet());
			r.add(_emptySet.getAsSet());
			r.add(_emptySet.getAsSet());
			return r;
		}
		if(_bridged.isLowerThan(set))
		{
			r.add(_bridged.getAsSet());
			r.add(_emptySet.getAsSet());
			r.add(_emptySet.getAsSet());
			return r;
		}
		if(_bridged.isInRange(set))
		{
			r.add(_emptySet.getAsSet());
			r.add(_bridged.getAsSet());
			r.add(_emptySet.getAsSet());
			return r;
		}
		if(_bridged.isGreaterThan(set))
		{
			r.add(_emptySet.getAsSet());
			r.add(_emptySet.getAsSet());
			r.add(_bridged.getAsSet());
			return r;
		}

		if(_bridged.hasValueLowerThan(set))
		{
			if(_bridged.hasValueGreaterThan(set))
			{
				return _creationToolbox.splitOnRange(set);
			}
			else
			{
				final List<? extends Set> splitParts = _creationToolbox.splitOnLowerBound(set);
				r.add(splitParts.get(0));
				r.add(splitParts.get(1));
				r.add(_emptySet.getAsSet());
				return r;
			}
		}

		final List<? extends Set> splitParts = _creationToolbox.splitOnUpperBound(set);
		r.add(_emptySet.getAsSet());
		r.add(splitParts.get(0));
		r.add(splitParts.get(1));
		return r;
	}
}
