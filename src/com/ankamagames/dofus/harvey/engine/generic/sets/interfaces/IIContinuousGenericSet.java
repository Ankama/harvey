package com.ankamagames.dofus.harvey.engine.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementaryContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleContinuousSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IContinuousGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIContinuousGenericSet
<
	Data,
	Set extends IContinuousSet<IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleContinuousSet<IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementaryContinuousSet<IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>
>
extends ICommonContinuousGenericSet<Data, IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>,
IContinuousSet<IContinuousGenericBound<Data>, Set, SimpleSet, ElementarySet>
{}