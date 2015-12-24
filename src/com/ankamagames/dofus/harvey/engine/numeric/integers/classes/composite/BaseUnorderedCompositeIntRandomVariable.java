/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeIntRandomVariable<ChildType extends BaseIntRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeIntRandomVariable<ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeIntRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeIntRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}