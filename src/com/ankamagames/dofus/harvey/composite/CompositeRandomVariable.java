/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import java.util.HashSet;
import java.util.Set;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.composite.IParentedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeRandomVariable
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ?, ?>,
	ChildType extends IParentedRandomVariable<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends AbstractCompositeRandomVariable<Data, ParentType, ChildType, ProbabilityStrategy>
{
	protected HashSet<ChildType> _subVariables;

	public CompositeRandomVariable(final ChildType heldRandomVariable,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(heldRandomVariable, parent, probabilityStrategy);
		_subVariables = new HashSet<ChildType>();
	}

	@Override
	public Set<ChildType> getSubVariables()
	{
		return _subVariables;
	}
}