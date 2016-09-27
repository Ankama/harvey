/**
 *
 */
package com.ankamagames.dofus.harvey.generic.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IElementaryEvent;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IDegenerateSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IElementarySortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISimpleSortedGenericSet;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.ISortedGenericSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISortedGenericElementaryEvent<Data, ElementType extends IDegenerateSortedGenericSet<Data>>
extends ISortedGenericRandomVariable<Data, ElementType>,
IElementaryEvent<ISortedGenericSet<Data>, ISimpleSortedGenericSet<Data>, IElementarySortedGenericSet<Data>, IDegenerateSortedGenericSet<Data>, ISortedGenericRandomVariable<Data, ?>, ISortedGenericElementaryEvent<Data, ?>, ElementType>
{}