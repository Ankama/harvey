/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IRandomVariable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableCompositeRandomVariable
<
	WrappableBaseCollectionType extends IRandomVariable,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
{
	void add(WrappableBaseCollectionType randomVariable, int probability, ProbabilityStrategiesEnum probabilityStrategy);
	boolean remove(final WrappableBaseCollectionType randomVariable);
}