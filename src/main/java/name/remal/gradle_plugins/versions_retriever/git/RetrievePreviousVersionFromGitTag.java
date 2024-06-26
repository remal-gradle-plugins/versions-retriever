package name.remal.gradle_plugins.versions_retriever.git;

import java.io.File;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.UntrackedTask;

@UntrackedTask(because = "This task should always fetch from remote Git repository")
public abstract class RetrievePreviousVersionFromGitTag extends AbstractRetrieveVersionsFromGit {

    @Input
    public abstract ListProperty<String> getTagPatterns();

    protected void retrieveImpl(File resultPropertiesFile) {
        createWorkQueue().submit(RetrievePreviousVersionFromGitTagAction.class, params -> {
            params.getResultPropertiesFile().set(resultPropertiesFile);
            params.getProjectDirectory().set(getProjectLayout().getProjectDirectory());
            params.getTagPatterns().set(getTagPatterns());
        });
    }

}
