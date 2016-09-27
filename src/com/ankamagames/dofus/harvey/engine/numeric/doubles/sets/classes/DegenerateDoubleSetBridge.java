/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractDegenerateContinuousSetBridge;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.BoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.interfaces.IDoubleBound;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleBoundComparisonToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.sets.classes.toolbox.DoubleSetCreationToolbox;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.classes.DegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDegenerateDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IElementaryDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleInterval;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.IDoubleSet;
import com.ankamagames.dofus.harvey.numeric.doubles.sets.interfaces.ISimpleDoubleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class DegenerateDoubleSetBridge<BridgedSet extends DegenerateDoubleSet> extends
AbstractDegenerateContinuousSetBridge<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, IDegenerateDoubleSet, BridgedSet>
{
	protected DoubleEqualityToolbox<BridgedSet> _equalityToolbox;
	protected DoubleSetCreationToolbox<BridgedSet> _creationToolbox;
	protected DoubleBoundComparisonToolbox<BridgedSet> _boundToolbox;


	public DegenerateDoubleSetBridge(final BridgedSet bridged) {
		super(bridged);
		_equalityToolbox = new DoubleEqualityToolbox<BridgedSet>(_bridged);
		_creationToolbox = new DoubleSetCreationToolbox<BridgedSet>(_bridged);
		_boundToolbox = new DoubleBoundComparisonToolbox<BridgedSet>(_bridged);
	}


	@Override
	protected IEqualityToolbox<IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, BridgedSet> getEqualityToolbox()
	{
		return _equalityToolbox;
	}

	@Override
	protected ISortedSetCreationToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, ?, BridgedSet> getSetCreationToolbox()
	{
		return _creationToolbox;
	}

	@Override
	protected BoundComparisonToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, BridgedSet> getBoundComparisonToolbox()
	{
		return _boundToolbox;
	}

	@Override
	public IDoubleSet unite(final IDoubleSet set)
	{
		final IDoubleBound otherLowerBound = set.getLowerBound();
		final IDoubleBound otherUpperBound = set.getUpperBound();
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
				final ISortedSetCreationToolbox<IDoubleBound, IDoubleSet, ISimpleDoubleSet, IElementaryDoubleSet, IDoubleInterval, ?, BridgedSet>  setCreationToolbox = getSetCreationToolbox();
				final IDoubleSet makeComposite = setCreationToolbox.makeComposite(set);
				final IDoubleSet asSet = makeComposite.getAsSet();
				return asSet;
			}

		return set.unite(_bridged.getAsSet()).getAsSet();
	}
}
