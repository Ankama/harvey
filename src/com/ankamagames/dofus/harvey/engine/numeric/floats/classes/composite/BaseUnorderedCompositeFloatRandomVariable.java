/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeFloatRandomVariable<ChildType extends BaseFloatRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeFloatRandomVariable<ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeFloatRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeFloatRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}