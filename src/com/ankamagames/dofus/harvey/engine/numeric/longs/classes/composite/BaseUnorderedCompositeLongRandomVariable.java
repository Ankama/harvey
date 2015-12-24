/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeLongRandomVariable<ChildType extends BaseLongRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeLongRandomVariable<ChildType>
{
	protected HashSet<ChildType> _elements;

	protected BaseUnorderedCompositeLongRandomVariable(final Collection<? extends ChildType> elements)
	{
		_elements = new HashSet<ChildType>(elements);
	}

	public BaseUnorderedCompositeLongRandomVariable()
	{
		_elements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getElements()
	{
		return _elements;
	}
}