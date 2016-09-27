/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes.BaseCompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.classes.MutableCompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ICompositeContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerInterval;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IElementaryContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IEmptyContinuousIntegerSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.ISimpleContinuousIntegerSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousIntegerSetCreationToolbox
<
	ChildType extends IContinuousIntegerSet,
	BridgedSet extends BaseCompositeContinuousIntegerSet<ChildType>
>
extends ContinuousIntegerSetCreationToolbox<BridgedSet>
implements ICompositeSortedSetCreationToolbox
<
	IContinuousIntegerBound,
	IContinuousIntegerSet,
	ISimpleContinuousIntegerSet,
	IElementaryContinuousIntegerSet,
	IContinuousIntegerInterval,
	IEmptyContinuousIntegerSet,
	ICompositeContinuousIntegerSet<?>,
	ChildType,
	BridgedSet
>
{

	public CompositeContinuousIntegerSetCreationToolbox(final BridgedSet bridged){
		super(bridged);
	}

	@Override
	public IContinuousIntegerSet addChildToBridgedComposite(final IContinuousIntegerSet set)
	{
		final ArrayList<IContinuousIntegerSet> children = new ArrayList<IContinuousIntegerSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeContinuousIntegerSet.makeSet(children);
	}
}
