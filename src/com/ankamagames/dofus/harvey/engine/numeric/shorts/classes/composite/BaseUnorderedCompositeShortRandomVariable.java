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
	protected HashSet<ChildType> _defaultElements;
	protected HashSet<ChildType> _otherElements;

	protected BaseUnorderedCompositeShortRandomVariable(final Collection<? extends ChildType> defaultElements, final Collection<? extends ChildType> otherElements)
	{
		_defaultElements = new HashSet<ChildType>(defaultElements);
		_otherElements = new HashSet<ChildType>(otherElements);
	}

	public BaseUnorderedCompositeShortRandomVariable()
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