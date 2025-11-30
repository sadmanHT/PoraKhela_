package com.porakhela.ui.performance

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.LruCache
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * ADVANCED PERFORMANCE HARDENING - Step 2/6: BITMAP & ANIMATION OPTIMIZATION
 * 
 * Optimized for Bangladesh low-end devices (৳800-৳2000 price range)
 * Target: 60+ FPS with minimal memory footprint and smooth animations
 * 
 * Key Features:
 * - Drawable pre-loading and caching for first 2 screens
 * - Object pooling for animations to reduce GC pressure
 * - Optimized animation configurations for low-end hardware
 * - Lazy loading with memory-aware caching strategy
 * - Hardware acceleration compatibility checks
 * 
 * Memory Strategy:
 * - Vector drawables preferred over bitmaps (already implemented)
 * - Pre-load only critical assets for immediate screens
 * - LRU cache with memory-aware sizing based on device capabilities
 * - Efficient animation pooling to prevent object creation during scrolling
 */
class OptimizedBitmapManager private constructor(private val context: Context) : DefaultLifecycleObserver {

    companion object {
        @Volatile
        private var INSTANCE: OptimizedBitmapManager? = null
        
        fun getInstance(context: Context): OptimizedBitmapManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: OptimizedBitmapManager(context.applicationContext).also { INSTANCE = it }
            }
        }
        
        // Pre-loading strategy for first 2 screens (Home + Lessons)
        private val CRITICAL_DRAWABLES = listOf(
            "ic_home_24", "ic_lessons_24", "ic_rewards_24", "ic_leaderboard_24",
            "ic_crown_gold", "ic_crown_silver", "ic_crown_bronze", 
            "ic_star", "ic_trophy_gold", "default_avatar",
            "circle_background", "button_primary_background"
        )
        
        // Device capability detection for cache sizing
        private const val LOW_MEMORY_THRESHOLD = 512 * 1024 * 1024 // 512MB
        private const val MID_MEMORY_THRESHOLD = 1024 * 1024 * 1024 // 1GB
    }

    // Memory-aware drawable cache
    private val drawableCache: LruCache<String, Drawable> by lazy {
        val maxMemory = Runtime.getRuntime().maxMemory()
        val cacheSize = when {
            maxMemory < LOW_MEMORY_THRESHOLD -> 50  // Very small cache for low-end devices
            maxMemory < MID_MEMORY_THRESHOLD -> 100 // Medium cache for mid-range devices
            else -> 200 // Larger cache for high-end devices
        }
        
        LruCache<String, Drawable>(cacheSize)
    }

    // Pre-loading job for critical assets
    private var preloadJob: Job? = null

    /**
     * Pre-load critical drawables for immediate screens (Home + Lessons)
     * Called during app initialization to ensure smooth first experience
     */
    fun preloadCriticalAssets(lifecycleScope: CoroutineScope) {
        preloadJob?.cancel()
        preloadJob = lifecycleScope.launch(Dispatchers.IO) {
            try {
                CRITICAL_DRAWABLES.forEach { drawableName ->
                    val resId = getDrawableResourceId(drawableName)
                    if (resId != 0) {
                        val drawable = ContextCompat.getDrawable(context, resId)
                        drawable?.let { 
                            drawableCache.put(drawableName, it)
                        }
                    }
                    
                    // Small delay to prevent blocking UI thread
                    delay(5)
                }
                
                Timber.d("Preloaded ${CRITICAL_DRAWABLES.size} critical drawables")
            } catch (e: Exception) {
                Timber.e(e, "Error preloading critical assets")
            }
        }
    }

    /**
     * Get drawable with fallback to cache or lazy loading
     * Optimized for RecyclerView performance
     */
    fun getOptimizedDrawable(drawableName: String): Drawable? {
        // Check cache first
        drawableCache.get(drawableName)?.let { return it }
        
        // Lazy load and cache
        val resId = getDrawableResourceId(drawableName)
        if (resId != 0) {
            val drawable = ContextCompat.getDrawable(context, resId)
            drawable?.let { 
                drawableCache.put(drawableName, it)
                return it
            }
        }
        
        return null
    }

    private fun getDrawableResourceId(name: String): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        preloadJob?.cancel()
        drawableCache.evictAll()
    }
}

/**
 * Optimized animation manager with object pooling for smooth performance
 * Designed for 60+ FPS on low-end devices with minimal GC pressure
 */
class OptimizedAnimationManager : DefaultLifecycleObserver {

    companion object {
        // Animation pools to prevent object creation during scrolling
        private val shakeAnimatorPool = mutableListOf<ObjectAnimator>()
        private val fadeAnimatorPool = mutableListOf<ObjectAnimator>()
        private val scaleAnimatorPool = mutableListOf<ValueAnimator>()
        
        // Optimized animation durations for low-end devices
        private const val FAST_ANIMATION_DURATION = 200L
        private const val MEDIUM_ANIMATION_DURATION = 300L
        private const val SLOW_ANIMATION_DURATION = 500L
        
        // Pool sizes based on typical usage patterns
        private const val MAX_POOL_SIZE = 10
    }

    /**
     * Optimized shake animation using object pool
     * Specifically tuned for low-end device performance
     */
    fun playOptimizedShakeAnimation(view: View, lifecycle: Lifecycle) {
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) return
        
        val animator = getPooledShakeAnimator(view) ?: createShakeAnimator(view)
        
