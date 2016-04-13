/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.TreeSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeOrderedSet
<
	Set extends IOrderedSet<Set>,
	ChildType extends IOrderedSet<Set>,
	BridgedCompositeType extends AbstractBridgedCompositeOrderedSet<Set, ChildType, ? extends AbstractCompositeOrderedSet<Set, ChildType, ?>>
>
	extends AbstractSet<Set>
	implements IOrderedSet<Set>, ICompositeSet<Set, ChildType>
{
	protected abstract BridgedCompositeType getBridgedComposite();

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		return getBridgedComposite().isEqual(obj);
	}
	
	@Override
	public boolean isEmpty()
	{
		return getBridgedComposite().isEmpty();
	}
	
	@Override
	public double size()
	{
		double r = 0;
		double sz;
		for(final ISet<Set> child:getMergedSet().getChildren())
		{
			sz = child.size();
			if(sz>0)
				r+=sz;
		}
		return r;
	}

	@Override
	public boolean is(final SetCoveringMask mask, final Set set)
	{
		return getBridgedComposite().is(mask, set);
	}

	@Override
	public boolean has(final SetCoveringMask mask, final Set set)
	{
		return getBridgedComposite().has(mask, set);
	}

	@Override
	public boolean isDegenerate()
	{
		return getBridgedComposite().isDegenerate();
	}
	
	@Override
	public boolean contains(final Set set)
	{
		return getBridgedComposite().contains(set);
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return getBridgedComposite().isContainedBy(set);
	}

	@Override
	public boolean intersects(final Set set)
	{
		return getBridgedComposite().intersects(set);
	}

	@Override
	public boolean isInRange(final Set set)
	{
		return getBridgedComposite().isInRange(set);
	}

	@Override
	public boolean hasValueInRange(final Set set)
	{
		return getBridgedComposite().hasValueInRange(set);
	}

	@Override
	public boolean isGreaterThan(final Set set)
	{
		return getBridgedComposite().isGreaterThan(set);
	}

	@Override
	public boolean isGreaterThanOrEqualTo(final Set set)
	{
		return getBridgedComposite().isGreaterThanOrEqualTo(set);
	}

	@Override
	public boolean hasValueLowerThan(final Set set)
	{
		return getBridgedComposite().hasValueLowerThan(set);
	}

	@Override
	public boolean isLowerThan(final Set set)
	{
		return getBridgedComposite().isLowerThan(set);
	}

	@Override
	public boolean isLowerThanOrEqualTo(final Set set)
	{
		return getBridgedComposite().isLowerThanOrEqualTo(set);
	}

	@Override
	public boolean hasValueGreaterThan(final Set set)
	{
		return getBridgedComposite().hasValueGreaterThan(set);
	}
	
	@Override
	public TreeSet<ChildType> getChildren()
	{
		return getBridgedComposite().getChildren();
	}
	public TreeSet<ChildType> getChildrenDescending()
	{
		return getBridgedComposite().getChildrenDescending();
	}
}