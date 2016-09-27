/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.classes;

import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IIntegerBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class IntegerBound
extends AbstractIntegerBound<IIntegerBound>
implements IIntegerBound
{

	public static  IntegerBound makeBound(final int value)
	{
		return new IntegerBound(value);
	}

	protected IntegerBound(final int value)
	{
		super(value);
	}

	@Override
	public boolean isPreceding(final @Nullable IIntegerBound bound)
	{
		if(bound == null)
			return false;
		final int predecessor = (int) (bound.getValue()-1);
		return predecessor == getValue();
	}

	@Override
	public boolean isSucceeding(final @Nullable IIntegerBound bound)
	{
		if(bound == null)
			return false;
		final int succcessor = (int) (bound.getValue()+1);
		return succcessor == getValue();
	}

	@Override
	public String toString()
	{
		return ((Integer)_value).toString();
	}
}