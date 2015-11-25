package com.ankamagames.dofus.harvey.engine.inetrfaces.singlevalues;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IISingleValueRandomVariable<Data>
{
	@Nullable Data getValue();
}
