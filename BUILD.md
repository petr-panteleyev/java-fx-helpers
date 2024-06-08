# Build

This library can be built with JDK 17 and newer.

* Set ```JAVA_HOME``` to JDK 17+
* Setup Maven toolchain:

```xml    
<toolchain>
    <type>jdk</type>
    <provides>
        <version>17</version>
    </provides>
    <configuration>
        <jdkHome>/path/to/jdk-17</jdkHome>
    </configuration>
</toolchain>
```

* Execute:

```shell script
$ ./mvnw clean install
```