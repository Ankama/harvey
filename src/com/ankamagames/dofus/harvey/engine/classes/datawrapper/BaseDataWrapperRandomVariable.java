/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.datawrapper;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseDataWrapperRandomVariable<Data, ProbabilityStrategy extends IProbabilityStrategy>
	implements IRandomVariable<Data>
{
	protected @Nullable Data _value;
	protected ProbabilityStrategy _probabilityStrategy;

	public BaseDataWrapperRandomVariable(@Nullable final Data value,  final ProbabilityStrategy probabilityStrategy)
	{
		_value = value;
		_probabilityStrategy = probabilityStrategy;
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
		return (!isEmpty()) && ( ((value!=null)&&(value.equals(_value)))||((value==null)&&(_value==null)) );
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
