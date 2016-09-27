/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.SortedSet;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractSimpleCompositeContinuousSet
<
	Bound extends IContinuousBound<Bound>,
	Set extends IContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	SimpleCompositeSet extends ISimpleCompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ?>,
	ChildType extends IElementaryContinuousSet<Bound, Set, SimpleSet, ElementarySet>,
	InternalChildrenCollectionType extends SortedSet<ChildType>
>
extends AbstractSimpleCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ChildType, InternalChildrenCollectionType>
implements ICompositeContinuousSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
{
	@Override
	public boolean isLowerBoundClosed()
	{
		if(getChildren().isEmpty())
			return false;
		return getChildren().first().isLowerBoundClosed();
	}

	@Override
	public boolean isUpperBoundClosed()
	{
		if(getChildrenDescending().isEmpty())
			return false;
		return getChildrenDescending().first().isUpperBoundClosed();
	}

	@Override
	public ISimpleContinuousSet<Bound, Set, SimpleSet, ElementarySet> getSimpleSet()
	{
		return super.getSimpleSet().getAsSimpleSet();
	}
}
