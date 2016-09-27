/**
 *
 */
package com.ankamagames.dofus.harvey.generic.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IContinuousBound;
import com.ankamagames.dofus.harvey.engine.generic.sets.interfaces.IIGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IContinuousGenericBound<Data>
extends IContinuousBound<IContinuousGenericBound<Data>>, IIGenericBound<Data>
{}