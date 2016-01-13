/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.common.classes.datawrapper.AbstractDataWrapperRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseGenericDataWrapperRandomVariable<Data, ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractDataWrapperRandomVariable<ProbabilityStrategy>
implements IGenericRandomVariable<Data>
{
	protected @Nullable Data _value;

	public BaseGenericDataWrapperRandomVariable(@Nullable final Data value,  final ProbabilityStrategy probabilityStrategy)
	{
		super(probabilityStrategy);
		_value = value;
	}

	@Override
	protected ProbabilityStrategy getProbabilityStrategy()
	{
		return super.getProbabilityStrategy();
	}

	public @Nullable Data getValue()
	{
		return _value;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.interfaces.IRandomVariable#getProbabilityOf(java.lang.Object)
	 */
	@Override
	public int getProbabilityOf(@Nullable final Data value)
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
	public boolean contains(@Nullable final Data value)
	{
		return (!isUnknown()) && ( ((value!=null)&&(value.equals(_value)))||((value==null)&&(_value==null)) );
	}

	@Override
	public boolean containsOnly(@Nullable final Data value)
	{
		return contains(value);
	}

	@Override
	public @Nullable Data getOnlyValue()
	{
		return _value;
	}

	@Override
	protected String toStringValues()
	{
		final Data value = getValue();
		if(value==null)
			return "null";
		return value.toString();
	}
}