/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractSet<Set extends ISet<Set>>
	implements ISet<Set>
{
	/**
	 * This is a "trick". Later on the implementation, "this" will always be a
	 * Set, but for now, it isn't. Thus, we create a _me member that will be set
	 * to <i>this</i> when fixing the Set type.
	 */
	protected Set _me;
	
	public AbstractSet()
	{
		_me = getThis();
	}
	
	protected abstract Set getThis();
}
