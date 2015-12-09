/**
 *
 */
package com.ankamagames.dofus.harvey.engine.inetrfaces.composite;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeRandomVariable<Data>
extends IRandomVariable<Data>, IICompositeRandomVariable<Data>
{}