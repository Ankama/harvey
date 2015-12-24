/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.IEditableOrderedLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedLongWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseOrderedLongWrapperRandomVariable<ProbabilityStrategy>
implements IEditableOrderedLongRandomVariable
{
	public BaseEditableOrderedLongWrapperRandomVariable(final long value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedLongWrapperRandomVariableEditor<BaseEditableOrderedLongWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	BridgedLongWrapperRandomVariableEditor<?> _editor;

	@Override
	public boolean setProbabilityOf(final long value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	@Override
	public boolean remove(final long value)
	{
		return _editor.remove(value);
	}

	@Override
	public boolean addProbabilityTo(final long value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	@Override
	public boolean removeProbabilityTo(final long value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	@Override
	public void clear()
	{
		_editor.clear();
	}
}