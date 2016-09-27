/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes.BaseCompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.classes.MutableCompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ICompositeContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortInterval;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IElementaryContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IEmptyContinuousShortSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.ISimpleContinuousShortSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousShortSetCreationToolbox
<
	ChildType extends IContinuousShortSet,
	BridgedSet extends BaseCompositeContinuousShortSet<ChildType>
>
extends ContinuousShortSetCreationToolbox<BridgedSet>
implements ICompositeSortedSetCreationToolbox
<
	IContinuousShortBound,
	IContinuousShortSet,
	ISimpleContinuousShortSet,
	IElementaryContinuousShortSet,
	IContinuousShortInterval,
	IEmptyContinuousShortSet,
	ICompositeContinuousShortSet<?>,
	ChildType,
	BridgedSet
>
{

	public CompositeContinuousShortSetCreationToolbox(final BridgedSet bridged){
		super(bridged);
	}

	@Override
	public IContinuousShortSet addChildToBridgedComposite(final IContinuousShortSet set)
	{
		final ArrayList<IContinuousShortSet> children = new ArrayList<IContinuousShortSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeContinuousShortSet.makeSet(children);
	}
}
