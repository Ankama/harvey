/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseRandomVariableWrapper
<
	Data,
	ChildType extends IRandomVariable<Data>,
	ParentType extends AbstractCompositeRandomVariable<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
implements IRandomVariable<Data>
{
	protected ChildType _element;
	protected ParentType _parent;
	protected ProbabilityStrategy _probabilityStrategy;

	public BaseRandomVariableWrapper(final ChildType element, final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		_element = element;
		_parent = parent;
		_probabilityStrategy = probabilityStrategy;
	}

	@Override
	public int getProbabilityOf(@Nullable final Data value)
	{
		final int proba = _element.getProbabilityOf(value);
		if(proba!=0)
			return RandomVariableUtils.multiplyFixedPrecision(proba, _probabilityStrategy.getProbability());
		return 0;
	}

	@Override
	public boolean contains(@Nullable final Data value)
	{
		if(_probabilityStrategy.getProbability()==0)
			return false;
		return _element.contains(value);
	}

	@Override
	public boolean isEmpty()
	{
		if(_probabilityStrategy.getProbability()==0)
			return true;
		return _element.isEmpty();
	}
}