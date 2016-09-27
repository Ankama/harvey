package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.MutableCompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ICompositeShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class CompositeShortSetCreationToolbox
<
	ChildType extends IShortSet,
	BridgedType extends ICompositeShortSet<ChildType>
>

extends ShortSetCreationToolbox<BridgedType>
implements
ICompositeSortedSetCreationToolbox
<
	IShortBound,
	IShortSet,
	ISimpleShortSet,
	IElementaryShortSet,
	IShortInterval,
	IEmptyShortSet,
	ICompositeShortSet<?>,
	ChildType,
	BridgedType
>
{

	public CompositeShortSetCreationToolbox(final BridgedType bridged) {
		super(bridged);
	}

	@Override
	public IShortSet addChildToBridgedComposite(final IShortSet set)
	{
		final ArrayList<IShortSet> children = new ArrayList<IShortSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeShortSet.makeSet(children);
	}
}
