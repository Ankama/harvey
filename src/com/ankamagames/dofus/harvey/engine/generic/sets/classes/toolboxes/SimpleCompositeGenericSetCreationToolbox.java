/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.CheckedSetArrayList;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSetCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.classes.GenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IEmptyGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleCompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class SimpleCompositeGenericSetCreationToolbox 
<
	Data,
	ChildType extends IElementaryGenericSet<Data>,
	BridgedType extends ISimpleCompositeGenericSet<Data, ChildType>
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

	
	
	public SimpleCompositeGenericSetCreationToolbox(BridgedType bridged) {
		super(bridged);
	}

	@Override
	public GenericSet<Data> addChildToBridgedComposite(IGenericSet<Data> set) 
	{
		final CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>> children = CheckedSetArrayList.makeCheckedSetArrayList();
		for(final ChildType child:_bridged.getChildren())
			children.add(child);
		children.addSet(set);
		BridgedType test = _bridged;
		return GenericSet.makeSet(children);
	}
	
}
