package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IBoundedInterval;


@NonNullByDefault
public interface IBoundedByteInterval<Elements extends IByteInterval<?>>
	extends IBoundedInterval<IByteSet<?>, Elements>, ILeftBoundedByteInterval<Elements>, IRightBoundedByteInterval<Elements>
{}