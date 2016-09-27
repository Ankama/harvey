/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.TwoValueIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBound;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSortedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISortedSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public class AbstractSimpleCompositeBoundBridge
<
	Bound extends IBound<Bound>,
	Set extends ISortedSet<Bound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSortedSet<Bound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	SimpleCompositeSet extends ISimpleCompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet, ChildType>,
	ChildType extends IElementarySortedSet<Bound, Set, SimpleSet, ElementarySet>,
	Bridged extends ICompositeSortedSet<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType>
>
extends AbstractCompositeBoundBridge<Bound, Set, SimpleSet, ElementarySet, CompositeSet, ChildType, Bridged>
{

	public AbstractSimpleCompositeBoundBridge(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public Iterator<Bound> getBoundIterator()
	{
		if(_bridged.isEmpty())
			return EmptyIterator.getInstance();
		final Bound lowerBound = _bridged.getLowerBound();
		final Bound upperBound = _bridged.getUpperBound();
		if(_bridged.isDegenerate() || _bridged.isInterval()) {
			if(lowerBound == null || upperBound == null)
				throw new NullPointerException();// this should never happen because interval must avec Lower and Upper bound
			return new TwoValueIterator<Bound>(lowerBound, upperBound);
		}
		return new Iterator<Bound>() {
			Iterator<? extends ChildType> childIt = _bridged.getChildren().iterator();
			ChildType lastChild = childIt.next();
			@Nullable Bound currentBound = lastChild.getLowerBound();
			ChildType nextChild = lastChild;
			ChildType prevChild = lastChild;
			Iterator<Bound> currentBoundIt = lastChild.getBoundIterator();
			boolean firstTime = true;
			boolean isLowerBound = true;
			boolean needNextChild = true;
			@Override
			public boolean hasNext() {

				while(!firstTime)
				{
					if(isLowerBound)
					{
						if(needNextChild && childIt.hasNext())
						{
							prevChild = nextChild;
							nextChild = childIt.next();
							if(nextChild.isSucceeding(prevChild.getAsSet()))
								continue;
							needNextChild = false;
						}
						else
						{
							isLowerBound = false;
							currentBound = lastChild.getUpperBound();
							needNextChild = false;
							return true;
						}
					}
					else
					{
						if(needNextChild && childIt.hasNext())
						{
							prevChild = nextChild;
							nextChild = childIt.next();
						}
						else if(!childIt.hasNext() && lastChild == nextChild)
							return false;
						lastChild = nextChild;
						currentBound = lastChild.getLowerBound();
						isLowerBound = true;
						needNextChild = true;
						return true;
					}
				}
				firstTime = false;
				return true;
			}

			@Override
			public Bound next()
			{
				final Bound tmp = currentBound;
				if(tmp == null)
					throw new NoSuchElementException();
				return tmp;
			}
		};
	}
}
