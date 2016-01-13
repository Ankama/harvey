/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableDoubleRandomVariable;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedDoubleRandomVariableWrapper
<
	ChildType extends IOrderedDoubleRandomVariable,
	ParentType extends AbstractCompositeDoubleRandomVariable<?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedDoubleRandomVariableWrapper
<
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableDoubleRandomVariable
{
	BridgedDoubleRandomVariableWrapperEditor<?> _editor;

	public BaseEditableOrderedDoubleRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedDoubleRandomVariableWrapperEditor<BaseEditableOrderedDoubleRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean setProbabilityOf(final double value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final double value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean addProbabilityTo(final double value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final double value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}