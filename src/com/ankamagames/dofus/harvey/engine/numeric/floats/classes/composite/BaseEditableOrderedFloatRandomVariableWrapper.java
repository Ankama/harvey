/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IEditableFloatRandomVariable;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedFloatRandomVariableWrapper
<
	ChildType extends IOrderedFloatRandomVariable,
	ParentType extends AbstractCompositeFloatRandomVariable<?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedFloatRandomVariableWrapper
<
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableFloatRandomVariable
{
	BridgedFloatRandomVariableWrapperEditor<?> _editor;

	public BaseEditableOrderedFloatRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedFloatRandomVariableWrapperEditor<BaseEditableOrderedFloatRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean setProbabilityOf(final float value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final float value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean addProbabilityTo(final float value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final float value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}