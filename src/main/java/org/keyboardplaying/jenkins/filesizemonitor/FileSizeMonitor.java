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

import org.keyboardplaying.jenkins.filesizemonitor.action.FileSizeBuildAction;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Recorder;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.keyboardplaying.jenkins.filesizemonitor.builder.FileSizeEvaluator;
import org.keyboardplaying.jenkins.filesizemonitor.model.FileSizeReport;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 * @author Victor Andrianjafintrimo <victor.andrianjafintrimo@cgi.com>
 */
// XXX Javadoc
public class FileSizeMonitor extends Recorder {

    private static final String PATH_SEPARATOR = ",";
    private final String pattern;

    @DataBoundConstructor
    public FileSizeMonitor(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        PrintStream logger = listener.getLogger();

        logger.println("[Filesize monitor] paths to monitor: " + getPattern());

        FileSizeEvaluator evaluator = new FileSizeEvaluator(getPattern(), logger);
        FileSizeReport report ;
        try {
            report = build.getWorkspace().act(evaluator);
        } catch (IOException e) {
            e.printStackTrace(logger);
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace(logger);
            return false;
        }

        // The report should be used to generate the result
        final FileSizeResult result = new FileSizeResult(report.getFilesSize()/*(int) (Math.random() * 10) + 1*/, build);
        build.addAction(new FileSizeBuildAction(result));

        return true;
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }
}
