/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import java.util.HashSet;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeBehaviour<Data, ChildType extends IRandomVariable<Data, ?>, Bridged extends IRandomVariable<Data, ?>>
	extends AbstractCompositeBehaviour<Data, ChildType, Bridged>
{
	protected HashSet<ChildType> _subVariables = new HashSet<ChildType>();

	public UnorderedCompositeBehaviour()
	{
		super();
	}

	public UnorderedCompositeBehaviour(final Bridged bridged)
	{
		super(bridged);
	}

	@Override
	public HashSet<ChildType> getSubVariables()
	{
		return _subVariables;
	}
}