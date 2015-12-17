/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.common.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeByteRandomVariable<ChildType extends BaseByteRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeRandomVariable<ChildType>
implements IByteRandomVariable
{
	@Override
	abstract protected Collection<ChildType> getElements();

	@Override
	public int getProbabilityOf(final byte value)
	{
		int r = 0;
		for(final ChildType element:getElements())
			r+=element.getProbabilityOf(value);
		return r;
	}

	@Override
	public boolean contains(final byte value)
	{
		for(final ChildType element:getElements())
			if(element.contains(value))
				return true;
		return false;
	}
}