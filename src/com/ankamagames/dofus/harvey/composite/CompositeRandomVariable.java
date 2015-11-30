/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import java.util.Collection;

import com.ankamagames.dofus.harvey.engine.classes.composite.AbstractCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.classes.parenting.ParentingRandomVariable;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.composite.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.parentedwithprobability.IParentedRandomVariableWithProbability;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class CompositeRandomVariable
<
	Data,
	ParentType extends ICompositeRandomVariable<Data, ParentType, ?>,
	ChildType extends IParentedRandomVariableWithProbability<Data, ?>,
	ProbabilityStrategy extends IProbabilityStrategy
>
extends AbstractCompositeRandomVariable<Data, ParentType, ParentingRandomVariable<Data, ChildType>, ChildType, ProbabilityStrategy>
{
	public CompositeRandomVariable(final Collection<ChildType> items,
			final ParentType parent, final ProbabilityStrategy probabilityStrategy)
	{
		super(parent, new ParentingRandomVariable<Data, ChildType>(items), probabilityStrategy);
	}
}