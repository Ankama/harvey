/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.ReadOnlyProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericSingleValueRandomVariableView<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends ISingleValueBehaviour<Data>>
extends BaseSingleValueRandomVariable<Data, ParentType, Behaviour, ReadOnlyProbabilityStrategy>
implements IRandomVariableWithBehaviourAndReadOnlyProbabilityStrategy<Data, ParentType, Behaviour>
{
	public GenericSingleValueRandomVariableView(final @Nullable ParentType parent, final Behaviour behaviour, final IProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, behaviour, new ReadOnlyProbabilityStrategy(probabilityStrategy));
	}

	public GenericSingleValueRandomVariableView(final Behaviour behaviour, final IProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(behaviour, new ReadOnlyProbabilityStrategy(probabilityStrategy));
	}
}
