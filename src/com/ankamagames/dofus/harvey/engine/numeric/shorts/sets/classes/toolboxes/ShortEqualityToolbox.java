package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class ShortEqualityToolbox
<
	Set extends IIShortSet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IShortBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IShortBound, Set, SimpleSet, ElementarySet>,
	BridgedSet extends IIShortSet<Set, SimpleSet, ElementarySet>
>
implements IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet>
{
	BridgedSet _bridged;

	public ShortEqualityToolbox(final BridgedSet bridged)
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