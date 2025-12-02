# SoftwareTestingCourse
This is a repo for a code reviewing lab in the Software Testing course

## File Search Utility

A grep-like Java utility for searching patterns in files.

### Features

- Search for text patterns in files
- Case-sensitive and case-insensitive search options
- Simple command-line interface

### Building the Project

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

### Usage

Basic search (case-sensitive):
```bash
java -cp target/file-search-1.0-SNAPSHOT.jar com.search.FileSearcher <pattern> <file>
```

Case-insensitive search:
```bash
java -cp target/file-search-1.0-SNAPSHOT.jar com.search.FileSearcher -i <pattern> <file>
```

### Examples

Search for "cat" in demo.txt (case-sensitive):
```bash
java -cp target/file-search-1.0-SNAPSHOT.jar com.search.FileSearcher cat demo.txt
```

Search for "cat" in demo.txt (case-insensitive):
```bash
java -cp target/file-search-1.0-SNAPSHOT.jar com.search.FileSearcher -i cat demo.txt
```

### Options

- `-i` : Ignore case distinctions in patterns and data
