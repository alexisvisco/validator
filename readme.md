## Validator - [![CircleCI](https://img.shields.io/circleci/project/github/AlexisVisco/Validator.svg?style=for-the-badge)]()

Validator is a simple library that help you to handle form validation.
It's a painful task that needs to be automated.
Fortunately my lib is here for you. It will save you a lot of time!

### How to use with spark

Import this in your code base: [SparkForm.java](https://github.com/AlexisVisco/Validator/blob/master/src/test/java/impl/SparkForm.java)

```java

 post("/test/", (r, q) -> {
     Map<String, String> check = new Validator(new SparkForm(r))
             .addRule("title -> max_length: 100 | min_length: 10")
             .addRule("price -> range: 0, 99999")
             .addRule("website -> url")
             .addRule("email -> email")
             .addRule("tags -> json_arr | diff: (:1)", new JSONArray().put("hey").put("this is a replacement").toString())
             .check();
     if (!check.isEmpty()) {
         return "Somes errors, check is key/value where key=field and value=error message";
     }
     return "No error here!";
 });
    

```

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