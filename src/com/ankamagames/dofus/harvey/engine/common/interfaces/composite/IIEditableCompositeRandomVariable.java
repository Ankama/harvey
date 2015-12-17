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
public interface IIEditableCompositeRandomVariable
<
	WrappableBaseCollectionType extends IEditableBasicCollection,
	ProbabilityStrategiesEnum extends Enum<ProbabilityStrategiesEnum>&IBridgedProbabilityStrategyFactory<?, ?>
>
{
	void add(WrappableBaseCollectionType randomVariable, int probability, ProbabilityStrategiesEnum probabilityStrategy);
	boolean remove(final WrappableBaseCollectionType randomVariable);
}