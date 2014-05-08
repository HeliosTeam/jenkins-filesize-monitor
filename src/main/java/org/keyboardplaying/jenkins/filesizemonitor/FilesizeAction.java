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

import hudson.model.Action;
import java.io.Serializable;
import org.kohsuke.stapler.StaplerProxy;

/**
 * @author Cyrille Chopelet (http://keyboardplaying.org)
 */
// XXX Javadoc
// TODO i18n
public class FilesizeAction implements Action, Serializable, StaplerProxy {

    private static final String URL = "filesize-monitor";
    private FilesizeResult result;

    public FilesizeAction(FilesizeResult result) {
        this.result = result;
    }

    @Override
    public String getIconFileName() {
        return "/plugin/filesize-monitor/icons/filesize-24.png";
    }

    @Override
    public String getDisplayName() {
        return "Filesize";
    }

    @Override
    public String getUrlName() {
        return URL;
    }

    @Override
    public FilesizeResult getTarget() {
        return result;
    }
}
