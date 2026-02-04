## SizeTypeValidator (Android Kotlin Validation Library)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

A lightweight, fluent, and production-ready Android Kotlin validation library for validating files, URIs, strings, numbers, and collections.

---

## Features

### File & URI Validation

- File size validation (MB)
- File type validation (image, video, document, audio)
- URI support (content://, gallery, file picker)
- Image resolution validation (width × height)
- Video duration validation (seconds)
- Memory-safe & permission-safe

### General Validation
- String length validation
- Blank / empty string validation
- Number range validation
- List size & emptiness validation
- No unnecessary dependencies

---

## Installation (JitPack)

### 1️⃣ Add JitPack to your **root `settings.gradle` or `build.gradle`**

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Add Dependency
```
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_SizeAndTypeValidator:1.0.0'
	}
```

---

### Usage

**File & URI Validation**

Validate File Size & Type
```kotlin
FileValidator
    .from(file)
    .maxSizeMB(5.0)
    .allowTypes(FileType.IMAGE)
    .validate()
```

**Validate URI (Gallery / File Picker)**

```kotlin
FileValidator
    .from(context, uri)
    .maxSizeMB(5.0)
    .allowTypes(FileType.IMAGE)
    .validate()
```

Supported FileTypes:
```
IMAGE, VIDEO, DOCUMENT, AUDIO
```

**Image Resolution Validation**

```kotlin
FileValidator
    .from(context, uri)
    .allowTypes(FileType.IMAGE)
    .maxImageResolution(
        maxWidth = 1920,
        maxHeight = 1080
    )
    .validate()
```

**Video Duration Validation**

```kotlin
FileValidator
    .from(context, uri)
    .allowTypes(FileType.VIDEO)
    .maxVideoDuration(10) // seconds
    .validate()
```

**String Validation**

```kotlin
StringValidator
    .from("Hello World")
    .minLength(5)
    .maxLength(20)
    .allowBlank(false)
    .validate()
```

**Number Validation**

```kotlin
NumberValidator
    .from(25)
    .min(18)
    .max(60)
    .validate()
```
Supports Int, Float, Double, Long

**List Validation**

```kotlin
ListValidator
    .from(listOf(1, 2, 3))
    .minSize(2)
    .maxSize(5)
    .allowEmpty(false)
    .validate()
```

### Handling Results

```kotlin
when (val result = validator.validate()) {
    is ValidationResult.Success -> {
        // Valid
    }
    is ValidationResult.Error -> {
        Log.e("Validator", result.message)
    }
}
```

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```




