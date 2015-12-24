/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeShortRandomVariable<ChildType extends BaseShortRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeShortRandomVariable<ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeShortRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeShortRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}