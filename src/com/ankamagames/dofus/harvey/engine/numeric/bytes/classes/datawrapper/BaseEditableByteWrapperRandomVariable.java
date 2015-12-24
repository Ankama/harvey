/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IEditableByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseEditableByteWrapperRandomVariable<ProbabilityStrategy extends IEditableProbabilityStrategy>
extends BaseByteWrapperRandomVariable<ProbabilityStrategy>
implements IEditableByteRandomVariable
{
	BridgedByteWrapperRandomVariableEditor<?> _editor;

	public BaseEditableByteWrapperRandomVariable(final byte value, final ProbabilityStrategy probabilityStrategy)
	{
		super(value, probabilityStrategy);
		_editor = new BridgedByteWrapperRandomVariableEditor<BaseEditableByteWrapperRandomVariable<ProbabilityStrategy>>(this);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#setProbabilityOf(java.lang.Object, int)
	 */
	@Override
	public boolean setProbabilityOf(final byte value, final int probability)
	{
		return _editor.setProbabilityOf(value, probability);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(final byte value)
	{
		return _editor.remove(value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#addProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean addProbabilityTo(final byte value, final int delta)
	{
		return _editor.addProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#removeProbabilityTo(java.lang.Object, int)
	 */
	@Override
	public boolean removeProbabilityTo(final byte value, final int delta)
	{
		return _editor.removeProbabilityTo(value, delta);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		_editor.clear();
	}
}