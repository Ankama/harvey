/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractRandomVariable
	implements IRandomVariable
{
	abstract protected String toStringValues();

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.common.interfaces.IRandomVariable#getUnknownProbability()
	 */
	@Override
	public int getUnknownProbability()
	{
		return RandomVariableUtils.ONE - getKnownProbability();
	}

	@Override
	public boolean isKnown()
	{
		return getKnownProbability()>=RandomVariableUtils.ONE;
	}

	@Override
	public boolean isUnknown()
	{
		return getKnownProbability()<=0;
	}

	@Override
	public String toString()
	{
		final int knownProbability = getKnownProbability();
		if(knownProbability==0)
			return "{}";
		String r = "";
		if(knownProbability!=RandomVariableUtils.ONE)
			r+= RandomVariableUtils.convertToFloat(knownProbability)+" ~ ";

		if(hasOnlyOneValue())
			r+=toStringValues();
		else
			r+="{"+toStringValues()+"}";
		return r;
	}
}
