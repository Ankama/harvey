package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import java.util.Set;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IICompositeRandomVariable
<
	Data,
	ChildType extends IRandomVariable<Data>&IIParentedRandomVariable<Data, ?>
>
extends Iterable<ChildType>
{
	Set<ChildType> getSubVariables();
	int size();

	int getProbability(@Nullable IRandomVariable<Data> context);
	int getProbabilityOf(@Nullable Data value, @Nullable IRandomVariable<Data> context);
}