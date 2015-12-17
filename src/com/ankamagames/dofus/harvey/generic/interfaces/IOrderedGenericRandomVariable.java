/**
 *
 */
package com.ankamagames.dofus.harvey.generic.interfaces;

import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.IIOrderedGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IOrderedGenericRandomVariable<Data>
extends IGenericRandomVariable<Data>, IIOrderedGenericRandomVariable<Data>
{}