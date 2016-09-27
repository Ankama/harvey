/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.ICommonShortSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonCompositeShortSetBridge
<
	Bound extends IBound<Bound>&IIShortBound,
	Set extends ICommonShortSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ICommonShortSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, Bridged>
{
	protected ICompositeSortedSetCreationToolbox<Bound, Set, SimpleSet,ElementarySet, ?, ?, CompositeSet, ChildType, Bridged> _setCreationToolbox;

	public CommonCompositeShortSetBridge(final Bridged bridged,
		final ICompositeSortedSetCreationToolbox<Bound, Set, SimpleSet,ElementarySet, ?, ?, CompositeSet, ChildType, Bridged> setCreationToolbox)
	{
		super(bridged);
		_setCreationToolbox = setCreationToolbox;
	}

	public @Nullable Bound getLowerBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildren().iterator().next();
		return next.getLowerBound();
	}

	public @Nullable Bound getUpperBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildrenDescending().iterator().next();
		return next.getUpperBound();
	}

	protected boolean checkBoundary(final short value, final ChildType child)
	{
		final Bound lowerBound = child.getLowerBound();
		return (lowerBound!=null)&&((lowerBound.getValue() - value)>0);
	}

	public boolean contains(final short value)
	{
		for(final ChildType child:_bridged.getChildren())
			if(child.contains(value))
				return true;
			else
			{
				if(checkBoundary(value, child))
					return false;
			}
		return false;
	}


	public List<? extends Set> split(final short[] values, final boolean[] isIntervalStart) {
		final List<Set> r = new ArrayList<Set>( values.length + 1 );
		final ArrayList<List<Set>> partsList = new ArrayList<List<Set>>(values.length+1);
		for(int i = 0 ; i <= values.length ; i++)
			partsList.add(new LinkedList<Set>());
		for (final ChildType child : _bridged.getChildren()) {
			final List<? extends Set> currentSplit = child.split(values, isIntervalStart);
			final Iterator<List<Set>> it = partsList.iterator();
			for(final Set elmt:currentSplit) {
				final List<Set> next = it.next();
				if(!elmt.isEmpty())
					next.add(elmt);
			}
		}

		for(final List<Set> splitList:partsList)
		{
			if (splitList.isEmpty())
				r.add(_setCreationToolbox.makeEmptySet().getAsSet());
			else if (splitList.size() == 1)
				r.add(splitList.get(0));
			else
				r.add(_setCreationToolbox.makeComposite(splitList));
		}

		return r;
	}
}