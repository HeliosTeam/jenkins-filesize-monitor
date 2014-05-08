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
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
// XXX Javadoc
@Extension
public final class FileSizeMonitorDescriptor extends BuildStepDescriptor<Publisher> {

    /** Creates a new instance. */
    public FileSizeMonitorDescriptor() {
        super(FileSizeMonitor.class);
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
