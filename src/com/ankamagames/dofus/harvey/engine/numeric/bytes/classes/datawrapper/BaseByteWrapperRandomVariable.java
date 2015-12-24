/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.common.classes.datawrapper.AbstractDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseByteWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractDataWrapperRandomVariable<ProbabilityStrategy>
implements IByteRandomVariable
{
	protected byte _value;

	public BaseByteWrapperRandomVariable(final byte value,  final ProbabilityStrategy probabilityStrategy)
	{
		super(probabilityStrategy);
		_value = value;
	}

	@Override
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return super.getProbabilityStrategy();
	}

	public byte getValue()
	{
		return _value;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(final byte value)
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
	public boolean contains(final byte value)
	{
		return (!isUnknown()) && (value==_value);
	}

	@Override
	public byte getOnlyValue() throws MultipleValuesException
	{
		if(isKnown())
			return _value;
		throw new MultipleValuesException();
	}

	@Override
	protected String toStringValues()
	{
		return Byte.toString(getValue());
	}

	@Override
	public boolean containsOnly(final byte value)
	{
		return contains(value);
	}
}
