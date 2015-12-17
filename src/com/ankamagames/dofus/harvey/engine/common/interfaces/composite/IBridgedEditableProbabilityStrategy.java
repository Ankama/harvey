/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.interfaces.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IBasicCollection;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IBridgedEditableProbabilityStrategy<BridgedType extends IBasicCollection>
extends IEditableProbabilityStrategy
{
	void init(BridgedType bridged);
}
