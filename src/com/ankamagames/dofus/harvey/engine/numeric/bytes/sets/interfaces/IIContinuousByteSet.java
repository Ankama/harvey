package com.ankamagames.dofus.harvey.engine.numeric.bytes.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIContinuousByteSet
<
	Set extends IContinuousSet<IContinuousByteBound, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousByteBound, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousByteBound, Set, SimpleSet, ElementarySet>
>
extends ICommonContinuousByteSet<IContinuousByteBound, Set, SimpleSet, ElementarySet>,
IContinuousSet<IContinuousByteBound, Set, SimpleSet, ElementarySet>
{}