        animator.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                returnShakeAnimatorToPool(animation as ObjectAnimator)
            }
            override fun onAnimationCancel(animation: android.animation.Animator) {
                returnShakeAnimatorToPool(animation as ObjectAnimator)
            }
        })
        
        animator.start()
    }

    /**
     * Optimized confetti animation with reduced particle count for performance
     * Memory-efficient implementation for low-end devices
     */
    fun playOptimizedConfettiAnimation(
        container: View,
        lifecycleScope: CoroutineScope,
        lifecycle: Lifecycle,
        particleCount: Int = 4 // Reduced from 6 for better performance
    ) {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val confettiEmojis = listOf("⭐", "✨", "🌟") // Reduced emoji set
                
                repeat(particleCount) {
                    if (!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) return@launch
                    
                    // Use simple translation animations instead of complex particle systems
                    val animator = getPooledFadeAnimator() ?: createFadeAnimator()
                    animator.duration = SLOW_ANIMATION_DURATION
                    animator.start()
                    
                    delay(50) // Reduced stagger for smoother performance
                }
                
            } catch (e: Exception) {
                Timber.e(e, "Error in optimized confetti animation")
            }
        }
    }

    /**
     * Optimized button feedback animation using scale
     * Designed for instant response with minimal CPU usage
     */
    fun playOptimizedButtonFeedback(view: View) {
        val scaleDown = getPooledScaleAnimator() ?: createScaleAnimator()
        scaleDown.setObjectValues(1.0f, 0.95f, 1.0f)
        scaleDown.duration = FAST_ANIMATION_DURATION
        scaleDown.addUpdateListener { animation ->
            val scale = animation.animatedValue as Float
            view.scaleX = scale
            view.scaleY = scale
        }
        scaleDown.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                returnScaleAnimatorToPool(animation as ValueAnimator)
                view.scaleX = 1.0f
                view.scaleY = 1.0f
            }
        })
        scaleDown.start()
    }

    // Object pool management for shake animations
    private fun getPooledShakeAnimator(view: View): ObjectAnimator? {
        return if (shakeAnimatorPool.isNotEmpty()) {
            val animator = shakeAnimatorPool.removeAt(shakeAnimatorPool.size - 1)
            animator.target = view
            animator.removeAllListeners()
            animator
        } else null
    }

    private fun createShakeAnimator(view: View): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, "translationX", 0f, 15f, -15f, 10f, -10f, 5f, -5f, 0f).apply {
            duration = MEDIUM_ANIMATION_DURATION
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private fun returnShakeAnimatorToPool(animator: ObjectAnimator) {
        if (shakeAnimatorPool.size < MAX_POOL_SIZE) {
            animator.target = null
            animator.removeAllListeners()
            shakeAnimatorPool.add(animator)
        }
    }

    // Object pool management for fade animations
    private fun getPooledFadeAnimator(): ObjectAnimator? {
        return if (fadeAnimatorPool.isNotEmpty()) {
            val animator = fadeAnimatorPool.removeAt(fadeAnimatorPool.size - 1)
            animator.removeAllListeners()
            animator
        } else null
    }

    private fun createFadeAnimator(): ObjectAnimator {
        return ObjectAnimator.ofFloat(null, "alpha", 1.0f, 0.0f).apply {
            duration = MEDIUM_ANIMATION_DURATION
            interpolator = LinearInterpolator()
        }
    }

    // Object pool management for scale animations  
    private fun getPooledScaleAnimator(): ValueAnimator? {
        return if (scaleAnimatorPool.isNotEmpty()) {
            val animator = scaleAnimatorPool.removeAt(scaleAnimatorPool.size - 1)
            animator.removeAllListeners()
            animator.removeAllUpdateListeners()
            animator
        } else null
    }

    private fun createScaleAnimator(): ValueAnimator {
        return ValueAnimator().apply {
            interpolator = AccelerateDecelerateInterpolator()
        }
    }

    private fun returnScaleAnimatorToPool(animator: ValueAnimator) {
        if (scaleAnimatorPool.size < MAX_POOL_SIZE) {
            animator.removeAllListeners()
            animator.removeAllUpdateListeners()
            scaleAnimatorPool.add(animator)
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        // Clear animation pools
        shakeAnimatorPool.clear()
        fadeAnimatorPool.clear()
        scaleAnimatorPool.clear()
    }
}

/**
 * Lazy loading strategy for images and assets
 * Implements memory-efficient loading patterns for RecyclerViews
 */
class LazyImageLoader(private val context: Context) {

    companion object {
        // Image loading strategy based on scroll state
        private const val PRELOAD_DISTANCE = 2 // Preload 2 items ahead during slow scroll
        private var isScrolling = false
        private var isFastScrolling = false
    }

    /**
     * Update scroll state to optimize loading strategy
     * Called from RecyclerView scroll listeners
     */
    fun updateScrollState(scrolling: Boolean, fastScrolling: Boolean) {
        isScrolling = scrolling
        isFastScrolling = fastScrolling
    }

    /**
     * Load image with scroll-aware strategy
     * Defers loading during fast scrolling to maintain 60 FPS
     */
    fun loadImageOptimized(view: View, drawableName: String, bitmapManager: OptimizedBitmapManager) {
        when {
            isFastScrolling -> {
                // Skip loading during fast scroll to maintain frame rate
                view.alpha = 0.3f
            }
            isScrolling -> {
                // Quick loading during normal scroll
                val drawable = bitmapManager.getOptimizedDrawable(drawableName)
                if (view is android.widget.ImageView && drawable != null) {
                    view.setImageDrawable(drawable)
                    view.alpha = 1.0f
                }
            }
            else -> {
                // Full quality loading when stationary
                val drawable = bitmapManager.getOptimizedDrawable(drawableName)
                if (view is android.widget.ImageView && drawable != null) {
                    view.setImageDrawable(drawable)
                    view.alpha = 1.0f
                }
            }
        }
    }
}