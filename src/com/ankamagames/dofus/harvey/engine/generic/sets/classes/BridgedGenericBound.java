/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.AbstractBridgedBound;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericInterval;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IOrderedGenericSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedGenericBound<Data, Bridged extends IGenericInterval<Data>>
	extends AbstractBridgedBound<IOrderedGenericSet<Data>, Bridged>
{
	protected @Nullable Data _value;
	
	public BridgedGenericBound(@Nullable final Data value)
	{
		_value = value;
	}
	
	public @Nullable Data getValue()
	{
		return _value;
	}
}