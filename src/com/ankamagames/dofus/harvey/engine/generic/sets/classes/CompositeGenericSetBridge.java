package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractCompositeSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.CompositeGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericEqualityToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class CompositeGenericSetBridge
<
	Data,
	ChildType extends IGenericSet<Data>,
	BridgedSet extends ICompositeGenericSet<Data, ChildType>
>
extends
AbstractCompositeSetBridge
<
	IGenericSet<Data>,
	ISimpleGenericSet<Data>,
	IElementaryGenericSet<Data>,
	ICompositeGenericSet<Data, ?>,
	ChildType,
	BridgedSet,
	Collection<ChildType>
>
{
	protected GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet> _equalityToolbox;

	protected CompositeGenericSetCreationToolbox<Data, ChildType, BridgedSet> _setCreationToolbox;

	public CompositeGenericSetBridge(final BridgedSet bridged, final Collection<ChildType> childrenIterable)
	{
		super(bridged, childrenIterable);
		_equalityToolbox = new GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet>(_bridged);
		_setCreationToolbox = new CompositeGenericSetCreationToolbox<Data, ChildType, BridgedSet>(_bridged);
	}

	@Override
	protected GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected CompositeGenericSetCreationToolbox<Data, ChildType, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}
}