/**
 * Licensed to Tuplejump Software Pvt. Ltd. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Tuplejump Software Pvt. Ltd. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBinaryObjectInspector;
import org.apache.hadoop.io.BytesWritable;

public class CassandraLazyBinary extends LazyBinary {

  public CassandraLazyBinary(LazyBinaryObjectInspector oi) {
    super(oi);
  }

  public CassandraLazyBinary(LazyBinary other){
    super(other);
    BytesWritable incoming = other.getWritableObject();
    byte[] bytes = new byte[incoming.getLength()];
    System.arraycopy(incoming.getBytes(), 0, bytes, 0, incoming.getLength());
    data = new BytesWritable(bytes);
  }

  @Override
  public void init(ByteArrayRef bytes, int start, int length) {
    byte[] recv = new byte[length];
    System.arraycopy(bytes.getData(), start, recv, 0, length);
    data.set(recv, 0, recv.length);
  }
}
