/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.interfaces.IFloatBound;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes.toolbox.FloatSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.floats.sets.classes.DegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IDegenerateFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IElementaryFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.ISimpleFloatSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DegenerateFloatSetBridge<BridgedSet extends DegenerateFloatSet> extends
AbstractDegenerateContinuousSetBridge<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, IDegenerateFloatSet, BridgedSet>
{
	protected FloatEqualityToolbox<BridgedSet> _equalityToolbox;
	protected FloatSetCreationToolbox<BridgedSet> _creationToolbox;
	protected FloatBoundComparisonToolbox<BridgedSet> _boundToolbox;


	public DegenerateFloatSetBridge(final BridgedSet bridged) {
		super(bridged);
		_equalityToolbox = new FloatEqualityToolbox<BridgedSet>(_bridged);
		_creationToolbox = new FloatSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new FloatBoundComparisonToolbox<BridgedSet>(_bridged);
	}


	@Override
	protected IEqualityToolbox<IFloatSet, ISimpleFloatSet, IElementaryFloatSet, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ISortedSetCreationToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, ?, BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected BoundComparisonToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	public IFloatSet unite(final IFloatSet set)
	{
		final IFloatBound otherLowerBound = set.getLowerBound();
		final IFloatBound otherUpperBound = set.getUpperBound();
		if(otherLowerBound == null || otherUpperBound == null)
			return _bridged.getAsSet();
		if(set.isDegenerate())
			if(getBoundComparisonToolbox().areLowerBoundsEqual(set))
				return _bridged.getAsSet();
			else if(_bridged.isPreceding(set))
				return getSetCreationToolbox().makeInterval(_bridged.getLowerBound(), otherUpperBound).getAsSet();
			else if(_bridged.isSucceeding(set))
				return getSetCreationToolbox().makeInterval(otherLowerBound, _bridged.getUpperBound()).getAsSet();
			else if(set.isElementary())
				return getSetCreationToolbox().makeComposite(set).getAsSet();
			else
				return set.unite(_bridged.getAsSet()).getAsSet();
		if(isContainedBy(set))
			return set;
		if(set.isInterval())
			if(_bridged.isPreceding(set))
				return getSetCreationToolbox().makeInterval(_bridged.getLowerBound(), otherUpperBound).getAsSet();
			else if(_bridged.isSucceeding(set))
				return getSetCreationToolbox().makeInterval(otherLowerBound, _bridged.getUpperBound()).getAsSet();
			else if(set.isElementary()) {
				final ISortedSetCreationToolbox<IFloatBound, IFloatSet, ISimpleFloatSet, IElementaryFloatSet, IFloatInterval, ?, BridgedSet>  setCreationToolbox = getSetCreationToolbox();
				final IFloatSet makeComposite = setCreationToolbox.makeComposite(set);
				final IFloatSet asSet = makeComposite.getAsSet();
				return asSet;
			}

		return set.unite(_bridged.getAsSet()).getAsSet();
	}
}
