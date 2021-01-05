/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.common.sysflag;

public class MessageSysFlag {
    /**
     * 0x表示16进制，0x1用表示2进制中的0001，0x2表示2进制的0002，0x3表示2进制的0003
     *  0x1 << 1 ，位运算，左移1位也就是1*2=2。0x1 << 2 左移两位也就是1*2*2=4
     *  0x2 << 1 , 左移1位也就是2*2=4，0x2 << 2 = 8
     *  0x3 <<1 , 左移1位也就是3*2=6，0x3 << 2 = 16
     *  左移1位，就相当于*2 ，左移动2位，就相当于乘以2的平方，以此类推，其实就是*2的n次方
     */
    public final static int COMPRESSED_FLAG = 0x1;
    public final static int MULTI_TAGS_FLAG = 0x1 << 1;
    public final static int TRANSACTION_NOT_TYPE = 0;
    public final static int TRANSACTION_PREPARED_TYPE = 0x1 << 2;
    public final static int TRANSACTION_COMMIT_TYPE = 0x2 << 2;
    public final static int TRANSACTION_ROLLBACK_TYPE = 0x3 << 2;

    public static int getTransactionValue(final int flag) {
        return flag & TRANSACTION_ROLLBACK_TYPE;
    }

    public static int resetTransactionValue(final int flag, final int type) {
        return (flag & (~TRANSACTION_ROLLBACK_TYPE)) | type;
    }

    public static int clearCompressedFlag(final int flag) {
        return flag & (~COMPRESSED_FLAG);
    }
}
