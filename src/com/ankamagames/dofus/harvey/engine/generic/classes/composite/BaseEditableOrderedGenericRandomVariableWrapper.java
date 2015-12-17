/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableGenericRandomVariable;
import com.ankamagames.dofus.harvey.generic.interfaces.IEditableOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedGenericRandomVariableWrapper
<
	Data,
	ChildType extends IEditableOrderedGenericRandomVariable<Data>,
	ParentType extends AbstractCompositeGenericRandomVariable<Data, ?>,
	ProbabilityStrategy extends IEditableProbabilityStrategy
>
extends BaseOrderedGenericRandomVariableWrapper
<
	Data,
	ChildType,
	ParentType,
	ProbabilityStrategy
>
implements IEditableGenericRandomVariable<Data>
{
	BridgedGenericRandomVariableWrapperEditor<Data, ?> _editor;

	public BaseEditableOrderedGenericRandomVariableWrapper(final ChildType element,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(element, parent, probabilityStrategy);
		_editor = new BridgedGenericRandomVariableWrapperEditor<Data, BaseEditableOrderedGenericRandomVariableWrapper<Data, ChildType, ParentType, ProbabilityStrategy>>(this);
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
