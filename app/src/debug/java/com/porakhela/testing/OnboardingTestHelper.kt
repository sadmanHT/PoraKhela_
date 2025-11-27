package com.porakhela.testing

import android.content.Context
import com.porakhela.data.local.OnboardingPreferences
import timber.log.Timber

/**
 * Helper class for testing onboarding flow validation
 */
class OnboardingTestHelper(private val context: Context) {
    
    private val onboardingPreferences = OnboardingPreferences(context)
    
    /**
     * Test 1: Clear onboarding state to ensure fresh start
     */
    fun clearOnboardingState(): TestResult {
        return try {
            onboardingPreferences.clearOnboardingData()
            TestResult.success("Onboarding state cleared successfully")
        } catch (e: Exception) {
            TestResult.error("Failed to clear onboarding state", e)
        }
    }
    
    /**
     * Test 2: Check if onboarding is properly bypassed after completion
     */
    fun testOnboardingBypass(): TestResult {
        return try {
            val isCompleted = onboardingPreferences.isOnboardingCompleted()
            if (isCompleted) {
                TestResult.success("Onboarding bypass working - completed status: true")
            } else {
                TestResult.warning("Onboarding not completed - user will see onboarding flow")
            }
        } catch (e: Exception) {
            TestResult.error("Failed to check onboarding bypass", e)
        }
    }
    
    /**
     * Test 3: Validate phone number validation logic
     */
    fun testPhoneValidation(): TestResult {
        return try {
            val validPhones = listOf("01712345678", "01812345678", "01912345678")
            val invalidPhones = listOf("123", "123456", "021234567890", "1712345678")
            
            val validResults = validPhones.map { 
                com.porakhela.core.utils.ValidationUtils.validatePhoneNumber(it)
            }
            
            val invalidResults = invalidPhones.map {
                com.porakhela.core.utils.ValidationUtils.validatePhoneNumber(it)
            }
            
            val validPassed = validResults.all { it.isValid }
            val invalidBlocked = invalidResults.none { it.isValid }
            
            when {
                validPassed && invalidBlocked -> TestResult.success("Phone validation working correctly")
                !validPassed -> TestResult.error("Valid phone numbers being rejected")
                !invalidBlocked -> TestResult.error("Invalid phone numbers being accepted")
                else -> TestResult.error("Unknown phone validation issue")
            }
        } catch (e: Exception) {
            TestResult.error("Phone validation test failed", e)
        }
    }
    
    /**
     * Test 4: Test consent requirement enforcement
     */
    fun testConsentValidation(): TestResult {
        return try {
            val validationWithConsent = com.porakhela.core.utils.ValidationUtils.validateConsent(true)
            val validationWithoutConsent = com.porakhela.core.utils.ValidationUtils.validateConsent(false)
            
            when {
                validationWithConsent.isValid && !validationWithoutConsent.isValid -> 
                    TestResult.success("Consent validation working correctly")
                !validationWithConsent.isValid -> 
                    TestResult.error("Consent checkbox not being validated properly - true rejected")
                validationWithoutConsent.isValid -> 
                    TestResult.error("Consent requirement bypassed - false accepted")
                else -> 
                    TestResult.error("Unknown consent validation issue")
            }
        } catch (e: Exception) {
            TestResult.error("Consent validation test failed", e)
        }
    }
    
    /**
     * Test 5: Test form field validation 
     */
    fun testFieldValidation(): TestResult {
        return try {
            val emptyFieldValidations = com.porakhela.core.utils.ValidationUtils.validateOnboardingForm(
                parentName = "",
                parentPhone = "",
                childName = "",
                consentGiven = false
            )
            
            val validFieldValidations = com.porakhela.core.utils.ValidationUtils.validateOnboardingForm(
                parentName = "John Doe",
                parentPhone = "01712345678",
                childName = "Jane Doe",
                consentGiven = true
            )
            
            val emptyFieldsBlocked = !com.porakhela.core.utils.ValidationUtils.areAllValid(emptyFieldValidations)
            val validFieldsAccepted = com.porakhela.core.utils.ValidationUtils.areAllValid(validFieldValidations)
            
            when {
                emptyFieldsBlocked && validFieldsAccepted -> 
                    TestResult.success("Field validation working correctly")
                !emptyFieldsBlocked -> 
                    TestResult.error("Empty fields being accepted")
                !validFieldsAccepted -> 
                    TestResult.error("Valid fields being rejected")
                else -> 
                    TestResult.error("Unknown field validation issue")
            }
        } catch (e: Exception) {
            TestResult.error("Field validation test failed", e)
        }
    }
    
    /**
     * Test 6: Test SharedPreferences persistence
     */
    fun testStatePersistence(): TestResult {
        return try {
            // Save test data
            val testParentName = "Test Parent"
            val testChildName = "Test Child"
            val testPhone = "01712345678"
            
            onboardingPreferences.saveOnboardingData(
                parentName = testParentName,
                parentPhone = testPhone,
                childName = testChildName,
                consentGiven = true
            )
            
            // Retrieve and verify
            val savedData = onboardingPreferences.getOnboardingData()
            
            when {
                savedData.parentName == testParentName &&
                savedData.childName == testChildName &&
                savedData.parentPhone == testPhone &&
                savedData.consentGiven &&
                savedData.onboardingCompleted -> 
                    TestResult.success("State persistence working correctly")
                else -> 
                    TestResult.error("State persistence failed - data not saved correctly")
            }
        } catch (e: Exception) {
            TestResult.error("State persistence test failed", e)
        }
    }
    
    /**
     * Run all validation tests
     */
    fun runAllTests(): List<TestResult> {
        Timber.i("Starting comprehensive onboarding validation tests...")
        
        return listOf(
            testPhoneValidation(),
            testConsentValidation(),
            testFieldValidation(),
            testStatePersistence(),
            testOnboardingBypass()
        )
    }
    
    /**
     * Generate test report
     */
    fun generateTestReport(): String {
        val results = runAllTests()
        val passed = results.count { it.status == TestResult.Status.SUCCESS }
        val failed = results.count { it.status == TestResult.Status.ERROR }
        val warnings = results.count { it.status == TestResult.Status.WARNING }
        
        return buildString {
            appendLine("=== ONBOARDING VALIDATION TEST REPORT ===")
            appendLine("Total Tests: ${results.size}")
            appendLine("Passed: $passed")
            appendLine("Failed: $failed") 
            appendLine("Warnings: $warnings")
            appendLine()
            
            results.forEachIndexed { index, result ->
                appendLine("${index + 1}. ${result.message}")
                if (result.status == TestResult.Status.ERROR && result.exception != null) {
                    appendLine("   Error: ${result.exception.message}")
                }
            }
            
            appendLine()
            if (failed == 0) {
                appendLine("✅ ALL TESTS PASSED - Onboarding flow is ready for deployment!")
            } else {
                appendLine("❌ $failed TESTS FAILED - Please fix issues before deployment")
            }
        }
    }
    
    /**
     * Data class to represent test results
     */
    data class TestResult(
        val status: Status,
        val message: String,
        val exception: Throwable? = null
    ) {
        enum class Status { SUCCESS, WARNING, ERROR }
        
        companion object {
            fun success(message: String) = TestResult(Status.SUCCESS, message)
            fun warning(message: String) = TestResult(Status.WARNING, message)
            fun error(message: String, exception: Throwable? = null) = 
                TestResult(Status.ERROR, message, exception)
        }
    }
}