package com.anacoimbra.android.contentview

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.N_MR1], manifest = Config.NONE)
internal class ContentViewTest {

    private lateinit var activityController: ActivityController<Activity>

    private lateinit var activity: Activity

    private lateinit var contentView: ContentView

    @Before
    fun setup() {
        activityController = Robolectric.buildActivity(Activity::class.java)
        activity = activityController.get()
        contentView = ContentView(activity)
    }

    @Test
    fun `given ContentView with no layout, when some layout are set, then the view should have the correct childCount`() {
        val loading = View(activity)
        contentView.setLayout(loading, ContentView.LAYOUT_TYPE_LOADING)
        assertEquals(1, contentView.childCount)
        val content = View(activity)
        contentView.setLayout(content, ContentView.LAYOUT_TYPE_CONTENT)
        assertEquals(2, contentView.childCount)
        val error = View(activity)
        contentView.setLayout(error, ContentView.LAYOUT_TYPE_ERROR)
        assertEquals(3, contentView.childCount)
        val empty = View(activity)
        contentView.setLayout(empty, ContentView.LAYOUT_TYPE_EMPTY)
        assertEquals(4, contentView.childCount)
        val custom = View(activity)
        contentView.setLayout(custom, LAYOUT_TYPE_CUSTOM)
        assertEquals(5, contentView.childCount)
    }

    @Test
    fun `given ContentView with a loading layout, then the initial state is hidden, when showLoading is called, then the loading layout show be visible`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_LOADING)
        assertEquals(View.GONE, textView.visibility)
        contentView.showLoading()
        assertEquals(View.VISIBLE, textView.visibility)
    }

    @Test
    fun `given ContentView with a loading layout, then the initial state is hidden, when showContent is called, then the loading layout show stay hidden`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_LOADING)
        assertEquals(View.GONE, textView.visibility)
        contentView.showContent()
        assertEquals(View.GONE, textView.visibility)
    }

    @Test
    fun `given ContentView with a content layout, then the initial state is hidden, when showContent is called, then the content layout show be visible`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_CONTENT)
        assertEquals(View.GONE, textView.visibility)
        contentView.showContent()
        assertEquals(View.VISIBLE, textView.visibility)
    }

    @Test
    fun `given ContentView with a content layout, then the initial state is hidden, when showError is called, then the content layout show stay hidden`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_CONTENT)
        assertEquals(View.GONE, textView.visibility)
        contentView.showError()
        assertEquals(View.GONE, textView.visibility)
    }

    @Test
    fun `given ContentView with an error layout, then the initial state is hidden, when showError is called, then the error layout show be visible`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_ERROR)
        assertEquals(View.GONE, textView.visibility)
        contentView.showError()
        assertEquals(View.VISIBLE, textView.visibility)
    }

    @Test
    fun `given ContentView with an error layout, then the initial state is hidden, when showEmpty is called, then the error layout show stay hidden`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_ERROR)
        assertEquals(View.GONE, textView.visibility)
        contentView.showEmpty()
        assertEquals(View.GONE, textView.visibility)
    }

    @Test
    fun `given ContentView with an empty layout, then the initial state is hidden, when showEmpty is called, then the empty layout show be visible`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_EMPTY)
        assertEquals(View.GONE, textView.visibility)
        contentView.showEmpty()
        assertEquals(View.VISIBLE, textView.visibility)
    }

    @Test
    fun `given ContentView with an empty layout, then the initial state is hidden, when showView is called, then the empty layout show stay hidden`() {
        val textView = View(activity)
        contentView.setLayout(textView, ContentView.LAYOUT_TYPE_EMPTY)
        assertEquals(View.GONE, textView.visibility)
        contentView.showView(LAYOUT_TYPE_CUSTOM)
        assertEquals(View.GONE, textView.visibility)
    }

    @Test
    fun `given ContentView with a custom layout, then the initial state is hidden, when showView is called, then the custom layout show be visible`() {
        val textView = View(activity)
        contentView.setLayout(textView, LAYOUT_TYPE_CUSTOM)
        assertEquals(View.GONE, textView.visibility)
        contentView.showView(LAYOUT_TYPE_CUSTOM)
        assertEquals(View.VISIBLE, textView.visibility)
    }

    @Test
    fun `given ContentView with a custom layout, then the initial state is hidden, when showLoading is called, then the custom layout show stay hidden`() {
        val textView = View(activity)
        contentView.setLayout(textView, LAYOUT_TYPE_CUSTOM)
        assertEquals(View.GONE, textView.visibility)
        contentView.showLoading()
        assertEquals(View.GONE, textView.visibility)
    }

    @Test
    fun `given ContentView, when setLayout is called, then the layout added should be visible`() {
        val textView = View(activity)
        contentView.setLayout(textView, LAYOUT_TYPE_CUSTOM, true)
        assertEquals(View.VISIBLE, textView.visibility)
    }

    companion object {
        private const val LAYOUT_TYPE_CUSTOM = 10
    }
}