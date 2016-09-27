/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.bytes.randomVariables.Interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IContinuousElementaryEvent;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteBound;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IDegenerateContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.IElementaryContinuousByteSet;
import com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces.ISimpleContinuousByteSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface IContinuousByteElementaryEvent
extends	IContinuousElementaryEvent<IContinuousByteBound, IContinuousByteSet, ISimpleContinuousByteSet, IElementaryContinuousByteSet, IDegenerateContinuousByteSet, IContinuousByteRandomVariable, IContinuousByteElementaryEvent, IDegenerateContinuousByteSet>
{}
