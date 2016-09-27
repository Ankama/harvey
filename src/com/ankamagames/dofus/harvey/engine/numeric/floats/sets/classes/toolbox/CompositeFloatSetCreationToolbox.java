/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.EmptyFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.MutableCompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ICompositeFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeFloatSetCreationToolbox<ChildType extends IFloatSet, Bridged extends ICompositeFloatSet<ChildType>>
extends FloatSetCreationToolbox<Bridged>
implements ICompositeSortedSetCreationToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, EmptyFloatSet, ICompositeFloatSet<?>, ChildType, Bridged>
{

	public CompositeFloatSetCreationToolbox(final Bridged bridged) {
		super(bridged);
	}

	@Override
	public IFloatSet addChildToBridgedComposite(final IFloatSet set)
	{
		final ArrayList<IFloatSet> children = new ArrayList<IFloatSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeFloatSet.makeSet(children);
	}

}
