/**
 *
 */
package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.classes;

import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ByteBound
extends AbstractByteBound<IByteBound>
implements IByteBound
{

	public static  ByteBound makeBound(final byte value)
	{
		return new ByteBound(value);
	}

	protected ByteBound(final byte value)
	{
		super(value);
	}

	@Override
	public boolean isPreceding(final @Nullable IByteBound bound)
	{
		if(bound == null)
			return false;
		final byte predecessor = (byte) (bound.getValue()-1);
		return predecessor == getValue();
	}

	@Override
	public boolean isSucceeding(final @Nullable IByteBound bound)
	{
		if(bound == null)
			return false;
		final byte succcessor = (byte) (bound.getValue()+1);
		return succcessor == getValue();
	}

	@Override
	public String toString()
	{
		return ((Byte)_value).toString();
	}
}