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
