<!--
  Copyright (c) 2023 Bradley Larrick. All rights reserved.

  Licensed under the Apache License v2.0
  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 -->

# Maven Site Plugin Error Example

The Maven guide for
[Configuring for Reproducible Builds](https://maven.apache.org/guides/mini/guide-reproducible-builds.html)
suggests that `project.build.outputTimestamp` can be set using `${git.commit.time}` from
[git-commit-id-maven-plugin](https://github.com/git-commit-id/git-commit-id-maven-plugin).
This appears to work with `maven-jar-plugin`, but `maven-site-plugin` returns an error.

To see the error, build this project with:

```
mvn clean package site
```

During site generation the following error occurs:

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-site-plugin:4.0.0-M9:site (default-site) on project site-plugin-example: Execution defau
lt-site of goal org.apache.maven.plugins:maven-site-plugin:4.0.0-M9:site failed: Invalid project.build.outputTimestamp value '${git.commit.time}': Tex
t '${git.commit.time}' could not be parsed at index 0
```

As can be seen in the `pom.xml` file, I've also defined the `implementation.build` parameter using git commit values:

```
<implementation.build>${git.commit.id.describe}; ${project.build.outputTimestamp}</implementation.build>
```

Inspecting the `MANIFEST.MF` file in the resulting jar file shows that the git commit values have been properly interpolated:

```agsl
Manifest-Version: 1.0
Created-By: Maven JAR Plugin 3.3.0
Build-Jdk-Spec: 20
Build-Tool: Apache Maven 3.9.3 (21122926829f1ead511c958d89bd2f672198ae9f
 )
Build-Jdk: 20.0.1 (Oracle Corporation)
Build-Os: Windows 10 (10.0; amd64)
Specification-Title: Maven Site Plugin Error Example
Specification-Version: 1.0
Specification-Vendor: Bradley Larrick
Implementation-Title: Maven Site Plugin Error Example
Implementation-Version: 1
Implementation-Vendor: Bradley Larrick
Implementation-Build: aec64af-dirty; 2023-07-15T11:14:51-04:00
Implementation-Vendor-Id: org.natuna.examples
X-Compile-Source-JDK: 17
X-Compile-Target-JDK: 17
```
