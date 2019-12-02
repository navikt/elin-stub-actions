#!/bin/bash
set -e

echo === ls -la ===
ls -la
echo === end ===

if [ ! -f .is-release-candidate ]
  then
    if [ ! -f .is-not-release-candidate ]
      then
        >&2 echo "ERROR! No verification of artifact is eligable for deploy has been done"
        exit 1;
    fi

    git reset --hard
    echo This is not a release candidate. Will not deploy artifact
    exit 0;
fi

if [ ! -f .new-snapshot-version ]
  then
    >&2 echo .new-snapshot-version is not present
    exit 1;
fi

echo "Running release"
mvn -B --settings maven-settings.xml deploy -Dmaven.wagon.http.pool=false

NEW_SNAPSHOT_VERSION=$(cat $NEW_SNAPSHOT_VERSION_FILE)

# Update to new SNAPSHOT version
echo "Setting SNAPSHOT version: $NEW_SNAPSHOT_VERSION"
mvn -B versions:set -DnewVersion="$NEW_SNAPSHOT_VERSION"
