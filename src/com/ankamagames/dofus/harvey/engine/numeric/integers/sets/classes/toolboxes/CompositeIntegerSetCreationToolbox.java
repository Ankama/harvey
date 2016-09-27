package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.MutableCompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ICompositeIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class CompositeIntegerSetCreationToolbox
<
	ChildType extends IIntegerSet,
	BridgedType extends ICompositeIntegerSet<ChildType>
>

extends IntegerSetCreationToolbox<BridgedType>
implements
ICompositeSortedSetCreationToolbox
<
	IIntegerBound,
	IIntegerSet,
	ISimpleIntegerSet,
	IElementaryIntegerSet,
	IIntegerInterval,
	IEmptyIntegerSet,
	ICompositeIntegerSet<?>,
	ChildType,
	BridgedType
>
{

	public CompositeIntegerSetCreationToolbox(final BridgedType bridged) {
		super(bridged);
	}

	@Override
	public IIntegerSet addChildToBridgedComposite(final IIntegerSet set)
	{
		final ArrayList<IIntegerSet> children = new ArrayList<IIntegerSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeIntegerSet.makeSet(children);
	}
}
