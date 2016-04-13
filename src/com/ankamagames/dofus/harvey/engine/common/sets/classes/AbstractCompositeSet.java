package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


@NonNullByDefault
public abstract class AbstractCompositeSet
<
	Set extends ISet<Set>,
	ChildType extends ISet<Set>,
	BridgedCompositeType extends AbstractBridgedCompositeSet<Set, ChildType, HashSet<ChildType>, ?>
>
	extends AbstractSet<Set>
	implements ICompositeSet<Set, ChildType>
{
	abstract protected BridgedCompositeType getBridgedComposite();
	
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
		for(final ISet<Set> element:getMergedSet().getChildren())
		{
			r += element.size();
		}
		return r;
	}
	
	@Override
	public boolean isDegenerate()
	{
		return getBridgedComposite().isDegenerate();
	}

	@Override
	public boolean intersects(final Set set)
	{
		return getBridgedComposite().intersects(set);
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return getBridgedComposite().isContainedBy(set);
	}

	@Override
	public boolean contains(final Set set)
	{
		return getBridgedComposite().contains(set);
	}
	
	@Override
	public HashSet<ChildType> getChildren()
	{
		return getBridgedComposite().getChildren();
	}	
}