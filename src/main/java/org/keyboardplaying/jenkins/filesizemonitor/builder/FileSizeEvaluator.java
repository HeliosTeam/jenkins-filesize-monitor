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
package org.keyboardplaying.jenkins.filesizemonitor.builder;

import org.keyboardplaying.jenkins.filesizemonitor.model.FileSizeReport;
import hudson.FilePath.FileCallable;
import hudson.remoting.VirtualChannel;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 * @author Victor Andrianjafintrimo <victor.andrianjafintrimo@cgi.com>
 */
// XXX Javadoc
public class FileSizeEvaluator implements FileCallable<FileSizeReport> {

    private final String filePattern;
    private transient PrintStream logger;
    private MultiValueMap filterFiles;

    public FileSizeEvaluator(String filePattern, PrintStream logger) {
        this.filePattern = filePattern;
        
        this.logger = logger;
    }

    public FileSizeReport invoke(File workspace, VirtualChannel vc) throws IOException, InterruptedException {
        String[] files = findFiles(workspace);
        separateFilesByFilter(files);
        logger.println("Matching files: " + Arrays.toString(files));
        logger.println("Workspace :"+workspace.getAbsolutePath());
        FileSizeReport report = new FileSizeReport(calculateFilesSize(files));
        report.setFilesSize(calculateFilesSize(files));
        return report;
    }

    private String[] findFiles(File workspace) {
        try {
            FileSet fileSet = new FileSet();
            Project antProject = new Project();
            fileSet.setProject(antProject);
            fileSet.setDir(workspace);
            fileSet.setIncludes(filePattern);

            return fileSet.getDirectoryScanner(antProject).getIncludedFiles();
        } catch (BuildException e) {
            return new String[0];
        }
    }
    
    private void separateFilesByFilter(String[] files){
       //TODO
    }
    
    private Map<String,Double> calculateFilesSize(String[] filesName) {
         Set<String> filesSet = new HashSet<String>(Arrays.asList(filesName));
         Map<String,Double> filesSize = new HashMap<String,Double>();
        for(String fileName : filesSet) {
            File file = new File(fileName);
            double size = file.length();
                filesSize.put(fileName, size);
        }
        return filesSize;
    }
}
