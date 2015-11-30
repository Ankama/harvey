/**
 *
 */
package com.ankamagames.dofus.harvey.ordered;

import com.ankamagames.dofus.harvey.RandomVariableUtils;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IIRandomVariableDecorator;
import com.ankamagames.dofus.harvey.engine.inetrfaces.IISimpleOrderedRandomVariable;
import com.ankamagames.dofus.harvey.engine.inetrfaces.withprobability.IIRandomVariableWithProbability;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class OrderedDecoratorBridge<Data, Bridged extends IIRandomVariableWithProbability&IIRandomVariableDecorator<Data, ? extends IOrderedRandomVariable<Data>>>
implements IISimpleOrderedRandomVariable<Data>
{
	protected Bridged _bridged;

	public OrderedDecoratorBridge(final Bridged bridged)
	{
		_bridged = bridged;
	}

	@Override
	public int getProbabilityForLowerThan(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(_bridged.getProbability(), _bridged.getDecoratedRandomVariable().getProbabilityForLowerThan(value));
	}

	@Override
	public int getProbabilityForLowerThanOrEqualTo(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(_bridged.getProbability(), _bridged.getDecoratedRandomVariable().getProbabilityForLowerThanOrEqualTo(value));
	}

	@Override
	public int getProbabilityForGreaterThan(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(_bridged.getProbability(), _bridged.getDecoratedRandomVariable().getProbabilityForGreaterThan(value));
	}

	@Override
	public int getProbabilityForGreaterThanOrEqualTo(@Nullable final Data value)
	{
		return RandomVariableUtils.multiplyFixedPrecision(_bridged.getProbability(), _bridged.getDecoratedRandomVariable().getProbabilityForGreaterThanOrEqualTo(value));
	}
}