/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parenting;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parenting.IParentingRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability
>
implements IParentingRandomVariable<Data, ChildType>
{
	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType subVariable:getSubVariables())
		{
			r += subVariable.getProbabilityOf(value);
		}
		return r;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(@Nullable final Data value)
	{
		for(final ChildType subVariable:getSubVariables())
		{
			if(subVariable.contains(value))
				return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		for(final ChildType subVariable:getSubVariables())
		{
			if(!subVariable.isEmpty())
				return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<ChildType> iterator()
	{
		return getSubVariables().iterator();
	}


	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.composite.IIParentingRandomVariable#size()
	 */
	@Override
	public int size()
	{
		return getSubVariables().size();
	}
}
