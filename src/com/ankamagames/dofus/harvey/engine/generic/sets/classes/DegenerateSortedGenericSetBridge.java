/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateSortedSetBridge;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.GenericEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes.SortedGenericSetCreationToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DegenerateSortedGenericSetBridge<Data, BridgedSet extends IDegenerateSortedGenericSet<Data>>
	extends AbstractDegenerateSortedSetBridge<IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IGenericInterval<Data>, IDegenerateSortedGenericSet<Data>, BridgedSet>
{
	protected GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedSet> _equalityToolbox;
	protected SortedGenericSetCreationToolbox<Data, BridgedSet> _creationToolbox;
	protected GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedSet> _boundToolbox;

	public DegenerateSortedGenericSetBridge(final BridgedSet bridged, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(bridged);
		_equalityToolbox = new GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedSet>(_bridged);
		_creationToolbox = new SortedGenericSetCreationToolbox<Data, BridgedSet>(_bridged, comparator, surroundingProvider);
		_boundToolbox = new GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedSet>(_bridged, comparator);
	}

	@Override
	protected GenericEqualityToolbox<Data, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected SortedGenericSetCreationToolbox<Data, BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected GenericBoundComparisonToolbox<Data, IGenericBound<Data>, ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}
}