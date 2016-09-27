/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.Comparator;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class SortedDescendingComparator<Set extends ISortedSet<?, Set, ?, ?>, ChildType extends ISortedSet<?, Set, ?, ?>>
implements Comparator<ChildType>
{

	@Override
	public int compare(@Nullable final ChildType o1, @Nullable final ChildType o2)
	{
		//Empty sets are put in the end of the collection to make it easy to find upperbound
		if((o1==null)||(o1.isEmpty()))
			if((o2==null)||(o2.isEmpty()))
				return 0;
			else
				return 1;
		if((o2==null)||(o2.isEmpty()))
			return -1;
		if(o1.hasValueGreaterThan(o2.getAsSet()) ||
				((!o2.hasValueGreaterThan(o1.getAsSet())) && (o2.hasValueLowerThan(o1.getAsSet()))))
			return -1;
		if(o2.hasValueGreaterThan(o1.getAsSet()) ||
				(o1.hasValueLowerThan(o2.getAsSet())))
			return 1;
		return 0;
	}

}
