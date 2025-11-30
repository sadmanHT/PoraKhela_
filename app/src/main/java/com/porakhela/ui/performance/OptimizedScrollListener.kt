package com.porakhela.ui.performance

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

/**
 * ADVANCED PERFORMANCE HARDENING - Scroll Optimization Component
 * 
 * Optimized RecyclerView scroll listener that manages image loading and animations
 * during scrolling to maintain 60+ FPS on low-end Bangladeshi devices
 * 
 * Key Features:
 * - Detects fast vs normal scrolling to adjust loading strategy
 * - Pauses/resumes image loading based on scroll state
 * - Integrates with LazyImageLoader for scroll-aware optimizations
 * - Reduces main thread workload during fast scrolling
 */
class OptimizedScrollListener(
    private val lazyImageLoader: LazyImageLoader
) : RecyclerView.OnScrollListener() {

    companion object {
        private const val FAST_SCROLL_VELOCITY_THRESHOLD = 1000 // pixels per second
        private const val SCROLL_STATE_UPDATE_DELAY = 50L // milliseconds
    }

    private var lastScrollTime = 0L
    private var lastScrollY = 0
    private var scrollVelocity = 0f
    private var isScrolling = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                // Scrolling stopped - resume full image loading
                isScrolling = false
                lazyImageLoader.updateScrollState(scrolling = false, fastScrolling = false)
                Timber.d("Scroll idle - resumed image loading")
            }
            RecyclerView.SCROLL_STATE_DRAGGING -> {
                // User started scrolling
                isScrolling = true
                updateScrollState(recyclerView)
            }
            RecyclerView.SCROLL_STATE_SETTLING -> {
                // Fling scroll in progress
                isScrolling = true
                updateScrollState(recyclerView)
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        
        if (isScrolling) {
            updateScrollVelocity(dy)
            updateScrollState(recyclerView)
        }
    }

    private fun updateScrollVelocity(dy: Int) {
        val currentTime = System.currentTimeMillis()
        val timeDelta = currentTime - lastScrollTime
        
        if (timeDelta > 0) {
            scrollVelocity = Math.abs(dy.toFloat() / timeDelta * 1000) // pixels per second
        }
        
        lastScrollTime = currentTime
        lastScrollY = dy
    }

    private fun updateScrollState(recyclerView: RecyclerView) {
        val isFastScrolling = scrollVelocity > FAST_SCROLL_VELOCITY_THRESHOLD
        
        // Update image loader with current scroll state
        lazyImageLoader.updateScrollState(
            scrolling = true,
            fastScrolling = isFastScrolling
        )
        
        if (isFastScrolling) {
            // During fast scrolling, reduce view updates to maintain frame rate
            recyclerView.setItemViewCacheSize(5) // Reduce cache during fast scroll
        } else {
            // Restore normal cache size for smooth scrolling
            recyclerView.setItemViewCacheSize(10)
        }
    }
}

/**
 * Enhanced RecyclerView configuration utility for optimal performance
 * Contains all optimization settings in one place for easy management
 */
object RecyclerViewOptimizer {

    /**
     * Apply comprehensive optimization to RecyclerView for low-end device performance
     * Call this method for every RecyclerView to ensure consistent optimization
     */
    fun optimizeRecyclerView(
        recyclerView: RecyclerView,
        lazyImageLoader: LazyImageLoader,
        viewPool: RecyclerView.RecycledViewPool? = null,
        cacheSize: Int = 10
    ) {
        with(recyclerView) {
            // Basic performance optimizations
            setHasFixedSize(true)
            setItemViewCacheSize(cacheSize)
            
            // Use shared view pool if provided
            viewPool?.let { setRecycledViewPool(it) }
            
            // Disable item animations for smoother scrolling on low-end devices
            itemAnimator = null
            
            // Disable nested scrolling to reduce layout passes
            isNestedScrollingEnabled = false
            
            // Enable drawing cache for better scroll performance
            isDrawingCacheEnabled = true
            setWillNotCacheDrawing(false)
            
            // Add optimized scroll listener
            addOnScrollListener(OptimizedScrollListener(lazyImageLoader))
            
            // Optimize touch handling
            isMotionEventSplittingEnabled = false
        }
        
        Timber.d("Applied comprehensive RecyclerView optimizations")
    }

    /**
     * Pre-warm RecyclerView with empty adapter to initialize internal structures
     * Call during app initialization to reduce first-scroll latency
     */
    fun preWarmRecyclerView(recyclerView: RecyclerView) {
        // Create a temporary empty adapter to initialize internal view structures
        val tempAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(android.widget.TextView(parent.context)) {}
            }
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
            override fun getItemCount(): Int = 0
        }
        
        recyclerView.adapter = tempAdapter
        recyclerView.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        
        // Remove temporary adapter
        recyclerView.adapter = null
        
        Timber.d("Pre-warmed RecyclerView for optimal first-scroll performance")
    }
}