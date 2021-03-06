/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.ICommonContinuousShortSet;
import com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces.IIShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonCompositeContinuousShortSetBridge
<
	Bound extends IContinuousBound<Bound>&IIShortBound,
	Set extends ICommonContinuousShortSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ICommonContinuousShortSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends CommonCompositeShortSetBridge<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, Bridged>
{


	public CommonCompositeContinuousShortSetBridge(final Bridged bridged,
			final ICompositeSortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, ?, ?, CompositeSet, ChildType, Bridged> setCreationToolbox)
	{
		super(bridged, setCreationToolbox);
	}

	@Override
	protected boolean checkBoundary(final short value, final ChildType child)
	{
		final Bound lowerBound = child.getLowerBound();
		return (lowerBound!=null)&&(!lowerBound.isInfinity())&&((lowerBound.getValue() - value)>0);
	}

	@Override
	public @Nullable Bound getLowerBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildren().iterator().next();
		return next.getLowerBound();
	}

	@Override
	public @Nullable Bound getUpperBound()
	{
		if(_bridged.getChildrenCount()<=0)
			return null;
		final ChildType next = _bridged.getChildrenDescending().iterator().next();
		return next.getUpperBound();
	}
}