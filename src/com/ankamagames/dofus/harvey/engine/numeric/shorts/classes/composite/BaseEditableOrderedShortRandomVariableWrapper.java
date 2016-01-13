/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableShortRandomVariable;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedShortRandomVariableWrapper
<
	ChildType extends IOrderedShortRandomVariable,
	ParentType extends AbstractCompositeShortRandomVariable<?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedShortRandomVariableWrapper
<
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableShortRandomVariable
{
	BridgedShortRandomVariableWrapperEditor<?> _editor;

	public BaseEditableOrderedShortRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedShortRandomVariableWrapperEditor<BaseEditableOrderedShortRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean setProbabilityOf(final short value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final short value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean addProbabilityTo(final short value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final short value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}