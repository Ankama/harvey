package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateContinuousSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IOrderedSet;

@NonNullByDefault
public abstract class AbstractDegenerateContinuousSet<Set extends IOrderedSet<Set>>
extends AbstractDegenerateOrderedSet<Set>
implements IDegenerateContinuousSet<Set>
{}