/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedCompositeSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedCompositeGenericSet<Data, ChildType extends IGenericSet<Data>>
	extends AbstractBridgedCompositeSet
	<
		IGenericSet<Data>,
		ChildType,
		HashSet<ChildType>,
		IGenericSet<Data>
	>
{
	protected HashSet<ChildType> _children;

	public BridgedCompositeGenericSet()
	{
		_children = new HashSet<ChildType>();
	}
	
	public BridgedCompositeGenericSet(final int initialCapacity)
	{
		_children = new HashSet<ChildType>(initialCapacity);
	}

	public BridgedCompositeGenericSet(final Collection<? extends ChildType> children)
	{
		_children = new HashSet<ChildType>(children);
	}
	
	@Override
	public HashSet<ChildType> getChildren()
	{
		return _children;
	}

	@SuppressWarnings("null")
	@Override
	public boolean contains(final IGenericSet<Data> set)
	{
		if(set.isEmpty())
			return true;
		
		if(isEmpty())
			return false;
		
		if(set.isDegenerate())
		{
			for(final ChildType child:getChildren())
			{
				if(child.contains(set))
					return true;
			}
			return false;
		}
		
		if(isDegenerate())
		{
			return false;
		}
		
		for(final IGenericSet<Data> element:set.getMergedSet().getChildren())
		{
			if(!contains(element))
				return false;
		}
		return true;
	}

	public boolean contains(final @Nullable Data value)
	{
		for(final ChildType child:getChildren())
		{
			if(child.contains(value))
				return true;
		}
		return false;
	}
}