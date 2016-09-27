package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces.IIIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class IntegerEqualityToolbox
<
	Set extends IIIntegerSet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IIntegerBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IIntegerBound, Set, SimpleSet, ElementarySet>,
	BridgedSet extends IIIntegerSet<Set, SimpleSet, ElementarySet>
>
implements IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet>
{
	BridgedSet _bridged;

	public IntegerEqualityToolbox(final BridgedSet bridged)
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