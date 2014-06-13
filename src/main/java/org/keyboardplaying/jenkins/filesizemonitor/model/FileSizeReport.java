/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keyboardplaying.jenkins.filesizemonitor.model;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.map.MultiValueMap;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 * @author Victor Andrianjafintrimo <victor.andrianjafintrimo@cgi.com>
 */
// XXX Javadoc
public class FileSizeReport {
    
    private Map<String,Double> filesSize;
    //The map of file of one filter pattern
    private MultiValueMap filterFiles;
    private String filterPattern;
    private double totalMonitoredSize;
    
    public FileSizeReport(Map<String,Double> filesSize){
        this.filesSize = new HashMap<String, Double>();
    }
    
    public FileSizeReport(){}


    public Map<String, Double> getFilesSize() {
        return filesSize;
    }

    public void setFilesSize(Map<String, Double> filesSize) {
        this.filesSize = filesSize;
    }

    public MultiValueMap getFilterFiles() {
        return filterFiles;
    }

    public void setFilterFiles(MultiValueMap filterFiles) {
        this.filterFiles = filterFiles;
    }

    public String getFilterPattern() {
        return filterPattern;
    }

    public void setFilterPattern(String filterPattern) {
        this.filterPattern = filterPattern;
    }

    public void setTotalMonitoredSize(double totalSize) {
        this.totalMonitoredSize = totalSize;
    }
    
    public double getTotalMonitoredSize(){
        return this.totalMonitoredSize;
    }
    
    
    
    
    
    
}
