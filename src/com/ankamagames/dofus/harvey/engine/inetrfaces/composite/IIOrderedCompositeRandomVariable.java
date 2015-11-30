package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IIOrderedCompositeRandomVariable<Data, ParentType, ChildType>
{
	int getProbabilityForLowerThan(@Nullable Data value, @Nullable IRandomVariable<Data> context);
	int getProbabilityForLowerThanOrEqualTo(@Nullable Data value, @Nullable IRandomVariable<Data> context);
	int getProbabilityForGreaterThan(@Nullable Data value, @Nullable IRandomVariable<Data> context);
	int getProbabilityForGreaterThanOrEqualTo(@Nullable Data value, @Nullable IRandomVariable<Data> context);
}
