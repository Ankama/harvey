package com.ankamagames.dofus.harvey.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IRandomVariable<Data>
{
	int getProbabilityOf(@Nullable Data value);
	boolean contains(@Nullable Data value);
	boolean isEmpty();
}