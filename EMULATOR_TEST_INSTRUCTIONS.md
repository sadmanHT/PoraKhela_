# 🇧🇩 EMULATOR PERFORMANCE TEST INSTRUCTIONS

## 🎯 TEST REQUIREMENTS (STRICT COMPLIANCE)

### A. FPS Tests
- **Home screen scroll**: ≥ 55 FPS
- **Lesson screen transitions**: ≥ 50 FPS  
- **Leaderboard scroll**: ≥ 50 FPS

### B. Memory Tests
- **Idle state**: < 140MB
- **Running 3 lessons**: < 170MB
- **After "Download Lessons"**: < 200MB
- **No memory leaks** detected by LeakCanary

### C. Database Speed Tests
- **100 Room inserts**: < 1.5 seconds
- **Query 100 rows**: < 250ms
- **50 concurrent writes**: No lockups detected

### D. Scroll Test
- **Fast lesson list scroll**: ≥ 45 FPS minimum
- **No frame drops** below 45 FPS threshold

---

## 🚀 HOW TO RUN TESTS

### Option 1: Full Automated Test Suite
```kotlin
// Start PerformanceTestActivity
startActivity(Intent(this, PerformanceTestActivity::class.java))
```

### Option 2: Interactive Manual Testing  
```kotlin
// Start EmulatorTestActivity for hands-on testing
startActivity(Intent(this, EmulatorTestActivity::class.java))
```

---

## 📱 STEP-BY-STEP TESTING PROCEDURE

### 1. **Setup Phase**
- Launch `EmulatorTestActivity`
- Enable FPS overlay for visual monitoring
- Wait for baseline metrics to stabilize (2-3 seconds)

### 2. **FPS Testing**
```
🏠 Home Screen Test:
   • Tap "Home Scroll" button
   • Watch FPS overlay - must stay ≥ 55 FPS
   • Test passes if average ≥ 55 FPS

📚 Lesson Transitions Test:
   • Tap "Transitions" button  
   • Monitor smooth animations ≥ 50 FPS
   • Test passes if transitions smooth

🏆 Leaderboard Test:
   • Tap "Leaderboard" button
   • Scroll through rankings ≥ 50 FPS
   • Test passes if scrolling fluid
```

### 3. **Memory Testing**
```
💾 Memory Profile Test:
   • Tap "Run Memory Profile Test"
   • Watch memory usage progression:
     - Idle: Should show < 140MB 
     - 3 Lessons: Should stay < 170MB
     - Download: Should remain < 200MB
   • Test passes if all thresholds met
```

### 4. **Database Testing**
```
🗄️ Database Speed Test:
   • Tap "Run Database Speed Test"
   • Monitor performance metrics:
     - Insert speed: < 1.5 seconds for 100 operations
     - Query speed: < 250ms for 100 rows
     - Concurrency: No lockups during 50 writes
   • Test passes if all operations within limits
```

### 5. **Scroll Performance**
```
📜 Fast Scroll Test:
   • Tap "Test Scroll Performance"
   • Rapidly scroll the lesson list
   • Watch FPS overlay - must maintain ≥ 45 FPS
   • Test passes if no frame drops detected
```

---

## ❌ FAILURE RESPONSE PROTOCOL

### If ANY test fails, immediately:

1. **Stop testing and note failure details**
2. **Apply recommended optimizations:**
   ```
   • Fix slow DB queries → Better indexing
   • Reduce bitmap size → Memory optimization  
   • Optimize threading → Coroutine tuning
   • Lower memory allocations → Object pooling
   ```

3. **Use automated fix system:**
   ```kotlin
   // Tap "Optimize Now" button to apply fixes
   testViewModel.applyPerformanceOptimizations()
   ```

4. **Re-run profiler until ALL metrics pass**
5. **Do NOT proceed until 100% test success**

---

## 🛠️ AUTOMATED FIX RECOMMENDATIONS

### FPS Issues:
- Enable RecyclerView optimization
- Reduce view hierarchy complexity  
- Optimize bitmap loading and caching
- Remove unnecessary overdraw

### Memory Issues:
- Enable object pooling for heavy objects
- Implement lazy loading patterns
- Clear unused caches and references
- Optimize bitmap memory usage

### Database Issues:
- Add strategic database indices
- Enable WAL mode for better concurrency
- Optimize query patterns and batching
- Tune SQLite pragma settings

### Scroll Issues:
- Implement view holder recycling
- Reduce allocation during scroll
- Optimize layout performance
- Enable hardware acceleration

---

## 🎯 DEVICE TARGET SPECIFICATION

**Bangladesh Low-End Devices (৳800-৳2000)**
- **RAM**: 2-4GB
- **CPU**: MediaTek Helio series
- **Storage**: 32-64GB
- **Target**: 60+ FPS smooth performance

---

## 🔧 DEVELOPMENT TOOLS INTEGRATION

### Enable DevTools Dashboard:
```kotlin
// Access real-time performance monitoring
startActivity(Intent(this, DevToolsActivity::class.java))
```

### Features:
- Real-time FPS tracking
- Memory usage monitoring  
- Database query performance
- Thread pool utilization
- Performance history analysis

---

## ✅ SUCCESS CRITERIA

**ALL tests must pass with these metrics:**
- ✅ FPS: Home ≥55, Transitions ≥50, Leaderboard ≥50, Scroll ≥45
- ✅ Memory: Idle <140MB, Lessons <170MB, Download <200MB  
- ✅ Database: Insert <1.5s, Query <250ms, No lockups
- ✅ No memory leaks detected
- ✅ Consistent performance across test runs

**ONLY when ALL criteria are met is the performance hardening complete.**