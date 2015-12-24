/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IEditableOrderedIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedIntWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseOrderedIntWrapperRandomVariable<ProbabilityStrategy>
implements IEditableOrderedIntRandomVariable
{
	public BaseEditableOrderedIntWrapperRandomVariable(final int value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedIntWrapperRandomVariableEditor<BaseEditableOrderedIntWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	BridgedIntWrapperRandomVariableEditor<?> _editor;

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