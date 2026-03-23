# Build

This library can be built with JDK 25 and newer.

* Set ```JAVA_HOME``` to JDK 25+
* Setup Maven toolchain:

```xml    
<toolchain>
    <type>jdk</type>
    <provides>
        <version>25</version>
    </provides>
    <configuration>
        <jdkHome>/path/to/jdk-25</jdkHome>
    </configuration>
</toolchain>
```

* Execute:

```shell script
$ mvn clean install
```