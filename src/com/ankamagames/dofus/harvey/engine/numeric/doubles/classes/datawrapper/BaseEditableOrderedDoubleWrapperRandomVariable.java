/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.doubles.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.doubles.interfaces.IEditableOrderedDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedDoubleWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseOrderedDoubleWrapperRandomVariable<ProbabilityStrategy>
implements IEditableOrderedDoubleRandomVariable
{
	public BaseEditableOrderedDoubleWrapperRandomVariable(final double value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedDoubleWrapperRandomVariableEditor<BaseEditableOrderedDoubleWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	BridgedDoubleWrapperRandomVariableEditor<?> _editor;

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