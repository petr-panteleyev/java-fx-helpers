# Build

This library can be built with JDK 21 and newer.

* Set ```JAVA_HOME``` to JDK 21+
* Setup Maven toolchain:

```xml    
<toolchain>
    <type>jdk</type>
    <provides>
        <version>21</version>
    </provides>
    <configuration>
        <jdkHome>/path/to/jdk-21</jdkHome>
    </configuration>
</toolchain>
```

* Execute:

```shell script
$ ./mvnw clean install
```