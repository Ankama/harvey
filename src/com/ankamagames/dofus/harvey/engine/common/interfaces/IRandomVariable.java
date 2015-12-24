package com.ankamagames.dofus.harvey.engine.common.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IRandomVariable
{
	boolean hasOnlyOneValue();
	int getKnownProbability();
	int getUnknownProbability();
	boolean isKnown();
	boolean isUnknown();
}