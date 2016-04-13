/**
 * 
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;

/**
 * @author sgros
 *
 */
public interface IElementaryEvent<Set extends ISet<Set>, IterableType extends IElementaryEvent<Set, ?, ?>, ElementType extends IDegenerateSet<Set>>
extends IRandomVariable<Set, IterableType, ElementType>
{}