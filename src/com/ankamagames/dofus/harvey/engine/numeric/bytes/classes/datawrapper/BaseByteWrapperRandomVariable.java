/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.bytes.interfaces.IByteRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseByteWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
implements IByteRandomVariable
{
	protected byte _value;
	protected ProbabilityStrategy _probabilityStrategy;

	public BaseByteWrapperRandomVariable(final byte value,  final ProbabilityStrategy probabilityStrategy)
	{
		_value = value;
		_probabilityStrategy = probabilityStrategy;
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
		return (!isEmpty()) && (value==_value);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return _probabilityStrategy.getProbability()==0;
	}
}
