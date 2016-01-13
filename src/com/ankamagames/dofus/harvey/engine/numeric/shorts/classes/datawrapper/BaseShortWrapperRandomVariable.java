/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.common.classes.datawrapper.AbstractDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.shorts.interfaces.IShortRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseShortWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractDataWrapperRandomVariable<ProbabilityStrategy>
implements IShortRandomVariable
{
	protected short _value;

	public BaseShortWrapperRandomVariable(final short value,  final ProbabilityStrategy probabilityStrategy)
	{
		super(probabilityStrategy);
		_value = value;
	}

	@Override
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return super.getProbabilityStrategy();
	}

	public short getValue()
	{
		return _value;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(final short value)
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
	public boolean contains(final short value)
	{
		return (!isUnknown()) && (value==_value);
	}

	@Override
	public boolean containsOnly(final short value)
	{
		return contains(value);
	}

	@Override
	public short getOnlyValue()
	{
		return _value;
	}

	@Override
	protected String toStringValues()
	{
		return Short.toString(getValue());
	}
}