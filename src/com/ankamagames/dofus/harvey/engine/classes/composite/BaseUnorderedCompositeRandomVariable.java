/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeRandomVariable
<
	Data,
	ChildType extends BaseRandomVariableWrapper<Data, ?, ?, ?>
>
extends AbstractCompositeRandomVariable<Data, ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}