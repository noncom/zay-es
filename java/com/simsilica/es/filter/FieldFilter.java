/*
 * $Id: FieldFilter.java 1202 2013-10-17 05:40:20Z PSpeed42@gmail.com $
 *
 * Copyright (c) 2011-2013 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.simsilica.es.filter;

import com.google.common.base.Objects;
import java.lang.reflect.*;


import com.simsilica.es.ComponentFilter;
import com.simsilica.es.EntityComponent;

/**
 *
 *  @version   $Revision: 1202 $
 *  @author    Paul Speed
 */
public class FieldFilter<T extends EntityComponent> implements ComponentFilter<T> {

    private Class<T> type;
    private Field field;
    private Object value;
    private transient boolean initialized = false;
    
    public FieldFilter() {
    }
    
    public FieldFilter( Class<T> type, String field, Object value ) {
        try {
            this.type = type;
            this.field = type.getDeclaredField(field);
            this.field.setAccessible(true);
            this.value = value;
        } catch( NoSuchFieldException e ) {
            throw new IllegalArgumentException("Field not found:" + field + " on type:" + type, e);
        } 
    }

    public static <T extends EntityComponent> FieldFilter<T> create( Class<T> type, 
                                                                     String field, Object value ) {
        return new FieldFilter<T>(type, field, value);
    }

    public String getFieldName() {
        return field.getName();
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Class<T> getComponentType() {
        return type;
    }
    
    @Override
    public boolean evaluate( EntityComponent c ) {
        if( !type.isInstance(c) ) {
            return false;
        }
        try {
            if( !initialized ) {
                field.setAccessible(true);
                initialized = true;
            }
            Object val = field.get(c);
            return Objects.equal(value, val);
        } catch( IllegalAccessException e ) {
            throw new RuntimeException("Error retrieving field[" + field + "] of:" + c, e);
        } 
    }
    
    @Override
    public String toString() {
        return "FieldFilter[" + field + " == " + value + "]";
    }
}

