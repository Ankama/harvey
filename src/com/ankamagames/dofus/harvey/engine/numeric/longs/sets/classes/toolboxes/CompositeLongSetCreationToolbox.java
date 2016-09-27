package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.MutableCompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ILongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ICompositeLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class CompositeLongSetCreationToolbox
<
	ChildType extends ILongSet,
	BridgedType extends ICompositeLongSet<ChildType>
>

extends LongSetCreationToolbox<BridgedType>
implements
ICompositeSortedSetCreationToolbox
<
	ILongBound,
	ILongSet,
	ISimpleLongSet,
	IElementaryLongSet,
	ILongInterval,
	IEmptyLongSet,
	ICompositeLongSet<?>,
	ChildType,
	BridgedType
>
{

	public CompositeLongSetCreationToolbox(final BridgedType bridged) {
		super(bridged);
	}

	@Override
	public ILongSet addChildToBridgedComposite(final ILongSet set)
	{
		final ArrayList<ILongSet> children = new ArrayList<ILongSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeLongSet.makeSet(children);
	}
}
