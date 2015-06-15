/*
 * $Id: EntityId.java 1202 2013-10-17 05:40:20Z PSpeed42@gmail.com $
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

package com.simsilica.es;


/**
 *
 *  @author    Paul Speed
 */
public final class EntityId implements Comparable<EntityId> {

    public static final EntityId NULL_ID = new EntityId( Long.MIN_VALUE );
    
    private long id;
    
    public EntityId() {
    }
    
    public EntityId( long id ) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
 
    @Override
    public int compareTo( EntityId other ) {
        if( id < other.id )
            return -1;
        else if( id > other.id )
            return 1;
        return 0;
    }

    @Override
    public int hashCode() {
        return (int)(id^(id>>>32));
    }
 
    @Override
    public boolean equals( Object o ) {
        if( o == this )
            return true;
        if( o == null )
            return false;
        if( o.getClass() != getClass() )
            return false;
        EntityId other = (EntityId)o;
        return id == other.id;            
    }
    
    @Override
    public String toString() {
        return "EntityId[" + id + "]";
    }
}
