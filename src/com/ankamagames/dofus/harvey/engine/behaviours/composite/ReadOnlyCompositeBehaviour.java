/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import java.util.Collections;
import java.util.Set;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ReadOnlyCompositeBehaviour<Data, ChildType extends IRandomVariable<Data, ?>, Behaviour extends ICompositeBehaviour<Data, ChildType>, Bridged extends IRandomVariable<Data, ?>>
	extends AbstractCompositeBehaviour<Data, ChildType, Bridged>
{
	Behaviour _behaviour;

	public ReadOnlyCompositeBehaviour(final Behaviour behaviour, final Bridged bridged)
	{
		super(bridged);
		_behaviour = behaviour;
	}

	public ReadOnlyCompositeBehaviour(final Behaviour behaviour)
	{
		super();
		_behaviour = behaviour;
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return Collections.unmodifiableSet(_behaviour.getSubVariables());
	}
}
