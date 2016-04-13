/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedContinuousBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedContinuousGenericBound<Data, Bridged extends IContinuousGenericInterval<Data>>
	extends AbstractBridgedContinuousBound<IContinuousGenericSet<Data>, Bridged>
{
	protected @Nullable Data _value;
	
	public BridgedContinuousGenericBound(@Nullable final Data value)
	{
		_value = value;
	}
	
	public BridgedContinuousGenericBound(@Nullable final Data value, final boolean isClosed)
	{
		super(isClosed);
		_value = value;
	}
	
	public @Nullable Data getValue()
	{
		return _value;
	}
}