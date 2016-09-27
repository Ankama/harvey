/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ICompositeSortedSetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.ICommonContinuousGenericSet;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CommonCompositeContinuousGenericSetBridge
<
	Data,
	Bound extends IContinuousBound<Bound>&IIGenericBound<Data>,
	Set extends ICommonContinuousGenericSet<Data, Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	ChildType extends ICommonContinuousGenericSet<Data, Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends CommonCompositeSortedGenericSetBridge<Data, Bound,  Set, SimpleSet, ElementarySet, CompositeSet, ChildType, Bridged>
{
	public CommonCompositeContinuousGenericSetBridge(final Bridged bridged, final Comparator<? super Data> comparator,
		final ICompositeSortedSetCreationToolbox<Bound, Set, SimpleSet, ElementarySet, ?, ?, CompositeSet, ChildType, Bridged> setCreationToolbox)
	{
		super(bridged, comparator, setCreationToolbox);
	}

	@Override
	protected boolean checkBoundary(final @Nullable Data value, final ChildType child)
	{
		final Bound lowerBound = child.getLowerBound();
		return (lowerBound!=null)&&(!lowerBound.isInfinity())&&(_comparator.compare(lowerBound.getValue(), value)>0);
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