/**
 *
 */
package com.ankamagames.dofus.harvey.composite;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.staticstrategies.FixedProbability;
import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class FixedMergingUnorderedCompositeRandomVariable<Data>
extends DefaultMergingUnorderedCompositeRandomVariable<Data, FixedProbability>
{
	public FixedMergingUnorderedCompositeRandomVariable(@Nullable final IEditableCompositeRandomVariable<Data, ?, ?> parent, final int probability) throws OverOneProbabilityException
	{
		super(parent, new FixedProbability(probability));
	}

	public FixedMergingUnorderedCompositeRandomVariable(final int probability) throws OverOneProbabilityException
	{
		super(new FixedProbability(probability));
	}

	public FixedMergingUnorderedCompositeRandomVariable(@Nullable final IEditableCompositeRandomVariable<Data, ?, ?> parent) throws OverOneProbabilityException
	{
		super(parent, new FixedProbability());
	}

	public FixedMergingUnorderedCompositeRandomVariable() throws OverOneProbabilityException
	{
		super(new FixedProbability());
	}
}
