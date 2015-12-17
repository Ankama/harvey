/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableOrderedByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableOrderedByteWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseOrderedByteWrapperRandomVariable<ProbabilityStrategy>
implements IEditableOrderedByteRandomVariable
{
	public BaseEditableOrderedByteWrapperRandomVariable(final byte value,
			final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedByteWrapperRandomVariableEditor<BaseEditableOrderedByteWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	BridgedByteWrapperRandomVariableEditor<?> _editor;

	@Override
	public boolean containsOnly(final byte value)
	{
		return _editor.containsOnly(value);
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
	public boolean add(final byte value, final int probability)
	{
		return _editor.add(value, probability);
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