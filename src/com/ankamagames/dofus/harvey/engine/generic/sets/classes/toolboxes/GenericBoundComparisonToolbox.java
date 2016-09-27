/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.ICommonSortedGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;



/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class GenericBoundComparisonToolbox
<
	Data,
	Bound extends IBound<Bound>&IIGenericBound<Data>,
	Set extends ICommonSortedGenericSet<Data, Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICommonSortedGenericSet<Data, Bound, Set, SimpleSet, ElementarySet>
>
extends BoundComparisonToolbox<Bound, Set, SimpleSet, ElementarySet, Bridged>
{

	protected Comparator<? super Data> _comparator;

	public GenericBoundComparisonToolbox(final Bridged bridged, final Comparator<? super Data> comparator)
	{
		super(bridged);
		_comparator = comparator;
	}

	@Override
	public int compareBound(final @Nullable Bound b1, final @Nullable Bound b2)
	{
		if(b1 == null)
		{
			if(b2 == null)
				return 0;
			return 1;
		}
		else
		{
			if(b2 == null)
				return -1;
			return _comparator.compare(b1.getValue(), b2.getValue());
		}
	}
}