/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.engine.inetrfaces.IIOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedRandomVariable<Data>
extends IRandomVariable<Data>, IIOrderedRandomVariable<Data>
{}