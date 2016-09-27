/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.EmptyDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.MutableCompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ICompositeDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeDoubleSetCreationToolbox<ChildType extends IDoubleSet, Bridged extends ICompositeDoubleSet<ChildType>>
extends DoubleSetCreationToolbox<Bridged>
implements ICompositeSortedSetCreationToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, EmptyDoubleSet, ICompositeDoubleSet<?>, ChildType, Bridged>
{

	public CompositeDoubleSetCreationToolbox(final Bridged bridged) {
		super(bridged);
	}

	@Override
	public IDoubleSet addChildToBridgedComposite(final IDoubleSet set)
	{
		final ArrayList<IDoubleSet> children = new ArrayList<IDoubleSet>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeDoubleSet.makeSet(children);
	}

}
