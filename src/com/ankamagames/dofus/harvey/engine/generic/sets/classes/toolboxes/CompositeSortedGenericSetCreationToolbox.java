package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.ArrayList;
import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.generic.sets.classes.MutableCompositeSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class CompositeSortedGenericSetCreationToolbox
<
	Data,
	ChildType extends ISortedGenericSet<Data>,
	BridgedType extends ICompositeSortedGenericSet<Data, ChildType>
>

extends SortedGenericSetCreationToolbox<Data, BridgedType>
implements
ICompositeSortedSetCreationToolbox
<
	IGenericBound<Data>,
	ISortedGenericSet<Data>,
	ISimpleSortedGenericSet<Data>,
	IElementarySortedGenericSet<Data>,
	IGenericInterval<Data>,
	IEmptySortedGenericSet<Data>,
	ICompositeSortedGenericSet<Data, ?>,
	ChildType,
	BridgedType
>
{

	public CompositeSortedGenericSetCreationToolbox(final BridgedType bridged, final Comparator<? super Data> comparator,
			final SurroundingValuesProvider<Data> surroundingProvider) {
		super(bridged, comparator, surroundingProvider);
	}

	@Override
	public ISortedGenericSet<Data> addChildToBridgedComposite(final ISortedGenericSet<Data> set)
	{
		final ArrayList<ISortedGenericSet<Data>> children = new ArrayList<ISortedGenericSet<Data>>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeSortedGenericSet.makeSet(children, _comparator, _surroundingProvider);
	}
}
