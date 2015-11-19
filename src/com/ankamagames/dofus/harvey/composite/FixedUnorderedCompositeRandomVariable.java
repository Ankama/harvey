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
public class FixedUnorderedCompositeRandomVariable<Data>
extends DefaultUnorderedCompositeRandomVariable<Data, FixedProbability>
{
	public FixedUnorderedCompositeRandomVariable(final int probability, @Nullable final IEditableCompositeRandomVariable<Data, ?, ?> parent) throws OverOneProbabilityException
	{
		super(parent, new FixedProbability(probability));
	}

	public FixedUnorderedCompositeRandomVariable(final int probability) throws OverOneProbabilityException
	{
		super(new FixedProbability(probability));
	}

	public FixedUnorderedCompositeRandomVariable(@Nullable final IEditableCompositeRandomVariable<Data, ?, ?> parent) throws OverOneProbabilityException
	{
		super(parent, new FixedProbability());
	}

	public FixedUnorderedCompositeRandomVariable() throws OverOneProbabilityException
	{
		super(new FixedProbability());
	}
}
