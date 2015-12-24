/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableIntRandomVariable;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedIntRandomVariableWrapper
<
	ChildType extends IEditableOrderedIntRandomVariable,
	ParentType extends AbstractCompositeIntRandomVariable<?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedIntRandomVariableWrapper
<
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableIntRandomVariable
{
	BridgedIntRandomVariableWrapperEditor<?> _editor;

	public BaseEditableOrderedIntRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedIntRandomVariableWrapperEditor<BaseEditableOrderedIntRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean setProbabilityOf(final int value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final int value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean addProbabilityTo(final int value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final int value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}