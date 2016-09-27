package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces.IIByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class ByteEqualityToolbox
<
	Set extends IIByteSet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<IByteBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<IByteBound, Set, SimpleSet, ElementarySet>,
	BridgedSet extends IIByteSet<Set, SimpleSet, ElementarySet>
>
implements IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet>
{
	BridgedSet _bridged;

	public ByteEqualityToolbox(final BridgedSet bridged)
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