/**
 *
 */
package com.ankamagames.dofus.harvey.singlevalue;

import com.ankamagames.dofus.harvey.engine.base.classes.GenericSingleValueRandomVariableView;
import com.ankamagames.dofus.harvey.engine.base.interfaces.IterableEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.SingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class SingleValueRandomVariableView<Data>
extends GenericSingleValueRandomVariableView<Data, IterableEditableCompositeRandomVariable<Data, ?, ?>, SingleValueBehaviour<Data, SingleValueRandomVariableView<Data>>>
{
	public SingleValueRandomVariableView(
			@Nullable final Data value,
			final IterableEditableCompositeRandomVariable<Data, ?, ?> parent,
			final IProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(parent, new SingleValueBehaviour<Data, SingleValueRandomVariableView<Data>>(value), probabilityStrategy);
		_behaviour.init(this);
	}

	public SingleValueRandomVariableView(
			@Nullable final Data value,
			final IProbabilityStrategy probabilityStrategy)
			throws OverOneProbabilityException
	{
		super(new SingleValueBehaviour<Data, SingleValueRandomVariableView<Data>>(value), probabilityStrategy);
		_behaviour.init(this);
	}

}
