/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.numeric.floats.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedContinuousBound;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatInterval;
import com.ankamagames.dofus.harvey.numeric.floats.sets.interfaces.IFloatSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedFloatBound<Bridged extends IFloatInterval>
	extends AbstractBridgedContinuousBound<IFloatSet, Bridged>
{
	protected float _value;

	public BridgedFloatBound(final float value)
	{
		_value = value;
	}

	public BridgedFloatBound(final float value, final boolean isClosed)
	{
		super(isClosed);
		_value = value;
	}

	public float getValue()
	{
		return _value;
	}
}