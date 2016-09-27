/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes.BaseCompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.classes.MutableCompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ICompositeContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteInterval;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IEmptyContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousByteSetCreationToolbox
<
	ChildType extends IContinuousByteSet,
	BridgedSet extends BaseCompositeContinuousByteSet<ChildType>
>
extends ContinuousByteSetCreationToolbox<BridgedSet>
implements ICompositeSortedSetCreationToolbox
<
	IContinuousByteBound,
	IContinuousByteSet,
	ISimpleContinuousByteSet,
	IElementaryContinuousByteSet,
	IContinuousByteInterval,
	IEmptyContinuousByteSet,
	ICompositeContinuousByteSet<?>,
	ChildType,
	BridgedSet
>
{

	public CompositeContinuousByteSetCreationToolbox(final BridgedSet bridged){
		super(bridged);
	}

	@Override
	public IContinuousByteSet addChildToBridgedComposite(final IContinuousByteSet set)
	{
		final ArrayList<IContinuousByteSet> children = new ArrayList<IContinuousByteSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeContinuousByteSet.makeSet(children);
	}
}
