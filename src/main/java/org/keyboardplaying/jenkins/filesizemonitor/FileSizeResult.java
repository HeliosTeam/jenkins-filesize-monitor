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
package org.keyboardplaying.jenkins.filesizemonitor;

import hudson.model.AbstractBuild;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Map;
import org.keyboardplaying.jenkins.filesizemonitor.model.FileSizeReport;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 * @author Victor Andrianjafintrimo <victor.andrianjafintrimo@cgi.com>
 */
// XXX Javadoc
public class FileSizeResult implements Serializable {
    private int maxSize;
    private boolean hasLimitSize;
    private FileSizeReport report;
    private AbstractBuild<?, ?> owner;
    private transient PrintStream logger;

    public FileSizeResult(FileSizeReport report, AbstractBuild<?, ?> owner,PrintStream logger,int maxSize) {
        this.report = report;
        this.owner = owner;
        this.logger = logger;
        this.maxSize = maxSize;
        this.hasLimitSize = maxSize>0;
    }

    public FileSizeReport getReport() {
        return report;
    }

    public void setReport(FileSizeReport report) {
        this.report = report;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean hasLimitSize() {
        return hasLimitSize;
    }

    public void setHasLimitSize(boolean hasLimitSize) {
        this.hasLimitSize = hasLimitSize;
    }
    
    public AbstractBuild<?, ?> getOwner() {
        return owner;
    }
    
    public int getOverSizedFiles(){
        int res = 0;
        for(String key :report.getFilesSize().keySet()){
            if(report.getFilesSize().get(key)>maxSize){
                res++;
            }
        }
        return res;
    }

    public boolean fileTooBig(String fileName){
        return report.getFilesSize().get(fileName) > maxSize && hasLimitSize;
    }
    
    public String humanReadableByteCount(double bytes) {
        int unit = 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = "KMGTPE".charAt(exp - 1)+"";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}
