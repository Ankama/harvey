/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseUnorderedCompositeDoubleRandomVariable<ChildType extends BaseDoubleRandomVariableWrapper<?, ?, ?>>
extends AbstractCompositeDoubleRandomVariable<ChildType>
{
	protected HashSet<ChildType> _defaultElements;
	protected HashSet<ChildType> _otherElements;

	protected BaseUnorderedCompositeDoubleRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new HashSet<ChildType>(defaultElements);
		_otherElements = new HashSet<ChildType>(otherElements);
	}

	public BaseUnorderedCompositeDoubleRandomVariable()
	{
		_defaultElements = new HashSet<ChildType>();
		_otherElements = new HashSet<ChildType>();
	}

	@Override
	protected HashSet<ChildType> getDefaultElements()
	{
		return _defaultElements;
	}

	@Override
	protected HashSet<ChildType> getOtherElements()
	{
		return _otherElements;
	}
}