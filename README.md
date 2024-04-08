# Contentful - Java SDK Implementation

## List of Dependencies
for html processing of rich text

	<repositories>
		<repository>
			<id>jitpack.io</id>
			<url>https://jitpack.io</url>
		</repository>
	</repositories>

		<dependency>
			<groupId>com.github.contentful.rich-text-renderer-java</groupId>
			<artifactId>html</artifactId>
			<version>2.1.0</version>
		</dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-text</artifactId>
            <version>1.10.0</version>
        </dependency>

current version of the contentful SDK

		<dependency>
			<groupId>com.contentful.java</groupId>
			<artifactId>java-sdk</artifactId>
			<version>10.5.18</version>
		</dependency>

for loading the .env

		<dependency>
			<groupId>io.github.cdimascio</groupId>
			<artifactId>dotenv-java</artifactId>
			<version>3.0.0</version>
		</dependency>

## How it works

- First observeAndTransform() lets us take the result and map the contents to a class of our own
- Then the where() clause lets us search for the specific field entry we want
- all() allows us to search all the entries otherwise we would only get a specific one
- blocking means it will only take the first result and only one result otherwise it throws an exception
- iterator and next are necessary since the result is always a collection

For Unwrapping the class must have the @ContentfulEntryModel("contentModelId") annotation where contentModelId
is the ID of the content model that the class is shaped after, similar to using .withContentType().

As far as fields go, the class can have whichever, as long as they belong to that entry.

The @ContentfulSystemField and @ContentfulField("fieldName") annotations
are used for mapping the APIs result to the class, fieldName is the name of the
field you wish to map to that attribute, afterward, the attribute's actual 
name can be whatever.

## Credentials

Credentials can be obtained from the contentful space, simply make an .env file and
fill it with the value of your credentials
