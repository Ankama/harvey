/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.doubles.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.doubles.inetrfaces.IIEditableDoubleRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableDoubleRandomVariable
extends IDoubleRandomVariable, IEditableRandomVariable, IIEditableDoubleRandomVariable
{}