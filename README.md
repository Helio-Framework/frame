
# Blueprints for Helio components
[![Maven Central](https://img.shields.io/maven-central/v/io.github.helio-ecosystem/helio-blueprints.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.helio-ecosystem%22%20AND%20a:%22helio-blueprints%22) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Blueprints for developing new Helio components (**use java 11 or above**):

### 1. import the blueprints dependency


````xml 
	<dependency>
		<groupId>io.github.helio-ecosystem</groupId>
		<artifactId>helio-blueprints</artifactId>
		<version>0.4.26</version>
	</dependency>
````

### 2. Add some logging
It is also strongly recommended to add log traces in the component, for this purpose we suggest to add the following dependencies although any log tracker can be used
```xml
	<!-- Loggin -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-api</artifactId>
		<version>${slf4jVersion}</version>
	</dependency>
	<!-- Binding for System.out -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-simple</artifactId>
		<version>${slf4jVersion}</version>
	</dependency>
```


### 3. add plugins for building jars that contain all the third-party dependencies
The output jar of the component must include all the third-party dependencies. For this purpose we recommend to add the following excerpt in the pom, more information about how to build a *fat jar* can be found in this [article](https://www.baeldung.com/executable-jar-with-maven).

````xml
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-assembly-plugin</artifactId>
				<version>3.1.1</version>
				<configuration>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
				<executions>
					<execution>
						<id>make-assembly</id>
						<phase>package</phase>
						<goals>
							<goal>single</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
````

### 4. Putting all together

````xml
	<properties>
		<maven.compiler.target>11</maven.compiler.target>
		<maven.compiler.source>11</maven.compiler.source>
		<maven.compiler.release>11</maven.compiler.release>
		<slf4jVersion>1.6.1</slf4jVersion>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
	</properties>

	<dependencies>
		<dependency>
			<groupId>io.github.helio-ecosystem</groupId>
			<artifactId>helio-blueprints</artifactId>
			<version>0.4.26</version>
		</dependency>
		<!-- Loggin -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${slf4jVersion}</version>
		</dependency>
		<!-- Binding for System.out -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-simple</artifactId>
			<version>${slf4jVersion}</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-assembly-plugin</artifactId>
				<version>3.1.1</version>
				<configuration>
					<descriptorRefs>
						<descriptorRef>jar-with-dependencies</descriptorRef>
					</descriptorRefs>
				</configuration>
				<executions>
					<execution>
						<id>make-assembly</id>
						<phase>package</phase>
						<goals>
							<goal>single</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

````


### Publishing the new component
### Using the component in Helio


### Developing a new Mapping Processor

use `$ref.get('[VAR]')` for enclosing data references in the velocity template, e.g., `$ref.get('$.values.[*].measure')`

### Acknowledgements
This project has been partially funded by:

 | Project       | Grant |
 |   :---:      |      :---      |
 | <img src="https://github.com/helio-ecosystem/helio-ecosystem/assets/4105186/96d6a9bc-b92d-43fe-a921-c2c4cd811a30" height="80"/>  | The European project [VICINITY](https://vicinity2020.eu/index.html) from the European Union's Horizont 2020 research and innovation programme under grant agreement Nº688467. |
 | <img src="https://github.com/helio-ecosystem/helio-ecosystem/assets/4105186/fa127b1d-3b26-46c6-bae7-b193d6753071" height="80"/>  | The European project [BIMERR](https://bimerr.eu/) from the European Union's Horizont 2020 research and innovation programme under grant agreement Nº820621. |
 | <img src="https://github.com/helio-ecosystem/helio-ecosystem/assets/4105186/4475dd8d-fc4d-416c-84e7-ed16b34c86e7" height="80"/>  | The European project [DELTA](https://www.delta-h2020.eu/) from the European Union's Horizont 2020 research and innovation programme under grant agreement Nº688467. |
 | <img src="https://github.com/helio-ecosystem/helio-ecosystem/assets/4105186/c9081c01-69ed-4ba3-aa1a-fddbaaee19c1" height="80"/>   | The European project [AURORAL](https://www.auroral.eu/) from the European Union's Horizont 2020 research and innovation programme under grant agreement Nº101016854. |
 | <img src="https://github.com/helio-ecosystem/helio-ecosystem/assets/4105186/f1cde449-266f-45f4-a5da-e9c6006f5f3f" height="80"/>  | The European project [COGITO](https://cogito-project.eu/) from the European Union's Horizont 2020 research and innovation programme under grant agreement Nº958310. |

