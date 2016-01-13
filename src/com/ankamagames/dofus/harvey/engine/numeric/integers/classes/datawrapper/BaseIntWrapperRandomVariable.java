/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.common.classes.datawrapper.AbstractDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.integers.interfaces.IIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseIntWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractDataWrapperRandomVariable<ProbabilityStrategy>
implements IIntRandomVariable
{
	protected int _value;

	public BaseIntWrapperRandomVariable(final int value,  final ProbabilityStrategy probabilityStrategy)
	{
		super(probabilityStrategy);
		_value = value;
	}

	@Override
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return super.getProbabilityStrategy();
	}

	public int getValue()
	{
		return _value;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(final int value)
	{
		if(contains(value))
		{
			return _probabilityStrategy.getProbability();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(final int value)
	{
		return (!isUnknown()) && (value==_value);
	}

	@Override
	public boolean containsOnly(final int value)
	{
		return contains(value);
	}

	@Override
	public int getOnlyValue()
	{
		return _value;
	}

	@Override
	protected String toStringValues()
	{
		return Integer.toString(getValue());
	}
}