package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IIGenericBound<Data>
{
	@Nullable Data getValue();
}