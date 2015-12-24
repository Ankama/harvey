/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariable<WrappableBaseCollectionType extends IEditableRandomVariable, ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>>
extends IEditableRandomVariable,
IIEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
{}