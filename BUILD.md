# Build

This library can be built with JDK 24 and newer.

* Set ```JAVA_HOME``` to JDK 24+
* Setup Maven toolchain:

```xml    
<toolchain>
    <type>jdk</type>
    <provides>
        <version>24</version>
    </provides>
    <configuration>
        <jdkHome>/path/to/jdk-24</jdkHome>
    </configuration>
</toolchain>
```

* Execute:

```shell script
$ ./mvnw clean install
```