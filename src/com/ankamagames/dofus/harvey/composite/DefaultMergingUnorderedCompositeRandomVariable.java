/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.base.classes.MergingCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.composite.UnorderedCompositeBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IMergeableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class DefaultMergingUnorderedCompositeRandomVariable<Data, ProbabilityStrategy extends IProbabilityStrategy>
extends
	MergingCompositeRandomVariable
	<
		Data,
		IEditableCompositeRandomVariable<Data,?,?>,
		IMergeableRandomVariable<Data,?>,
		UnorderedCompositeBehaviour<Data, IMergeableRandomVariable<Data,?>, DefaultMergingUnorderedCompositeRandomVariable<Data, ProbabilityStrategy>>,
		IProbabilityStrategy
	>
{
	public DefaultMergingUnorderedCompositeRandomVariable(final @Nullable IEditableCompositeRandomVariable<Data, ?, ?> parent, final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent, new UnorderedCompositeBehaviour<Data, IMergeableRandomVariable<Data,?>, DefaultMergingUnorderedCompositeRandomVariable<Data, ProbabilityStrategy>>(), probabilityStrategy);
		_behaviour.init(this);
	}

	public DefaultMergingUnorderedCompositeRandomVariable(final ProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(new UnorderedCompositeBehaviour<Data, IMergeableRandomVariable<Data,?>, DefaultMergingUnorderedCompositeRandomVariable<Data, ProbabilityStrategy>>(), probabilityStrategy);
		_behaviour.init(this);
	}
}
