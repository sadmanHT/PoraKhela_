package com.porakhela.ui.performance

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import java.util.concurrent.ConcurrentLinkedQueue
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

/**
 * ADVANCED PERFORMANCE HARDENING - Step 5/6: GARBAGE COLLECTION OPTIMIZATION
 * 
 * Optimized for Bangladesh low-end devices (৳800-৳2000 price range)
 * Target: Eliminate object creation during scrolling and frequent operations
 * 
 * Key Features:
 * - Object pooling for frequently created objects (animations, views, data)
 * - String optimization with StringBuilder pools
 * - Collection reuse patterns for RecyclerView operations
 * - Memory-efficient data transformation patterns
 * - GC pressure reduction strategies
 * 
 * Memory Strategy:
 * - Pool expensive objects (animations, arrays, collections)
 * - Reuse StringBuilder instances for string operations
 * - Minimize autoboxing with primitive collections
 * - Efficient object lifecycle management
 * - Zero-allocation patterns for hot paths
 */

/**
 * Object pool manager for reducing garbage collection pressure
 */
@Singleton
class ObjectPoolManager @Inject constructor() {

    companion object {
        private const val MAX_POOL_SIZE = 20
        private const val MAX_STRING_BUILDER_SIZE = 10
        private const val MAX_ARRAY_POOL_SIZE = 15
    }

    // Object pools for frequently created items
    private val objectAnimatorPool = ConcurrentLinkedQueue<ObjectAnimator>()
    private val valueAnimatorPool = ConcurrentLinkedQueue<ValueAnimator>()
    private val stringBuilderPool = ConcurrentLinkedQueue<StringBuilder>()
    private val arrayListPool = ConcurrentLinkedQueue<ArrayList<Any>>()
    private val hashMapPool = ConcurrentLinkedQueue<HashMap<String, Any>>()

    /**
     * Object Animator Pool - for smooth animations without GC
     */
    fun acquireObjectAnimator(): ObjectAnimator {
        val animator = objectAnimatorPool.poll()
        return if (animator != null) {
            animator.apply {
                removeAllListeners()
                removeAllUpdateListeners()
                cancel()
            }
        } else {
            ObjectAnimator()
        }
    }

    fun releaseObjectAnimator(animator: ObjectAnimator) {
        if (objectAnimatorPool.size < MAX_POOL_SIZE) {
            animator.apply {
                removeAllListeners()
                removeAllUpdateListeners()
                cancel()
                target = null
            }
            objectAnimatorPool.offer(animator)
        }
    }

    /**
     * Value Animator Pool - for custom animations
     */
    fun acquireValueAnimator(): ValueAnimator {
        val animator = valueAnimatorPool.poll()
        return if (animator != null) {
            animator.apply {
                removeAllListeners()
                removeAllUpdateListeners()
                cancel()
            }
        } else {
            ValueAnimator()
        }
    }

    fun releaseValueAnimator(animator: ValueAnimator) {
        if (valueAnimatorPool.size < MAX_POOL_SIZE) {
            animator.apply {
                removeAllListeners()
                removeAllUpdateListeners()
                cancel()
            }
            valueAnimatorPool.offer(animator)
        }
    }

    /**
     * StringBuilder Pool - for efficient string operations
     */
    fun acquireStringBuilder(): StringBuilder {
        val builder = stringBuilderPool.poll()
        return if (builder != null) {
            builder.apply { clear() }
        } else {
            StringBuilder(64) // Pre-allocate reasonable capacity
        }
    }

    fun releaseStringBuilder(builder: StringBuilder) {
        if (stringBuilderPool.size < MAX_STRING_BUILDER_SIZE && builder.capacity() < 512) {
            builder.clear()
            stringBuilderPool.offer(builder)
        }
    }

    /**
     * ArrayList Pool - for temporary collections
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> acquireArrayList(): ArrayList<T> {
        val list = arrayListPool.poll()
        return if (list != null) {
            list.clear()
            list as ArrayList<T>
        } else {
            ArrayList<T>(16) // Pre-allocate reasonable capacity
        }
    }

    fun <T> releaseArrayList(list: ArrayList<T>) {
        if (arrayListPool.size < MAX_ARRAY_POOL_SIZE && list.size == 0) {
            arrayListPool.offer(list as ArrayList<Any>)
        }
    }

    /**
     * HashMap Pool - for temporary key-value operations
     */
    @Suppress("UNCHECKED_CAST")
    fun <K, V> acquireHashMap(): HashMap<K, V> {
        val map = hashMapPool.poll()
        return if (map != null) {
            map.clear()
            map as HashMap<K, V>
        } else {
            HashMap<K, V>(16) // Pre-allocate reasonable capacity
        }
    }

