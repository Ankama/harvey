/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces;

import com.ankamagames.dofus.harvey.engine.exceptions.OverOneProbabilityException;

import org.eclipse.jdt.annotation.NonNullByDefault;

import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIEditableRandomVariable<Data>
{
	void setProbabilityOf(@Nullable Data value, int probability) throws OverOneProbabilityException;
	boolean remove(@Nullable Data value);
	void add(@Nullable Data value, int probability);
	void addProbabilityTo(@Nullable Data value, int delta);
	void removeProbabilityTo(@Nullable Data value, int delta);
}
