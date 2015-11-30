/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.parenting;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class ParentingRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIRandomVariableWithProbability
>
	extends AbstractParentingRandomVariable<Data, ChildType>
{
	protected HashSet<ChildType> _subVariables;

	public ParentingRandomVariable(final Collection<ChildType> items)
	{
		_subVariables = new HashSet<ChildType>(items);
	}

	public ParentingRandomVariable()
	{
		_subVariables = new HashSet<ChildType>();
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return Collections.unmodifiableSet(_subVariables);
	}
}