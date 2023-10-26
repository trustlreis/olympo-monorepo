# Getting Started

### Release

#### Before

* Be sure you have committed/merged your changes

#### Interactive Mode

* Invoke the Maven Release Plugin
    * Inform the version to be released
    * Inform the versions for the new `SNAPSHOTS`

```shell
mvn release:prepare
mvn release:perform
```

#### Batch Mode

* Just invoke the batch mode using the new `RELEASE` and `DEVELOPMENT` versions

```shell
mvn -B release:prepare -DreleaseVersion=1.0.0 -DdevelopmentVersion=1.0.1-SNAPSHOT
```

* the `TAG` will be generated automatically using the `${project.name}-@{project.version}`

