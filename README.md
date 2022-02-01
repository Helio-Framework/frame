# Blueprints for Helio components
[![Maven Central](https://img.shields.io/maven-central/v/io.github.helio-ecosystem/helio-blueprints.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.helio-ecosystem%22%20AND%20a:%22helio-blueprints%22) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Blueprints for developing new Helio components


````
<dependency>
  <groupId>io.github.helio-ecosystem</groupId>
  <artifactId>helio-blueprints</artifactId>
  <version>0.4.0</version>
</dependency>
````

### Developing a new component


#### Setting up the `pom.xml` 
The output jar of the component must include all the third-party dependencies. For this purpose we recommend to add the following excerpt in the pom, more information about how to build a *fat jar* can be found in this [article](https://www.baeldung.com/executable-jar-with-maven).

````
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
