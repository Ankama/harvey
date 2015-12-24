/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.floats.interfaces.IEditableOrderedFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedFloatWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseOrderedFloatWrapperRandomVariable<ProbabilityStrategy>
implements IEditableOrderedFloatRandomVariable
{
	public BaseEditableOrderedFloatWrapperRandomVariable(final float value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedFloatWrapperRandomVariableEditor<BaseEditableOrderedFloatWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	BridgedFloatWrapperRandomVariableEditor<?> _editor;

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