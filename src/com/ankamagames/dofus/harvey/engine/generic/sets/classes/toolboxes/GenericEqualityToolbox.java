package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class GenericEqualityToolbox
<
	Data,
	Set extends IIGenericSet<Data, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	BridgedSet extends IIGenericSet<Data, Set, SimpleSet, ElementarySet>
>
implements IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet>
{
	BridgedSet _bridged;

	public GenericEqualityToolbox(final BridgedSet bridged)
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