/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.longs.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.common.classes.datawrapper.AbstractDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.exceptions.MultipleValuesException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.numeric.longs.interfaces.ILongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseLongWrapperRandomVariable<ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractDataWrapperRandomVariable<ProbabilityStrategy>
implements ILongRandomVariable
{
	protected long _value;

	public BaseLongWrapperRandomVariable(final long value,  final ProbabilityStrategy probabilityStrategy)
	{
		super(probabilityStrategy);
		_value = value;
	}

	@Override
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return super.getProbabilityStrategy();
	}

	public long getValue()
	{
		return _value;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(final long value)
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
	public boolean contains(final long value)
	{
		return (!isUnknown()) && (value==_value);
	}

	@Override
	public boolean containsOnly(final long value)
	{
		return contains(value);
	}

	@Override
	public long getOnlyValue() throws MultipleValuesException
	{
		if(isKnown())
			return _value;
		throw new MultipleValuesException();
	}

	@Override
	protected String toStringValues()
	{
		return Long.toString(getValue());
	}
}
