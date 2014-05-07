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

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractDescribableImpl;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Descriptor;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
// XXX Javadoc
public class FileSizeMonitor extends Builder {

    private final List<Entry> paths;

    @DataBoundConstructor
    public FileSizeMonitor(List<Entry> paths) {
        this.paths = paths != null ? new ArrayList<Entry>(paths) : Collections.<Entry>emptyList();
        if (this.paths.isEmpty()) {
            this.paths.add(new Entry(""));
        }
    }

    public List<Entry> getPaths() {
        return Collections.unmodifiableList(paths);
    }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        return true;
    }

    @Extension
    public static final class FileSizeMonitorDescriptor extends BuildStepDescriptor<Builder> {

        /** Creates a new instance. */
        public FileSizeMonitorDescriptor() {
            // Loads the persisted global configuration.
            load();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> type) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Monitor file size";
        }
    }

    public static final class Entry extends AbstractDescribableImpl<Entry> {

        private final String path;

        @DataBoundConstructor
        public Entry(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Entry> {

            @Override
            public String getDisplayName() {
                return "";
            }
        }
    }
}
