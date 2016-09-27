/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes.toolboxes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementaryContinuousGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleContinuousGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ContinuousGenericEqualityToolbox<Data, BridgedSet extends IContinuousGenericSet<Data>>
implements IEqualityToolbox<IContinuousGenericSet<Data>, ISimpleContinuousGenericSet<Data>, IElementaryContinuousGenericSet<Data>, BridgedSet>
{
	BridgedSet _bridged;

	public ContinuousGenericEqualityToolbox(final BridgedSet bridged)
	{
		_bridged = bridged;
	}

	@Override
	public boolean equalsValue(@Nullable final Object obj)
	{
		final IContinuousGenericBound<Data> lowerBound = _bridged.getLowerBound();
		if(lowerBound!=null)
		{
			final Data value = lowerBound.getValue();
			if(value == null)
				return obj == null;
			return value.equals(obj);
		}
		throw new NullPointerException();
	}

	@Override
	public boolean equalsDegenerateSet(final IContinuousGenericSet<Data> set)
	{
		final IContinuousGenericBound<Data> lowerBound = set.getLowerBound();
		if(lowerBound != null)
			return equalsValue(lowerBound.getValue());
		throw new NullPointerException();
	}
}