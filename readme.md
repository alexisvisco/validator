## Form validator

### Add to Maven/Gradle/Sbt

Maven
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
 
<dependency>
    <groupId>com.github.AlexisVisco</groupId>
    <artifactId>Validator</artifactId>
    <version>LATEST</version>
</dependency>
```

Gradle
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
 
dependencies {
    compile 'com.github.AlexisVisco:Validator:LATEST'
}
```
SBT
```sbt
resolvers += "jitpack" at "https://jitpack.io"
 
libraryDependencies += "com.github.AlexisVisco" % "Validator" % "LATEST"	
```

### How to use with spark

```java
import Validator;
import kwizzy.validation.impl.SparkForm;

import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws Exception {
        post("/test/", (r, q) -> {
            Map<String, String> check = new Validator(new SparkForm(r))
                    .addRule("title", "max_length:100|min_length:(10)")
                    .addRule("price", "range:0,99999")
                    .addRule("website", "url")
                    .addRule("email", "email")
                    .addRule("tags", "json_arr|diff:([\"sample\", \"sample2\"])")
                    .check();
            if (!check.isEmpty()) {
                return "Somes errors, check is key/value where key=field and value=error message";
            }
            return "No error here!";
        });
    }
}
```

