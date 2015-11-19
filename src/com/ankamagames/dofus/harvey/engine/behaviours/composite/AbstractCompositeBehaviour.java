/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import java.util.Set;

import com.ankamagames.dofus.harvey.engine.behaviours.AbstractRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeBehaviour<Data, ChildType extends IRandomVariable<Data, ?>, Bridged extends IRandomVariable<Data, ?>>
	extends AbstractRandomVariableBehaviour<Data, Bridged>
	implements ICompositeBehaviour<Data, ChildType>
{
	public AbstractCompositeBehaviour()
	{
		super();
	}

	public AbstractCompositeBehaviour(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public boolean isEmpty()
	{
		return getSubVariables().isEmpty();
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		for(final ChildType subValue:getSubVariables())
		{
			if(subValue.contains(value))
				return true;
		}
		return false;
	}

	@Override
	public int getProbabilityOf(@Nullable final Data value, @Nullable final IRandomVariable<Data, ?> context)
	{
		int r = 0;
		for(final ChildType subValue:getSubVariables())
		{
			r += subValue.getProbabilityOf(value, context);
		}
		return r;
	}

	@Override
	public abstract Set<ChildType> getSubVariables();
}
