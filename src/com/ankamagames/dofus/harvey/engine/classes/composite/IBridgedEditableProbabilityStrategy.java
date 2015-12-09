/**
 *
 */
package com.ankamagames.dofus.harvey.engine.classes.composite;

import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IBridgedEditableProbabilityStrategy<BridgedType extends IRandomVariable<?>>
	extends IEditableProbabilityStrategy
{
	void init(BridgedType bridged);
}
