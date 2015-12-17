/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.inetrfaces.composite;

import com.ankamagames.dofus.harvey.generic.interfaces.IGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeGenericRandomVariable<Data>
extends IGenericRandomVariable<Data>, IICompositeGenericRandomVariable<Data>
{}