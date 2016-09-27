package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.MutableCompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ICompositeByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class CompositeByteSetCreationToolbox
<
	ChildType extends IByteSet,
	BridgedType extends ICompositeByteSet<ChildType>
>

extends ByteSetCreationToolbox<BridgedType>
implements
ICompositeSortedSetCreationToolbox
<
	IByteBound,
	IByteSet,
	ISimpleByteSet,
	IElementaryByteSet,
	IByteInterval,
	IEmptyByteSet,
	ICompositeByteSet<?>,
	ChildType,
	BridgedType
>
{

	public CompositeByteSetCreationToolbox(final BridgedType bridged) {
		super(bridged);
	}

	@Override
	public IByteSet addChildToBridgedComposite(final IByteSet set)
	{
		final ArrayList<IByteSet> children = new ArrayList<IByteSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeByteSet.makeSet(children);
	}
}
