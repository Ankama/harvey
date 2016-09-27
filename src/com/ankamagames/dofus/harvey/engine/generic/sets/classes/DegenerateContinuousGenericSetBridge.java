/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.comparators.ContinuousComparator;
import com.ankamagames.dofus.harvey.engine.generic.comparators.Splitter;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.ContinuousGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateContinuousGenericSetBridge<Data, BridgedSet extends IDegenerateContinuousGenericSet<Data>>
	extends AbstractDegenerateContinuousSetBridge<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>, IDegenerateContinuousGenericSet<Data>, BridgedSet>
{
	protected ContinuousGenericEqualityToolbox<Data, BridgedSet> _equalityToolbox;
	protected ContinuousGenericSetCreationToolbox<Data, BridgedSet> _creationToolbox;
	protected ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet> _boundToolbox;

	public DegenerateContinuousGenericSetBridge(final BridgedSet bridged, final ContinuousComparator<? super Data> comparator, final Splitter<IContinuousGenericBound<Data>, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, IContinuousGenericInterval<Data>> splitter)
	{
		super(bridged);
		_equalityToolbox = new ContinuousGenericEqualityToolbox<Data, BridgedSet>(_bridged);
		_creationToolbox = new ContinuousGenericSetCreationToolbox<Data, BridgedSet>(_bridged, comparator, splitter);
		_boundToolbox = new ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet>(_bridged, comparator);
	}

	@Override
	protected ContinuousGenericEqualityToolbox<Data, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ContinuousGenericSetCreationToolbox<Data, BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected ContinuousGenericBoundComparisonToolbox<Data, IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}