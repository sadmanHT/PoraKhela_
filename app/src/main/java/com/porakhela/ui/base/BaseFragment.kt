package com.porakhela.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var binding: T

    abstract val layoutResource: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitialize()
    }

    abstract fun onInitialize()

    protected fun showError(message: String) {
        // Simple error display - you can enhance this with Material3 Snackbar if needed
        // Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    protected fun showLoading(show: Boolean = true) {
        // Override in subclasses to handle loading state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::binding.isInitialized) {
            binding.unbind()
        }
    }
}