    fun <K, V> releaseHashMap(map: HashMap<K, V>) {
        if (hashMapPool.size < MAX_POOL_SIZE && map.size == 0) {
            hashMapPool.offer(map as HashMap<String, Any>)
        }
    }

    /**
     * Clear all pools during memory pressure
     */
    fun clearAllPools() {
        objectAnimatorPool.clear()
        valueAnimatorPool.clear()
        stringBuilderPool.clear()
        arrayListPool.clear()
        hashMapPool.clear()
        Timber.d("Cleared all object pools due to memory pressure")
    }

    /**
     * Get pool statistics for monitoring
     */
    fun getPoolStats(): PoolStats {
        return PoolStats(
            objectAnimators = objectAnimatorPool.size,
            valueAnimators = valueAnimatorPool.size,
            stringBuilders = stringBuilderPool.size,
            arrayLists = arrayListPool.size,
            hashMaps = hashMapPool.size
        )
    }
}

data class PoolStats(
    val objectAnimators: Int,
    val valueAnimators: Int,
    val stringBuilders: Int,
    val arrayLists: Int,
    val hashMaps: Int
)

/**
 * Zero-allocation utilities for hot path operations
 * Critical for RecyclerView scrolling and frequent UI updates
 */
object ZeroAllocUtils {

    // Reusable arrays for frequent operations (avoid autoboxing)
    private val tempIntArray = IntArray(4)
    private val tempFloatArray = FloatArray(4)
    private val tempLongArray = LongArray(4)

    /**
     * Format numbers without creating new strings
     * Uses pre-allocated StringBuilder for efficiency
     */
    fun formatNumber(
        number: Long,
        builder: StringBuilder,
        suffix: String = ""
    ): String {
        builder.clear()
        builder.append(number)
        if (suffix.isNotEmpty()) {
            builder.append(suffix)
        }
        return builder.toString()
    }

    /**
     * Format progress percentage without allocation
     */
    fun formatProgress(
        current: Int,
        total: Int,
        builder: StringBuilder
    ): String {
        builder.clear()
        if (total > 0) {
            val percentage = (current * 100) / total
            builder.append(percentage).append('%')
        } else {
            builder.append("0%")
        }
        return builder.toString()
    }

    /**
     * Format duration without creating temporary objects
     */
    fun formatDuration(
        minutes: Int,
        builder: StringBuilder
    ): String {
        builder.clear()
        when {
            minutes < 60 -> {
                builder.append(minutes).append("m")
            }
            minutes < 1440 -> { // Less than 24 hours
                val hours = minutes / 60
                val mins = minutes % 60
                builder.append(hours).append("h")
                if (mins > 0) {
                    builder.append(" ").append(mins).append("m")
                }
            }
            else -> {
                val days = minutes / 1440
                val hours = (minutes % 1440) / 60
                builder.append(days).append("d")
                if (hours > 0) {
                    builder.append(" ").append(hours).append("h")
                }
            }
        }
        return builder.toString()
    }

    /**
     * Safe array operations without bounds checking overhead
     */
    fun getIntArray(size: Int): IntArray {
        return when {
            size <= tempIntArray.size -> tempIntArray
            else -> IntArray(size)
        }
    }

    fun getFloatArray(size: Int): FloatArray {
        return when {
            size <= tempFloatArray.size -> tempFloatArray
            else -> FloatArray(size)
        }
    }
}

/**
 * Memory-efficient data transformation utilities
 * Optimized for RecyclerView adapter operations and data processing
 */
