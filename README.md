
# Blueprints for Helio components
[![Maven Central](https://img.shields.io/maven-central/v/io.github.helio-ecosystem/helio-blueprints.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.helio-ecosystem%22%20AND%20a:%22helio-blueprints%22) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Blueprints for developing new Helio components (**use java 11 or above**):

### 1. import the blueprints dependency


````xml 
	<dependency>
		<groupId>io.github.helio-ecosystem</groupId>
		<artifactId>helio-blueprints</artifactId>
		<version>0.4.25</version>
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
			<version>0.4.25</version>
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