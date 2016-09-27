package com.ankamagames.dofus.harvey.engine.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces.IContinuousIntegerBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIContinuousIntegerSet
<
	Set extends IContinuousSet<IContinuousIntegerBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousIntegerBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousIntegerBound, Set, SimpleSet, ElementarySet>
>
extends ICommonContinuousIntegerSet<IContinuousIntegerBound, Set, SimpleSet, ElementarySet>,
IContinuousSet<IContinuousIntegerBound, Set, SimpleSet, ElementarySet>
{}