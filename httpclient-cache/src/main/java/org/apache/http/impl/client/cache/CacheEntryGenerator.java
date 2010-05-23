/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.impl.client.cache;

import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.protocol.HTTP;

/**
 * Generates a {@link CacheEntry} from a {@link HttpResponse}
 *
 * @since 4.1
 */
@Immutable
public class CacheEntryGenerator {

    public CacheEntry generateEntry(Date requestDate, Date responseDate, HttpResponse response,
            byte[] body) {
        Header ct = response.getFirstHeader(HTTP.CONTENT_TYPE);
        Header ce = response.getFirstHeader(HTTP.CONTENT_ENCODING);
        CacheEntity entity = new CacheEntity(
                body,
                ct != null ? ct.getValue() : null,
                ce != null ? ce.getValue() : null);
        return new CacheEntry(requestDate,
                              responseDate,
                              response.getProtocolVersion(),
                              response.getAllHeaders(),
                              entity,
                              response.getStatusLine().getStatusCode(),
                              response.getStatusLine().getReasonPhrase());
    }
}