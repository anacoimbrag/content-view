package com.anacoimbra.android.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contentView.setLayout(R.layout.custom_layout, LAYOUT_TYPE_CUSTOM)
    }

    fun showLayout(view: View) {
        when (view.id) {
            R.id.show_loading -> contentView.showLoading()
            R.id.show_content -> contentView.showContent()
            R.id.showError -> contentView.showError()
            R.id.showEmpty -> contentView.showEmpty()
            R.id.showCustom -> contentView.showView(LAYOUT_TYPE_CUSTOM)
        }
    }

    companion object {
        const val LAYOUT_TYPE_CUSTOM = 10
    }
}