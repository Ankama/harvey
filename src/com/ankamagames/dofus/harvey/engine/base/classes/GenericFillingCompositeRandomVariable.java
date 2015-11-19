/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.base.interfaces.IEditableRandomVariableWithProbabilityStrategy;
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
public class GenericFillingCompositeRandomVariable
<
	Data,
	ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>&Iterable<? extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>>,
	ChildType extends IEditableRandomVariableWithProbabilityStrategy<Data, ?, ?>,
	Behaviour extends ICompositeBehaviour<Data, ChildType>
>
extends EditableCompositeRandomVariable<Data, ParentType, ChildType, Behaviour, FillingProbability<Data, ParentType>>
{
	public GenericFillingCompositeRandomVariable(@Nullable final ParentType parent, final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(parent, behaviour, new FillingProbability<Data, ParentType>());
		getProbabilityStrategy().init(this);
	}

	public GenericFillingCompositeRandomVariable(final Behaviour behaviour) throws OverOneProbabilityException
	{
		super(behaviour, new FillingProbability<Data, ParentType>());
		getProbabilityStrategy().init(this);
	}
}
