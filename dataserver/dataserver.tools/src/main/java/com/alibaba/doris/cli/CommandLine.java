/*
 * Copyright(C) 2010-2011 Alibaba Group Holding Limited All rights reserved. Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.alibaba.doris.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * CommandLine
 * 
 * @author Kun He (Raymond He), kun.hek@alibaba-inc.com
 * @since 1.0 2011-7-28
 */
public class CommandLine {

    private Map<String, String> valueMap = new HashMap<String, String>();
    private List<Option>        options;

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public String getValue(String key) {
        String value = valueMap.get(key);

        if (StringUtils.isNotBlank(value)) {
            return value;
        }

        Option option = getOption(key);
        if (null != option) {
            return option.getDefaultValue();
        }

        return null;
    }

    public int getInt(String key) {
        try {
            String s = getValue(key);
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid int arg for " + key);
        }
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }

    protected Option getOption(String key) {
        for (Option option : options) {
            String shortcut = option.getShortcut();
            if (shortcut.endsWith(key)) {
                return option;
            }
        }
        return null;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }

}
