package com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.ISortedRandomVariable;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IByteRandomVariable
extends ISortedRandomVariable<IByteBound, IByteSet, ISimpleByteSet, IElementaryByteSet, IDegenerateByteSet, IByteRandomVariable, IByteElementaryEvent, IByteSet>
{}
