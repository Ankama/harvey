/**
 *
 */
package com.ankamagames.dofus.harvey.engine.probabilitystrategies;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IMergeableProbabilityStrategy
	extends IProbabilityStrategy
{
	boolean merge(IProbabilityStrategy other);
}
