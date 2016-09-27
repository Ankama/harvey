package com.ankamagames.dofus.harvey.engine.numeric.longs.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.numeric.longs.sets.interfaces.IContinuousLongBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIContinuousLongSet
<
	Set extends IContinuousSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>
>
extends ICommonContinuousLongSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>,
IContinuousSet<IContinuousLongBound, Set, SimpleSet, ElementarySet>
{}