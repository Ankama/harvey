/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.floats.interfaces;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.floats.inetrfaces.IIEditableFloatRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableFloatRandomVariable
extends IFloatRandomVariable, IEditableRandomVariable, IIEditableFloatRandomVariable
{}