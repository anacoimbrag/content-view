# ContentView

![Deploy](https://github.com/anacoimbrag/content-view/workflows/Github%20Packages/badge.svg)
[![](https://jitpack.io/v/anacoimbrag/content-view.svg)](https://jitpack.io/#anacoimbrag/content-view))

Simple customizable component to show different types of layout. There are 4 default layout types 
to use: *LOADING*, *CONTENT*, *ERROR* and *EMPTY* or you can setup a customized one.

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

### Set layouts
You can set your layout directly on XML, setting up the default layout with it's corresponding layout resource:
```xml
<com.anacoimbra.android.contentview.ContentView
        android:id="@+id/content_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_content="@layout/content_layout"
        app:layout_empty="@layout/empty_layout"
        app:layout_error="@layout/error_layout"
        app:layout_loading="@layout/loading_layout" />
``` 

Or you can set programmatically:

```kotlin
contentView.setLayout(R.layout.custom_layout, LAYOUT_TYPE_CUSTOM, false)
```

#### Available methods

Show pre-configured loading layout and hide all others. If there isn't a loading layout set, the view doesn't change
```kotlin
contentView.showLoading()
```

Show pre-configured content layout and hide all others. If there isn't a content layout set, the view doesn't change
```kotlin
contentView.showContent()
```

Show pre-configured error layout and hide all others. If there isn't a error layout set, the view doesn't change
```kotlin
contentView.showError()
```

Show pre-configured empty layout and hide all others. If there isn't a empty layout set, the view doesn't change
```kotlin
contentView.showEmpty()
```

Set Layout for param `type`
```kotlin
// Using default layout type
contentView.setLayout(R.layout.custom_layout, ContentView.LAYOUT_TYPE_LOADING)
// Using custom layout type
contentView.setLayout(R.layout.custom_layout, LAYOUT_TYPE_CUSTOM, false)

fun setLayout(
    @LayoutRes layout: Int, //Layout resource id
    type: Int, // Layout type (Int)
    showNow: Boolean // Show as soon as added to the container
)
```