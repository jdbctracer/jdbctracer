package org.helper.jdbctracer;

import java.io.IOException;
import java.util.Properties;

final class GitRepositoryState {
    /*
    String tags;                    // =${git.tags} // comma separated tag names
    String branch;                  // =${git.branch}
    String dirty;                   // =${git.dirty}
    String remoteOriginUrl;         // =${git.remote.origin.url}

    String commitId;                // =${git.commit.id.full} OR ${git.commit.id}
    String commitIdAbbrev;          // =${git.commit.id.abbrev}
    String describe;                // =${git.commit.id.describe}
    String describeShort;           // =${git.commit.id.describe-short}
    String commitUserName;          // =${git.commit.user.name}
    String commitUserEmail;         // =${git.commit.user.email}
    String commitMessageFull;       // =${git.commit.message.full}
    String commitMessageShort;      // =${git.commit.message.short}
    String commitTime;              // =${git.commit.time}
    String closestTagName;          // =${git.closest.tag.name}
    String closestTagCommitCount;   // =${git.closest.tag.commit.count}

    String buildUserName;           // =${git.build.user.name}
    String buildUserEmail;          // =${git.build.user.email}
    String buildTime;               // =${git.build.time}
    String buildHost;               // =${git.build.host}
    String buildVersion;            // =${git.build.version}
    */
    private final Properties props;

    private GitRepositoryState(Properties p) {
        props = p;
    }

    static GitRepositoryState getGitRepositoryState() throws IOException
    {
       Properties properties = new Properties();
       properties.load(GitRepositoryState.class.getClassLoader().getResourceAsStream("git.properties"));
       return new GitRepositoryState(properties);
    }

    @Override
    public String toString() {
        return props.getProperty("git.build.version") + "-" + props.getProperty("git.commit.id.describe");
    }
}