/*
 * Copyright 2019 Mi&Jack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mijack.ppms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mi&Jack
 */
public class IpUtil {
    public static final int MAX_IP_NUMBER = 255;
    public static final Pattern IP_PATTERN = Pattern.compile("(\\d{1,3}).(\\d{1,3}).(\\d{1,3}).(\\d{1,3})");

    public static Ip parseIp(String clientIp) {
        Matcher matcher = IP_PATTERN.matcher(clientIp);
        if (!matcher.matches()) {
            return null;
        }
        long group0 = Long.valueOf(matcher.group(0));
        long group1 = Long.valueOf(matcher.group(1));
        long group2 = Long.valueOf(matcher.group(2));
        long group3 = Long.valueOf(matcher.group(3));
        if (group0 < 0 || group0 > MAX_IP_NUMBER) {
            return null;
        }
        if (group1 < 0 || group1 > MAX_IP_NUMBER) {
            return null;
        }
        if (group2 < 0 || group2 > MAX_IP_NUMBER) {
            return null;
        }
        if (group3 < 0 || group3 > MAX_IP_NUMBER) {
            return null;
        }
        Ip ip = new Ip();
        ip.setGroup0(group0);
        ip.setGroup1(group1);
        ip.setGroup2(group2);
        ip.setGroup3(group3);
        return ip;
    }


}
