/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.singlevalue;

import com.ankamagames.dofus.harvey.engine.behaviours.AbstractRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.WrongContextException;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SingleValueBehaviour<Data, Bridged extends IRandomVariable<Data,?>>
extends AbstractRandomVariableBehaviour<Data, Bridged>
implements ISingleValueBehaviour<Data>
{
	protected @Nullable Data _value;

	public SingleValueBehaviour(@Nullable final Data value)
	{
		super();
		_value = value;
	}

	public SingleValueBehaviour(@Nullable final Data value, final Bridged bridged)
	{
		super(bridged);
		_value = value;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.IProbabilisticValues#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(@Nullable final Data value)
	{
		if(value==null)
			return _value==null;
		if(value.equals(_value))
			return true;
		return false;
	}

	/**
	 * @param context
	 * @return
	 * @throws WrongContextException
	 */
	protected int checkContext(final @Nullable IRandomVariable<Data, ?> context)
			throws WrongContextException
	{
		if(context!=null)
		{
			IRandomVariable<Data, ?> iterator = _bridged;
			while((iterator!=null)&&(!iterator.equals(context)))
				iterator = iterator.getParent();
			if(iterator == null)
				throw new WrongContextException();
		}
		return 0;
	}

	@Override
	public int getProbabilityOf(@Nullable final Data value,
			@Nullable final IRandomVariable<Data, ?> context)
	{
		if((_value==value)||((value!=null)&&(value.equals(_value))))
		{
			return _bridged.getProbability(context);
		}
		return checkContext(context);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.singlevalue.ISingleValueRandomVariable#getValue()
	 */
	@Override
	public @Nullable Data getValue()
	{
		return _value;
	}
}
