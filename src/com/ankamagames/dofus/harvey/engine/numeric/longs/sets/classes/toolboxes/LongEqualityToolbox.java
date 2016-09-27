package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces.IILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class LongEqualityToolbox
<
	Set extends IILongSet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<ILongBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<ILongBound, Set, SimpleSet, ElementarySet>,
	BridgedSet extends IILongSet<Set, SimpleSet, ElementarySet>
>
implements IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet>
{
	BridgedSet _bridged;

	public LongEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(@Nullable final Object obj)
	{
		return _bridged.getDataIterator().next().equals(obj);
	}

	@Override
	public boolean equalsDegenerateSet(final Set set)
	{
		return equalsValue(set.getDataIterator().next());
	}
}