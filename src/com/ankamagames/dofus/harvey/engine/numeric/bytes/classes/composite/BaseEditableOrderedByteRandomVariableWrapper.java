/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedByteRandomVariableWrapper
<
	ChildType extends IEditableOrderedByteRandomVariable,
	ParentType extends AbstractCompositeByteRandomVariable<?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedByteRandomVariableWrapper
<
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableByteRandomVariable
{
	BridgedByteRandomVariableWrapperEditor<?> _editor;

	public BaseEditableOrderedByteRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedByteRandomVariableWrapperEditor<BaseEditableOrderedByteRandomVariableWrapper<ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean setProbabilityOf(final byte value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final byte value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean addProbabilityTo(final byte value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final byte value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}