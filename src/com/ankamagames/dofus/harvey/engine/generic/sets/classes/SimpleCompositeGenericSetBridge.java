/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractSimpleCompositeSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.CheckedSetArrayList;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.SimpleCompositeGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ICompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleCompositeGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class SimpleCompositeGenericSetBridge
<
	Data,
	BridgedSet extends ISimpleCompositeGenericSet<Data, IElementaryGenericSet<Data>>,
	InternalChildrenCollection extends CheckedSetArrayList<IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>>
>
extends AbstractSimpleCompositeSetBridge<IGenericSet<Data>,	ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, ICompositeGenericSet<Data, ?>, BridgedSet, InternalChildrenCollection>
{
	protected GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet> _equalityToolbox;

	protected SimpleCompositeGenericSetCreationToolbox<Data, IElementaryGenericSet<Data>, BridgedSet> _setCreationToolbox;

	public SimpleCompositeGenericSetBridge(final BridgedSet bridged, final InternalChildrenCollection childrenIterable)
	{
		super(bridged, childrenIterable);
		_equalityToolbox = new GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet>(bridged);
		_setCreationToolbox = new SimpleCompositeGenericSetCreationToolbox<Data, IElementaryGenericSet<Data>, BridgedSet>(bridged);
	}

	@Override
	protected GenericEqualityToolbox<Data, IGenericSet<Data>, ISimpleGenericSet<Data>, IElementaryGenericSet<Data>, BridgedSet> getEqualityToolbox()
	{

		return _equalityToolbox;
	}

	@Override
	protected SimpleCompositeGenericSetCreationToolbox<Data, IElementaryGenericSet<Data>, BridgedSet> getSetCreationToolbox()
	{
		return _setCreationToolbox;
	}

}
