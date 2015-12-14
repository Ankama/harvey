/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableOrderedRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedRandomVariableWrapper
<
	Data,
	ChildType extends IEditableOrderedRandomVariable<Data>,
	ParentType extends AbstractCompositeRandomVariable<Data, ?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedRandomVariableWrapper
<
	Data,
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableRandomVariable<Data>
{
	BridgedRandomVariableWrapperEditor<Data, ?> _editor;

	public BaseEditableOrderedRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedRandomVariableWrapperEditor<Data, BaseEditableOrderedRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>>(this);
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return _editor.containsOnly(value);
	}

	@Override
	public boolean setProbabilityOf(@Nullable final Data value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(@Nullable final Data value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean add(@Nullable final Data value, final int probability)
	{
		return _editor.add(value, probability);
	}

	@Override
	public boolean addProbabilityTo(@Nullable final Data value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(@Nullable final Data value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}
