package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.AbstractGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ElementaryGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericEqualityToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public class DegenerateGenericSetBridge<Data, BridgedSet extends IDegenerateGenericSet<Data>>
extends AbstractDegenerateSetBridge<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, IDegenerateGenericSet<Data>, BridgedSet>
{
	protected GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet> _equalityToolbox;

	protected AbstractGenericSetCreationToolbox<Data, BridgedSet> _setCreationToolbox;

	public DegenerateGenericSetBridge(final BridgedSet bridged) {
		super(bridged);
		_equalityToolbox = new GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet>(_bridged);
		_setCreationToolbox = new ElementaryGenericSetCreationToolbox<Data, BridgedSet>(_bridged);
	}

	@Override
	protected GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected AbstractGenericSetCreationToolbox<Data, BridgedSet> getSetCreationToolbox() {
		return _setCreationToolbox;
	}
}