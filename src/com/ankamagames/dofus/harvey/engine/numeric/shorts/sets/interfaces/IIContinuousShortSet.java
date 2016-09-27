package com.ankamagames.dofus.harvey.engine.numeric.shorts.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.numeric.shorts.sets.interfaces.IContinuousShortBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIContinuousShortSet
<
	Set extends IContinuousSet<IContinuousShortBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousShortBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousShortBound, Set, SimpleSet, ElementarySet>
>
extends ICommonContinuousShortSet<IContinuousShortBound, Set, SimpleSet, ElementarySet>,
IContinuousSet<IContinuousShortBound, Set, SimpleSet, ElementarySet>
{}