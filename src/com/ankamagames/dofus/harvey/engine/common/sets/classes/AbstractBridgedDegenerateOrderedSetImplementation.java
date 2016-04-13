package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateOrderedSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;


@NonNullByDefault
public abstract class AbstractBridgedDegenerateOrderedSetImplementation<Set extends IOrderedSet<Set>, BridgedType extends IDegenerateOrderedSet<Set>>
extends BridgedDegenerateOrderedSetIsAndHasImplementation<Set, BridgedType>
{
	public AbstractBridgedDegenerateOrderedSetImplementation(final BridgedType bridged)
	{
		super(bridged);
	}

	public abstract boolean isInRange(Set set);

	public abstract boolean isGreaterThan(Set set);

	public abstract boolean isGreaterThanOrEqualTo(Set set);

	public abstract boolean isLowerThan(Set set);

	public abstract boolean isLowerThanOrEqualTo(Set set);
}