/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractRandomVariableBehaviour<Data, Bridged extends IRandomVariable<Data, ?>>
	implements IRandomVariableBehaviour<Data>
{
	protected Bridged _bridged;

	/**
	 *
	 * If using this constructor, you MUST use the init() method prior to
	 * using the behaviour. Nullity won't be checked.
	 *
	 */
	@SuppressWarnings("null")
	public AbstractRandomVariableBehaviour()
	{
		_bridged = null;
	}

	public AbstractRandomVariableBehaviour(final Bridged bridged)
	{
		_bridged = bridged;
	}

	public void init(final Bridged bridged)
	{
		_bridged = bridged;
	}
}