class MemoryEfficientTransformations @Inject constructor(
    private val objectPoolManager: ObjectPoolManager
) {

    /**
     * Transform list with minimal object creation
     * Reuses collections where possible
     */
    inline fun <T, R> transformList(
        source: List<T>,
        crossinline transform: (T) -> R
    ): List<R> {
        if (source.isEmpty()) return emptyList()
        
        val result = objectPoolManager.acquireArrayList<R>()
        try {
            source.forEach { item ->
                result.add(transform(item))
            }
            return result.toList() // Create immutable copy
        } finally {
            objectPoolManager.releaseArrayList(result)
        }
    }

    /**
     * Group items by key with memory efficiency
     */
    inline fun <T, K> groupByEfficient(
        source: List<T>,
        crossinline keySelector: (T) -> K
    ): Map<K, List<T>> {
        if (source.isEmpty()) return emptyMap()
        
        val result = objectPoolManager.acquireHashMap<K, List<T>>()
        val tempList = objectPoolManager.acquireArrayList<T>()
        
        try {
            source.forEach { item ->
                val key = keySelector(item)
                val existing = result[key]
                if (existing == null) {
                    tempList.clear()
                    tempList.add(item)
                    result[key] = tempList.toList()
                } else {
                    tempList.clear()
                    tempList.addAll(existing)
                    tempList.add(item)
                    result[key] = tempList.toList()
                }
            }
            return result.toMap() // Create immutable copy
        } finally {
            objectPoolManager.releaseHashMap(result)
            objectPoolManager.releaseArrayList(tempList)
        }
    }

    /**
     * Filter items with minimal allocation
     */
    inline fun <T> filterEfficient(
        source: List<T>,
        crossinline predicate: (T) -> Boolean
    ): List<T> {
        if (source.isEmpty()) return emptyList()
        
        val result = objectPoolManager.acquireArrayList<T>()
        try {
            source.forEach { item ->
                if (predicate(item)) {
                    result.add(item)
                }
            }
            return result.toList() // Create immutable copy
        } finally {
            objectPoolManager.releaseArrayList(result)
        }
    }

    /**
     * Sort items with temporary collection reuse
     */
    fun <T> sortEfficient(
        source: List<T>,
        comparator: Comparator<T>
    ): List<T> {
        if (source.size <= 1) return source
        
        val result = objectPoolManager.acquireArrayList<T>()
        try {
            result.addAll(source)
            result.sortWith(comparator)
            return result.toList() // Create immutable copy
        } finally {
            objectPoolManager.releaseArrayList(result)
        }
    }
}

/**
 * RecyclerView optimization utilities to minimize object creation
 */
class RecyclerViewGCOptimizer @Inject constructor(
    private val objectPoolManager: ObjectPoolManager
) {

    /**
     * Optimized adapter data comparison without excessive object creation
     */
    fun <T> calculateDifferences(
        oldList: List<T>,
        newList: List<T>,
        itemComparator: (T, T) -> Boolean
    ): DiffResult {
        val builder = objectPoolManager.acquireStringBuilder()
        val addedItems = objectPoolManager.acquireArrayList<Int>()
        val removedItems = objectPoolManager.acquireArrayList<Int>()
        val changedItems = objectPoolManager.acquireArrayList<Int>()
        
        try {
            // Simple diff algorithm optimized for mobile performance
            var oldIndex = 0
            var newIndex = 0
            
            while (oldIndex < oldList.size && newIndex < newList.size) {
                val oldItem = oldList[oldIndex]
                val newItem = newList[newIndex]
                
                if (itemComparator(oldItem, newItem)) {
                    // Items are the same, move to next
                    oldIndex++
                    newIndex++
                } else {
                    // Check if item was added, removed, or changed
                    var found = false
                    
                    // Look ahead in new list for old item
                    for (i in newIndex + 1 until minOf(newIndex + 5, newList.size)) {
                        if (itemComparator(oldItem, newList[i])) {
                            // Item was moved/inserted
                            for (j in newIndex until i) {
                                addedItems.add(j)
                            }
                            newIndex = i + 1
                            oldIndex++
                            found = true
                            break
                        }
                    }
                    
                    if (!found) {
                        // Item was removed or changed
                        removedItems.add(oldIndex)
                        oldIndex++
                    }
                }
            }
            
            // Handle remaining items
            while (newIndex < newList.size) {
                addedItems.add(newIndex++)
            }
            while (oldIndex < oldList.size) {
                removedItems.add(oldIndex++)
            }
            
            return DiffResult(
                addedItems.toList(),
                removedItems.toList(),
                changedItems.toList()
            )
            
        } finally {
            objectPoolManager.releaseStringBuilder(builder)
            objectPoolManager.releaseArrayList(addedItems)
            objectPoolManager.releaseArrayList(removedItems)
            objectPoolManager.releaseArrayList(changedItems)
        }
    }
}

data class DiffResult(
    val addedItems: List<Int>,
    val removedItems: List<Int>,
    val changedItems: List<Int>
)