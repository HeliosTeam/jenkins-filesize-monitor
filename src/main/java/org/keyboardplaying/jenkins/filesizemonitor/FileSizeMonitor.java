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

    private final Config config;

    @DataBoundConstructor
    public FileSizeMonitor(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
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

    public static final class Config extends AbstractDescribableImpl<Config> {

        private final List<Entry> entries;

        @DataBoundConstructor
        public Config(List<Entry> entries) {
            this.entries = entries != null ? new ArrayList<Entry>(entries) : Collections.<Entry>emptyList();
        }

        public List<Entry> getEntries() {
            return Collections.unmodifiableList(entries);
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Config> {

            @Override
            public String getDisplayName() {
                return "";
            }
        }
    }

    public static abstract class Entry extends AbstractDescribableImpl<Entry> {
    }

    public static final class FileEntry extends Entry {

        private final String path;

        @DataBoundConstructor
        public FileEntry(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Entry> {

            @Override
            public String getDisplayName() {
                return "File";
            }
        }
    }

    public static final class DirectoryEntry extends Entry {

        private final String path;
        private final String filter;

        @DataBoundConstructor
        public DirectoryEntry(String path, String filter) {
            this.path = path;
            this.filter = filter;
        }

        public String getPath() {
            return path;
        }

        public String getFilter() {
            return filter;
        }

        @Extension
        public static class DescriptorImpl extends Descriptor<Entry> {

            @Override
            public String getDisplayName() {
                return "Directory";
            }
        }
    }
}
