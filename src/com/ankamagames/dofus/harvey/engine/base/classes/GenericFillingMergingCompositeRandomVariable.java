/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IMergeableRandomVariableWithProbabilityStrategy;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.ICompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.dynamicstrategies.FillingProbability;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericFillingMergingCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>&Iterable<? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>>,
	ChildType extends IMergeableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	Behaviour extends ICompositeBehaviour<Data, ChildType>
>
extends MergingCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, FillingProbability<Data, ParentType>>
{
	public GenericFillingMergingCompositeRandomVariable(@Nullable final ParentType parent, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(parent, behaviour, new FillingProbability<Data, ParentType>());
		getProbabilityStrategy().init(this);
	}

	public GenericFillingMergingCompositeRandomVariable(final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(behaviour, new FillingProbability<Data, ParentType>());
		getProbabilityStrategy().init(this);
	}
}
