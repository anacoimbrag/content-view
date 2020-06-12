# ContentView

![Deploy](https://github.com/anacoimbrag/content-view/workflows/Github%20Packages/badge.svg)
[![](https://jitpack.io/v/anacoimbrag/content-view.svg)](https://jitpack.io/#anacoimbrag/content-view)
[![codecov](https://codecov.io/gh/anacoimbrag/content-view/branch/master/graph/badge.svg)](https://codecov.io/gh/anacoimbrag/content-view)

Simple customizable component to show different types of layout. There are 4 default layout types 
to use: *LOADING*, *CONTENT*, *ERROR* and *EMPTY* or you can setup a *Custom* layout.

![ContentView Sample](screenshots/content-view.gif)

## Usage

For a working implementation, please take a look at the [sample](https://github.com/anacoimbrag/content-view/tree/master/sample)

1. Include library
```groovy
// project/build.gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

```groovy
// app/build.gradle
dependencies {
    implementation "com.anacoimbra.android.content-view:$latest_version"
}
```
Latest version is [![](https://jitpack.io/v/anacoimbrag/content-view.svg)](https://jitpack.io/#anacoimbrag/content-view)

### Set layouts
You can set your layout directly on XML, in two ways:
 1. setting up the layout type using `ContentView` attributes (`cv_layout_loading`, `cv_layout_content`, `cv_layout_error` or `cv_layout_empty`).
 2. placing the views as a child, then you need to set `android:tag` in children views to work. You can use custom tags, but it needs to be **convertible to an Integer**. 
```xml
<!-- activity_main.xml -->
<com.anacoimbra.android.contentview.ContentView
    android:id="@+id/contentView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:cv_layout_error="@layout/error_layout"
    app:cv_layout_loading="@layout/loading_layout"
    tools:context=".MainActivity">

    <include layout="@layout/content_layout" />

    <include layout="@layout/empty_layout" />
</com.anacoimbra.android.contentview.ContentView>
``` 

```xml
<!-- content_layout.xml -->
<androidx.appcompat.widget.LinearLayoutCompat 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:tag="@string/cv_layout_type_empty">
    ...
</androidx.appcompat.widget.LinearLayoutCompat>
```

Or you can set programmatically:

```kotlin
contentView.setLayout(R.layout.custom_layout, LAYOUT_TYPE_CUSTOM, false)
```

#### Showing pre configured layout

Show pre defined loading layout and hide all others. If there isn't a loading layout set, the view doesn't change
```kotlin
contentView.showLoading()
```

Show pre defined content layout and hide all others. If there isn't a content layout set, the view doesn't change
```kotlin
contentView.showContent()
```

Show pre defined error layout and hide all others. If there isn't a error layout set, the view doesn't change
```kotlin
contentView.showError()
```

Show pre defined empty layout and hide all others. If there isn't a empty layout set, the view doesn't change
```kotlin
contentView.showEmpty()
```

#### Setting up layout

Just call `setLayout` with your layout and the `type`:
```kotlin
// Using default layout type
contentView.setLayout(R.layout.custom_layout, ContentView.LAYOUT_TYPE_LOADING)
// Using custom layout type
contentView.setLayout(R.layout.custom_layout, LAYOUT_TYPE_CUSTOM, false)
```

Available parameters:
| Parameter | Type | Usage |
| --- |:---:| -----|
| layout        | @LayoutRes Int or View |   |
| type          | Int | One of `ContentView.LAYOUT_TYPE_LOADING`, `ContentView.LAYOUT_TYPE_CONTENT`, `ContentView.LAYOUT_TYPE_ERROR`, `ContentView.LAYOUT_TYPE_EMPTY` or a custom Int value |
| showNow       | Boolean |    Optional |