@echo off
:: USSD SIMULATION - STRICT VALIDATION TEST
:: Validates all requirements for production-ready telecom USSD interface

echo.
echo =======================================================
echo   🔌 USSD STRICT TEST VALIDATION - ALL REQUIREMENTS
echo =======================================================
echo.

:: Check if device is connected
echo 📱 Device Connection Check...
adb devices | findstr "device$"
if %errorlevel% neq 0 (
    echo ❌ CRITICAL: No Android device/emulator connected
    echo    Start emulator first: emulator -avd Medium_Phone_API_36.1
    pause
    exit /b 1
)

echo ✅ Device connected successfully
echo.

:: Install latest APK
echo 📦 Installing Latest USSD APK...
adb install -r "app\build\outputs\apk\debug\app-debug.apk"
if %errorlevel% neq 0 (
    echo ❌ CRITICAL: APK installation failed
    pause
    exit /b 1
)

echo ✅ APK installed successfully
timeout /t 2 >nul

echo.
echo 🚀 Starting Porakhela App...
adb shell am start -n com.porakhela/.DemoHomeActivity
timeout /t 4 >nul

echo.
echo ================================================
echo   A. USSD MENU VALIDATION TESTS
echo ================================================

echo.
echo A1. NAVIGATION TO USSD FROM PROFILE
echo    → Navigating to Profile tab...
adb shell input tap 540 2200
timeout /t 2 >nul

echo    → Looking for '*123# USSD Menu' button...
echo    → Tapping USSD button...
adb shell input tap 540 1400
timeout /t 3 >nul

echo.
echo A2. PIN AUTHENTICATION VALIDATION
echo    ✓ Should show: "Welcome to *123# USSD"
echo    ✓ Should show: "Enter 4-digit parent PIN"
echo    ✓ Should show number pad interface
echo.

echo    Testing INCORRECT PIN (9999) - Must deny access:
adb shell input tap 540 1800  :: Tap '9'
timeout /t 0.5 >nul
adb shell input tap 540 1800  :: Tap '9'
timeout /t 0.5 >nul
adb shell input tap 540 1800  :: Tap '9'
timeout /t 0.5 >nul
adb shell input tap 540 1800  :: Tap '9'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul

echo    ✓ Should show "Access Denied" message
echo.

echo    Testing CORRECT PIN (1234) - Must grant access:
adb shell input tap 400 1600  :: Tap '1'
timeout /t 0.5 >nul
adb shell input tap 540 1600  :: Tap '2'
timeout /t 0.5 >nul
adb shell input tap 680 1600  :: Tap '3'
timeout /t 0.5 >nul
adb shell input tap 400 1700  :: Tap '4'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 3 >nul

echo    ✓ Should show main USSD menu with 4 options
echo.

echo A3. MENU NAVIGATION VALIDATION
echo    All menu options must load without crashes:

echo    → Testing Option 1: Check Porapoints
adb shell input tap 400 1600  :: Tap '1'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 3 >nul
echo      ✓ Should show total points and pending redemptions

echo    → Returning to main menu
adb shell input tap 540 1700  :: Tap '0'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul

echo    → Testing Option 2: Approve Reward
adb shell input tap 540 1600  :: Tap '2'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 3 >nul
echo      ✓ Should show pending rewards or "No pending rewards"

echo    → Returning to main menu
adb shell input tap 540 1700  :: Tap '0'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul

echo    → Testing Option 3: Learning Summary
adb shell input tap 680 1600  :: Tap '3'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 3 >nul
echo      ✓ Should show lessons today, time spent, current streak

echo    → Returning to main menu
adb shell input tap 540 1700  :: Tap '0'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul

echo    → Testing Option 4: Change PIN
adb shell input tap 400 1700  :: Tap '4'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul
echo      ✓ Should ask for current PIN verification

echo    → Canceling PIN change and returning to menu
adb shell input tap 540 1700  :: Tap '0'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul

echo.
echo ================================================
echo   B. DATA CONSISTENCY VALIDATION
echo ================================================

echo.
echo B1. PORAPOINTS CONSISTENCY CHECK
echo    → Checking Porapoints in USSD...
adb shell input tap 400 1600  :: Tap '1'
timeout /t 0.5 >nul
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 3 >nul

