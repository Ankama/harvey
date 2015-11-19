/**
 *
 */
package com.ankamagames.dofus.harvey.engine.base.classes;

import com.ankamagames.dofus.harvey.engine.behaviours.singlevalue.ISingleValueBehaviour;
import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.ICompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.ISingleValueRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Holds one value of type Data with associated probability
 *
 * @author sgros
 *
 */
@NonNullByDefault
public class BaseSingleValueRandomVariable<Data, ParentType extends ICompositeRandomVariable<Data, ?, ?>, Behaviour extends ISingleValueBehaviour<Data>, ProbabilityStrategy extends IProbabilityStrategy>
extends BaseRandomVariable<Data, ParentType, Behaviour, ProbabilityStrategy>
implements ISingleValueRandomVariable<Data, ParentType>
{
	public BaseSingleValueRandomVariable(final @Nullable ParentType parent, final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(parent, behaviour, probabilityStrategy);
	}

	public BaseSingleValueRandomVariable(final Behaviour behaviour, final ProbabilityStrategy probabilityStrategy) throws OverOneProbabilityException
	{
		super(behaviour, probabilityStrategy);
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.singlevalue.ISingleValueRandomVariable#getValue()
	 */
	@Override
	public @Nullable Data getValue()
	{
		return _behaviour.getValue();
	}
}