/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeByteRandomVariable<ChildType extends BaseByteRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeByteRandomVariable<ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeByteRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeByteRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}