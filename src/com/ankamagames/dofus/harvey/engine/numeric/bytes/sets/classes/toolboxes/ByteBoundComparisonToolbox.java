/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.ICommonByteSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;



/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class ByteBoundComparisonToolbox
<
	Bound extends IBound<Bound> & IIByteBound,
	Set extends ICommonByteSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICommonByteSet<Bound, Set, SimpleSet, ElementarySet>
>
extends BoundComparisonToolbox<Bound, Set, SimpleSet, ElementarySet, Bridged>
{


	public ByteBoundComparisonToolbox(final Bridged bridged)
	{
		super(bridged);
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
			return b1.getValue() - b2.getValue();
		}
	}
}