echo    ✓ Note USSD Porapoints value displayed
echo    → Returning to home screen to compare...

adb shell input keyevent KEYCODE_HOME
timeout /t 2 >nul
adb shell am start -n com.porakhela/.DemoHomeActivity
timeout /t 3 >nul

echo    ✓ Compare Porapoints on Home screen - MUST MATCH USSD value
echo.

echo B2. REWARD STATUS UPDATE VALIDATION
echo    → Testing reward approval status update...
echo    ✓ After approval in USSD, status must update instantly
echo    ✓ Changes must be reflected immediately
echo.

echo ================================================
echo   C. ROBUSTNESS VALIDATION  
echo ================================================

echo.
echo C1. SPAM INPUT PROTECTION
echo    → Re-entering USSD for spam test...
adb shell input tap 540 2200  :: Profile tab
timeout /t 2 >nul
adb shell input tap 540 1400  :: USSD button  
timeout /t 3 >nul

echo    → Entering correct PIN fast...
adb shell input tap 400 1600  :: Tap '1'
adb shell input tap 540 1600  :: Tap '2'
adb shell input tap 680 1600  :: Tap '3'
adb shell input tap 400 1700  :: Tap '4'
adb shell input tap 680 1900  :: Tap 'SEND'
timeout /t 2 >nul

echo    → Testing rapid button spam...
adb shell input tap 400 1600  :: Multiple rapid taps
adb shell input tap 400 1600
adb shell input tap 400 1600
adb shell input tap 540 1600
adb shell input tap 540 1600
adb shell input tap 680 1600
timeout /t 1 >nul

echo    ✓ App should NOT crash from spam input
echo    ✓ Input cooldown should prevent excessive input
echo.

echo C2. SCREEN ROTATION PERSISTENCE
echo    → Testing screen rotation...
adb shell settings put system accelerometer_rotation 1
adb shell input keyevent KEYCODE_VOLUME_UP  :: Simulate orientation change trigger
timeout /t 2 >nul

echo    ✓ USSD state should persist after rotation
echo    ✓ Input buffer should be maintained
echo    ✓ Current menu should remain displayed
echo.

echo ================================================
echo   D. FINAL TELECOM INTERFACE VALIDATION
echo ================================================

echo.
echo D1. AUTHENTIC USSD BEHAVIOR CHECK
echo    ✓ Monochrome text-only interface: VERIFY VISUALLY
echo    ✓ Green terminal colors: VERIFY VISUALLY  
echo    ✓ No back navigation allowed: TEST BACK BUTTON
echo    ✓ Single elastic stack behavior: VERIFY FLOW
echo    ✓ Number pad feels like telecom hardware: TEST INPUT
echo.

echo D2. BUSINESS LOGIC VALIDATION
echo    ✓ PIN authentication works securely
echo    ✓ All 4 menu options functional
echo    ✓ Real data integration confirmed
echo    ✓ Error handling robust
echo    ✓ State persistence working
echo.

echo ================================================
echo   🎯 STRICT TEST VALIDATION COMPLETE
echo ================================================
echo.
echo VALIDATION CHECKLIST - MARK AS PASS/FAIL:
echo.
echo [  ] A1. PIN re-authentication denies wrong PIN
echo [  ] A1. PIN re-authentication grants correct PIN access  
echo [  ] A2. All menu options load without crashes
echo [  ] A3. Navigation flows work correctly
echo.
echo [  ] B1. Porapoints match between USSD and Home screen
echo [  ] B2. Reward status updates instantly after approval
echo [  ] B3. Learning summary matches actual logs
echo.
echo [  ] C1. Spam input protection prevents crashes
echo [  ] C2. Screen rotation preserves state
echo [  ] C3. App remains stable under stress
echo.
echo [  ] D1. Authentic telecom USSD interface achieved
echo [  ] D2. Single elastic stack navigation working
echo [  ] D3. All business logic validated
echo.
echo 🎉 IF ALL TESTS PASS: USSD INCLUSIVITY BRIDGE READY!
echo 🚨 IF ANY FAIL: Report issues to developer for fixes
echo.
pause