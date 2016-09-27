/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import java.util.ArrayList;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSetCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.classes.MutableCompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class CompositeGenericSetCreationToolbox
<
	Data,
	ChildType extends IGenericSet<Data>,
	BridgedType extends ICompositeGenericSet<Data, ChildType>
>
extends AbstractGenericSetCreationToolbox<Data, BridgedType>
implements
ICompositeSetCreationToolbox
<
	IGenericSet<Data>,
	ISimpleGenericSet<Data>,
	IElementaryGenericSet<Data>,
	IEmptyGenericSet<Data>,
	ICompositeGenericSet<Data, ?>,
	ChildType,
	BridgedType
>
{
	public CompositeGenericSetCreationToolbox(final BridgedType bridged) {
		super(bridged);
	}

	@Override
	public IGenericSet<Data> addChildToBridgedComposite(final IGenericSet<Data> set) {
		final ArrayList<IGenericSet<Data>> children = new ArrayList<IGenericSet<Data>>(_bridged.getChildrenCount()+1);
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.add(set);
		return MutableCompositeGenericSet.makeSet(children);
	}
}