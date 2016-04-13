/**
 * 
 */
package com.ankamagames.dofus.harvey.numeric.bytes.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateOrderedSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IDegenerateByteSet<Elements extends IDegenerateByteSet<?>>
extends IByteSet<Elements>, IDegenerateOrderedSet<IByteSet<?>, Elements>
{
}