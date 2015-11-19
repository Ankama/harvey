/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import java.util.HashSet;

import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class UnorderedCompositeRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, ChildType extends IRandomVariable<Data, ?>, ProbabilityStrategy extends IProbabilityStrategy>
extends AbstractCompositeRandomVariable<Data, ParentType, ChildType, UnorderedCompositeBehaviour<Data, ChildType, IRandomVariable<Data,ParentType>>, ProbabilityStrategy>
{
	public UnorderedCompositeRandomVariable(@Nullable final ParentType parent, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, ChildType, IRandomVariable<Data,ParentType>>(), probabilityStrategy);
		_behaviour.init(this);
	}

	public UnorderedCompositeRandomVariable(final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, ChildType, IRandomVariable<Data,ParentType>>(), probabilityStrategy);
	}

	@Override
	public HashSet<ChildType> getSubVariables()
	{
		return _behaviour.getSubVariables();
	}
}