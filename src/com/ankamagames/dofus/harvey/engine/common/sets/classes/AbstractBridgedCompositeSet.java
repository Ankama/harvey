/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractBridgedCompositeSet
<
	Set extends ISet<Set>,
	ChildType extends ISet<Set>,
	ChildrenSetType extends java.util.Set<ChildType>,
	Bridged extends ISet<Set>
>
{
	public abstract ChildrenSetType getChildren();
	
	public boolean isEqual(@Nullable final Object obj)
	{
		if(obj instanceof ISet)
		{
			@SuppressWarnings("unchecked")
			final Set set = (Set)obj;
			if(set != null)
				try
				{
					return contains(set) && isContainedBy(set);
				}
				catch(final Throwable t)
				{
					return false;
				}
		}
		if(isDegenerate())
		{
			final Iterator<ChildType> it = getChildren().iterator();
			ChildType child;
			do
			{
				child = it.next();
			}
			while(child.isEmpty());
			return child.equals(obj);
		}
		return false;
	}

	public boolean isEmpty()
	{
		for(final ChildType child:getChildren())
		{
			if(!child.isEmpty())
				return false;
		}
		return true;
	}

	public boolean isDegenerate()
	{
		ChildType degenerateSet = null;
		for(final ChildType child:getChildren())
		{
			final boolean isDegenerate = child.isDegenerate();
			if((!isDegenerate)&&(!child.isEmpty()))
				return false;
			if(isDegenerate)
			{
				if(degenerateSet == null)
					degenerateSet = child;
				else if(!degenerateSet.equals(child))
					return false;
			}
		}
		return true;
	}

	protected boolean intersects(final Set set)
	{
		for(final ChildType child:getChildren())
		{
			if(child.intersects(set))
				return true;
		}
		return false;
	}

	public boolean isContainedBy(final Set set)
	{
		for(final ChildType child:getChildren())
		{
			if(!child.isContainedBy(set))
				return false;
		}
		return true;
	}

	public abstract boolean contains(final Set set);
}