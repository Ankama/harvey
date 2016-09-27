/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.longs.sets.classes.BaseCompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.classes.MutableCompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ICompositeContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongInterval;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IElementaryContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IEmptyContinuousLongSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.ISimpleContinuousLongSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousLongSetCreationToolbox
<
	ChildType extends IContinuousLongSet,
	BridgedSet extends BaseCompositeContinuousLongSet<ChildType>
>
extends ContinuousLongSetCreationToolbox<BridgedSet>
implements ICompositeSortedSetCreationToolbox
<
	IContinuousLongBound,
	IContinuousLongSet,
	ISimpleContinuousLongSet,
	IElementaryContinuousLongSet,
	IContinuousLongInterval,
	IEmptyContinuousLongSet,
	ICompositeContinuousLongSet<?>,
	ChildType,
	BridgedSet
>
{

	public CompositeContinuousLongSetCreationToolbox(final BridgedSet bridged){
		super(bridged);
	}

	@Override
	public IContinuousLongSet addChildToBridgedComposite(final IContinuousLongSet set)
	{
		final ArrayList<IContinuousLongSet> children = new ArrayList<IContinuousLongSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeContinuousLongSet.makeSet(children);
	}
}
