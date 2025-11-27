# 🧪 **COMPREHENSIVE STREAK ENGINE TEST PLAN**

## **Prerequisites**
- Emulator running: `Medium_Phone_API_36.1` 
- App installed: `com.porakhela` (latest build with fixes)
- Initial setup: Complete onboarding if needed

---

## **A. STREAK ACCURACY TESTS** ✅

### **Test A1: First Lesson Completion → Streak Increase**
1. **Setup**: Start fresh (streak = 0)
2. **Action**: Complete any lesson (Math/English/Science/Social Studies)
3. **Expected**: 
   - Streak increases from 0 → 1
   - Home screen shows "🔥 1 day streak!"
   - Lesson count today = 1

### **Test A2: Second Lesson Same Day → Streak Unchanged**
1. **Setup**: Streak = 1 (from Test A1)
2. **Action**: Complete another lesson
3. **Expected**: 
   - Streak remains at 1 (no increase)
   - Lesson count today = 2
   - Message: "Great progress! Keep learning!"

### **Test A3: App Kill/Reopen → Persistence**
1. **Setup**: Streak = 1
2. **Action**: Kill app completely → Reopen
3. **Expected**: 
   - Streak still shows 1
   - Today's lesson count preserved
   - Time tracking resets but total time persists

---

## **B. TIME TRACKING TESTS** ⏱️

### **Test B1: Start Lesson → Timer Begins**
1. **Action**: Navigate to any lesson → Start lesson
2. **Expected**: Timer begins counting (verify in logs)
3. **Verification**: `adb logcat | grep "Started time tracking"`

### **Test B2: Press Home → Timer Pauses**
1. **Setup**: Timer running in lesson
2. **Action**: Press Android Home button
3. **Expected**: 
   - Timer pauses automatically
   - Time accumulated but tracking stopped
4. **Verification**: `adb logcat | grep "Lifecycle onPause - tracking paused"`

### **Test B3: Return to App → Timer Resumes**
1. **Setup**: App minimized with paused timer
2. **Action**: Return to app (recent apps or launcher)
3. **Expected**: 
   - Timer does NOT auto-resume (fixed behavior)
   - User must manually resume lesson
4. **Verification**: `adb logcat | grep "tracking remains paused"`

### **Test B4: End Lesson → Total Time Updates**
1. **Setup**: Timer running for measurable time (30+ seconds)
2. **Action**: Complete lesson or exit lesson properly
3. **Expected**: 
   - Total time today increases
   - Home screen shows updated time spent
   - Timer stops and resets

---

## **C. STREAK RESET TESTS** ⚡

### **Test C1: Advance Time +24 Hours → Streak Reset**
1. **Setup**: Current streak = 1+, no lesson completed "yesterday"
2. **Action**: 
   ```bash
   adb shell date 112720002025  # Nov 27, 20:00, 2025
   ```
3. **Expected**: 
   - Open app → Streak resets to 0
   - Warning: "Start your learning streak today!"
   - Previous day marked as missed

### **Test C2: Advance Time +48 Hours → Streak Reset**
1. **Setup**: Current streak = 1+
2. **Action**: Skip 2 full days
3. **Expected**: 
   - Streak = 0
   - Multi-day miss detected
   - Fresh start message

---

## **D. DATE MANIPULATION TESTS** 🚫

### **Test D1: Manual Date Change → Freeze Protection**
1. **Setup**: Current streak = 1+
2. **Action**: 
   ```bash
   # Go backwards in time
   adb shell date 112520002025  # Nov 25, 2025
   ```
3. **Expected**: 
   - Open app → Red warning appears
   - "⚠️ Date manipulation detected. Streak updates frozen."
   - Streak count frozen (no changes allowed)

### **Test D2: Lesson During Freeze → No Progress**
1. **Setup**: Date manipulation detected (Test D1)
2. **Action**: Try to complete lesson
3. **Expected**: 
   - Lesson completes but streak unchanged
   - Time tracking works but streak frozen
   - Warning remains visible

---

## **E. NOTIFICATION TESTS** 🔔

### **Test E1: 8 PM Notification Trigger**
1. **Setup**: Set emulator time to 19:59 (7:59 PM)
2. **Action**: 
   ```bash
   adb shell date 112720002025  # Set to 20:00 (8 PM)
   ```
3. **Expected**: 
   - Notification appears: "Don't break your streak!"
   - Click notification → Opens app to lesson selection

### **Test E2: No Notification If Lesson Complete**
1. **Setup**: Complete lesson today, set time to 8 PM
2. **Action**: Wait for notification time
3. **Expected**: 
   - NO notification sent
   - Logs show: "User already completed lesson today"

---

## **F. STRESS TESTS** 💪

### **Test F1: Rapid App Open/Close**
1. **Action**: Open app → Close → Open → Close (10 times)
2. **Expected**: 
   - Streak count remains stable
   - No duplicate increments
   - Performance remains smooth

### **Test F2: Multiple Date Changes**
1. **Action**: Change date forward/backward multiple times
2. **Expected**: 
   - System handles gracefully
   - Freeze protection engages
   - No crashes or data corruption

---

## **G. VERIFICATION COMMANDS** 🔍

### **Log Monitoring:**
```bash
# Real-time log monitoring
adb logcat | grep -i "streak\|time\|notification"

# Check specific components
adb logcat | grep "StreakManager"
adb logcat | grep "TimeTracker" 
adb logcat | grep "StreakPreferences"
```

### **Date Manipulation:**
```bash
# Set specific date/time
adb shell date MMddHHmmyyyy

# Examples:
adb shell date 112620002025  # Nov 26, 8 PM, 2025
adb shell date 112720002025  # Nov 27, 8 PM, 2025
adb shell date 112820002025  # Nov 28, 8 PM, 2025
```

### **App State Check:**
```bash
# Force app restart
adb shell am force-stop com.porakhela
adb shell monkey -p com.porakhela -c android.intent.category.LAUNCHER 1

# Check if app is running
adb shell dumpsys activity | grep "mResumedActivity"
```

---

## **H. SUCCESS CRITERIA** ✅

**ALL TESTS MUST PASS:**

- ✅ **Streak Logic**: Exact +1 per day, reset on miss
- ✅ **Time Tracking**: Accurate pause/resume, no auto-resume
- ✅ **Date Protection**: Detects manipulation, freezes updates
- ✅ **Notifications**: 8 PM daily, skips if lesson done
- ✅ **Persistence**: Survives app kills, system reboots
- ✅ **Performance**: No crashes, smooth operation

---

## **I. FAILURE RECOVERY** 🛠️

**If ANY test fails:**

1. **Collect Logs**: 
   ```bash
   adb logcat -d > streak_test_failure.log
   ```

2. **Report Issues**:
   - Exact test step that failed
   - Expected vs actual behavior
   - Log output from failure point

3. **Request Fix**:
   - "Fix timer logic, streak calculations, WorkManager scheduling, date manipulation detection, and state persistence. Rebuild until streak engine is exact and unbreakable."

---

**🎯 This test plan ensures the Daily Streak Engine is bulletproof and ready for production use.**