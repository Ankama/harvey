/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableRandomVariable<Set extends ISet<Set>, IterableType extends IRandomVariable<Set, ?, ?>, ElementType extends ISet<Set>>
	extends IRandomVariable<Set, IterableType, ElementType>, IIEditableRandomVariable
{}