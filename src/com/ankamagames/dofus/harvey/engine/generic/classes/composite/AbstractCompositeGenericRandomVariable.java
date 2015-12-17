/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeGenericRandomVariable
<
	Data,
	ChildType extends BaseGenericRandomVariableWrapper<Data, ?, ?, ?>
>
extends AbstractCompositeRandomVariable<ChildType>
implements IGenericRandomVariable<Data>
{
	@Override
	abstract protected Collection<ChildType> getElements();

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
}