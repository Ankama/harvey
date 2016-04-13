/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IIEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedRandomVariableWrapperEditor
<
	Bridged extends RandomVariableWrapper<?, ?, ? extends IEditableProbabilityStrategy>&IEditableRandomVariable
>
implements IIEditableRandomVariable
{
	private Bridged _bridged;

	public BridgedRandomVariableWrapperEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		getBridged().getProbabilityStrategy().setProbability(0);
	}

	/**
	 * @return the _bridged
	 */
	public Bridged getBridged()
	{
		return _bridged;
	}

	/**
	 * @param _bridged the _bridged to set
	 */
	public void setBridged(final Bridged bridged)
	{
		_bridged = bridged;
	}
}