/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableCompositeRandomVariable<WrappableBaseCollectionType extends IEditableBasicCollection, ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>>
extends IEditableBasicCollection,
IIEditableCompositeRandomVariable<WrappableBaseCollectionType, ProbabilityStrategiesEnum>
{}