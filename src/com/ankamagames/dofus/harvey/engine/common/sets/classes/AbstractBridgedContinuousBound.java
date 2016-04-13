/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class AbstractBridgedContinuousBound<Set extends IOrderedSet<Set>, Bridged extends IInterval<Set>>
	extends AbstractBridgedBound<Set, Bridged>
{
	protected boolean _isClosed;

	public AbstractBridgedContinuousBound()
	{
		_isClosed = true;
	}

	public AbstractBridgedContinuousBound(final boolean isClosed)
	{
		_isClosed = isClosed;
	}
	
	public boolean isClosed()
	{
		return _isClosed;
	}
}