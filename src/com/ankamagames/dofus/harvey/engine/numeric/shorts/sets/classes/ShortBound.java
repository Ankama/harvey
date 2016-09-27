/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.classes;

import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ShortBound
extends AbstractShortBound<IShortBound>
implements IShortBound
{

	public static  ShortBound makeBound(final short value)
	{
		return new ShortBound(value);
	}

	protected ShortBound(final short value)
	{
		super(value);
	}

	@Override
	public boolean isPreceding(final @Nullable IShortBound bound)
	{
		if(bound == null)
			return false;
		final short predecessor = (short) (bound.getValue()-1);
		return predecessor == getValue();
	}

	@Override
	public boolean isSucceeding(final @Nullable IShortBound bound)
	{
		if(bound == null)
			return false;
		final short succcessor = (short) (bound.getValue()+1);
		return succcessor == getValue();
	}

	@Override
	public String toString()
	{
		return ((Short)_value).toString();
	}
}