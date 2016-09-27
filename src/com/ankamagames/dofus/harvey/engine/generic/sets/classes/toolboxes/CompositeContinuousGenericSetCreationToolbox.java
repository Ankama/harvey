/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.BaseCompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.classes.MutableCompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeContinuousGenericSetCreationToolbox
<
	Data,
	ChildType extends IContinuousGenericSet<Data>,
	BridgedSet extends BaseCompositeContinuousGenericSet<Data, ChildType>
>
extends ContinuousGenericSetCreationToolbox<Data, BridgedSet>
implements ICompositeSortedSetCreationToolbox
<
	IContinuousGenericBound<Data>,
	IContinuousGenericSet<Data>,
	ISimpleContinuousGenericSet<Data>,
	IElementaryContinuousGenericSet<Data>,
	IContinuousGenericInterval<Data>,
	IEmptyContinuousGenericSet<Data>,
	ICompositeContinuousGenericSet<Data, ?>,
	ChildType,
	BridgedSet
>
{

	public CompositeContinuousGenericSetCreationToolbox(final BridgedSet bridged,
			final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter) {
		super(bridged, comparator, splitter);
	}

	@Override
	public IContinuousGenericSet<Data> addChildToBridgedComposite(final IContinuousGenericSet<Data> set)
	{
		final ArrayList<IContinuousGenericSet<Data>> children = new ArrayList<IContinuousGenericSet<Data>>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeContinuousGenericSet.makeSet(children, _comparator, _splitter);
	}
}
