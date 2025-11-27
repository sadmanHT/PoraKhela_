package com.porakhela.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T

    abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResource)
        binding.lifecycleOwner = this
        onInitialize()
    }

    abstract fun onInitialize()

    protected fun showError(message: String) {
        // Simple error display - you can enhance this with Material3 Snackbar if needed
        // Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected fun showLoading(show: Boolean = true) {
        // Override in subclasses to handle loading state
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::binding.isInitialized) {
            binding.unbind()
        }
    }
}