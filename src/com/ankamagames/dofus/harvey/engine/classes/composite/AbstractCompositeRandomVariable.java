/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeRandomVariable
<
	Data,
	ChildType extends BaseRandomVariableWrapper<Data, ?, ?, ?>
>
implements IRandomVariable<Data>
{
	abstract protected Collection<ChildType> getElements();

	protected Iterator<ChildType> iterator()
	{
		return getElements().iterator();
	}

	@Override
	public int getProbabilityOf(@Nullable final Data value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		for(final ChildType element:getElements())
			if(element.contains(value))
				return true;
		return false;
	}

	@Override
	public boolean isEmpty()
	{
		for(final ChildType element:getElements())
			if(!element.isEmpty())
				return false;
		return true;
	}
}