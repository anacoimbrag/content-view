package com.anacoimbra.android.contentview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.view.children
import com.anacoimbra.android.contentview.ContentView.Companion.LAYOUT_TYPE_CONTENT
import com.anacoimbra.android.contentview.ContentView.Companion.LAYOUT_TYPE_EMPTY
import com.anacoimbra.android.contentview.ContentView.Companion.LAYOUT_TYPE_ERROR
import com.anacoimbra.android.contentview.ContentView.Companion.LAYOUT_TYPE_LOADING

/**
 * ContentView is a [android.widget.FrameLayout] to show the correct view in the right time.
 * This view has some defaults Layout Types such as [LAYOUT_TYPE_LOADING], [LAYOUT_TYPE_CONTENT], [LAYOUT_TYPE_ERROR], [LAYOUT_TYPE_EMPTY]
 * but it is possible to configure your own customized type with any [Int] value.
 *
 * @author Ana Coimbra
 * @constructor
 */
class ContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    @LayoutRes
    private var loadingLayoutId: Int = 0
        set(value) {
            field = value
            addLayout(field, LAYOUT_TYPE_LOADING)
        }

    @LayoutRes
    private var contentLayoutId: Int = 0
        set(value) {
            field = value
            addLayout(field, LAYOUT_TYPE_CONTENT)
        }

    @LayoutRes
    private var errorLayoutId: Int = 0
        set(value) {
            field = value
            addLayout(field, LAYOUT_TYPE_ERROR)
        }

    @LayoutRes
    private var emptyLayoutId: Int = 0
        set(value) {
            field = value
            addLayout(field, LAYOUT_TYPE_EMPTY)
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContentView)

        loadingLayoutId = typedArray.getResourceId(R.styleable.ContentView_layout_loading, 0)
        contentLayoutId = typedArray.getResourceId(R.styleable.ContentView_layout_content, 0)
        errorLayoutId = typedArray.getResourceId(R.styleable.ContentView_layout_error, 0)
        emptyLayoutId = typedArray.getResourceId(R.styleable.ContentView_layout_empty, 0)

        typedArray.recycle()

        showLoading()
    }

    /**
     * Show pre-configured loading layout and hide all others.
     * If there isn't a loading layout set, the view doesn't change
     * @see setLayout
     */
    fun showLoading() = showLayout(LAYOUT_TYPE_LOADING)

    /**
     * Show pre-configured content layout and hide all others.
     * If there isn't a content layout set, the view doesn't change
     * @see setLayout
     */
    fun showContent() = showLayout(LAYOUT_TYPE_CONTENT)

    /**
     * Show pre-configured error layout and hide all others.
     * If there isn't a error layout set, the view doesn't change
     * @see setLayout
     */
    fun showError() = showLayout(LAYOUT_TYPE_ERROR)

    /**
     * Show pre-configured empty layout and hide all others.
     * If there isn't a empty layout set, the view doesn't change
     * @see setLayout
     */
    fun showEmpty() = showLayout(LAYOUT_TYPE_EMPTY)

    /**
     * Show pre-configured custom layout and hide all others.
     * If there isn't a layout configured to [type], the view doesn't change
     * @see setLayout
     * @param type Type of layout to be shown
     */
    fun showView(type: Int) = showLayout(type)

    /**
     * Adds a layout child with tag of type and presents it if showNow = true
     * @param layoutId The layout resource to be added
     * @param type Layout type can be one of [LAYOUT_TYPE_LOADING], [LAYOUT_TYPE_CONTENT], [LAYOUT_TYPE_ERROR], [LAYOUT_TYPE_EMPTY] or a custom type int
     * @param showNow Whether to present or not this just added layout
     */
    fun setLayout(@LayoutRes layoutId: Int, type: Int, showNow: Boolean = false) {
        when (type) {
            LAYOUT_TYPE_LOADING -> loadingLayoutId = layoutId
            LAYOUT_TYPE_CONTENT -> contentLayoutId = layoutId
            LAYOUT_TYPE_ERROR -> errorLayoutId = layoutId
            LAYOUT_TYPE_EMPTY -> emptyLayoutId = layoutId
            else -> addLayout(layoutId, type)
        }
        if (showNow) showLayout(type)
    }

    private fun addLayout(@LayoutRes layoutId: Int, type: Int) {
        val previousLayout = findViewWithTag<View>(type)
        if (previousLayout != null)
            removeView(previousLayout)
        if (layoutId != 0) {
            val view = LayoutInflater.from(context).inflate(layoutId, this, false)
            view.setVisibility(false)
            view.tag = type
            addView(view)
        }
    }

    private fun showLayout(type: Int) {
        val layoutView = findViewWithTag<View>(type)
        if (layoutView != null) {
            children.iterator().forEach { view ->
                view.setVisibility(view == layoutView)
            }
        }
    }

    private fun View.setVisibility(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    companion object {
        const val LAYOUT_TYPE_LOADING = 1054633244
        const val LAYOUT_TYPE_CONTENT = 1669513305
        const val LAYOUT_TYPE_ERROR = 66247144
        const val LAYOUT_TYPE_EMPTY = 66096429
    }
}