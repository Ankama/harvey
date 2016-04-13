/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariable<WrappableBaseCollectionType extends IRandomVariable, ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>>
extends IEditableRandomVariable,
IIEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
{}