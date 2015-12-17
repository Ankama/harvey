/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeGenericRandomVariable
<
	Data,
	ChildType extends BaseGenericRandomVariableWrapper<Data, ?, ?, ?>
>
extends AbstractCompositeGenericRandomVariable<Data, ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeGenericRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeGenericRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}