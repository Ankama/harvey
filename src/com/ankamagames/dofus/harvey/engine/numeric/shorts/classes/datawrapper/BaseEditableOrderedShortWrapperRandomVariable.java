/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IEditableOrderedShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedShortWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseOrderedShortWrapperRandomVariable<ProbabilityStrategy>
implements IEditableOrderedShortRandomVariable
{
	public BaseEditableOrderedShortWrapperRandomVariable(final short value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedShortWrapperRandomVariableEditor<BaseEditableOrderedShortWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	BridgedShortWrapperRandomVariableEditor<?> _editor;

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