package com.porakhela.ui.developer;

/**
 * Developer Tools popup for testing Applink API integrations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ.\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\fJ\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0016H\u0002J\u0018\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J \u0010 \u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0014H\u0002J\u0018\u0010\"\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0018\u0010#\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0010\u0010$\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0018\u0010%\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0016H\u0002J\u0010\u0010&\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0014H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/porakhela/ui/developer/DeveloperToolsDialog;", "", "context", "Landroid/content/Context;", "lifecycleScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "applinkRepository", "Lcom/porakhela/data/repository/ApplinkRepository;", "porapointsManager", "Lcom/porakhela/utils/PorapointsManager;", "onPointsUpdated", "Lkotlin/Function0;", "", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleCoroutineScope;Lcom/porakhela/data/repository/ApplinkRepository;Lcom/porakhela/utils/PorapointsManager;Lkotlin/jvm/functions/Function0;)V", "dialog", "Landroid/app/Dialog;", "handleApiError", "error", "", "tvLastOperation", "Landroid/widget/TextView;", "operation", "", "retryAction", "setupViews", "view", "Landroid/view/View;", "show", "showToast", "message", "testOtpVerification", "tvApiStatus", "testRewardsRedemption", "tvCurrentPoints", "testSmsNotification", "testSubscription", "updateApiStatus", "updateLastOperation", "updatePointsDisplay", "app_debug"})
public final class DeveloperToolsDialog {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LifecycleCoroutineScope lifecycleScope = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.data.repository.ApplinkRepository applinkRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.porakhela.utils.PorapointsManager porapointsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<kotlin.Unit> onPointsUpdated = null;
    @org.jetbrains.annotations.Nullable()
    private android.app.Dialog dialog;
    
    public DeveloperToolsDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LifecycleCoroutineScope lifecycleScope, @org.jetbrains.annotations.NotNull()
    com.porakhela.data.repository.ApplinkRepository applinkRepository, @org.jetbrains.annotations.NotNull()
    com.porakhela.utils.PorapointsManager porapointsManager, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPointsUpdated) {
        super();
    }
    
    public final void show() {
    }
    
    private final void setupViews(android.view.View view) {
    }
    
    private final void testSubscription(android.widget.TextView tvLastOperation, android.widget.TextView tvApiStatus) {
    }
    
    private final void testSmsNotification(android.widget.TextView tvLastOperation, android.widget.TextView tvApiStatus) {
    }
    
    private final void testRewardsRedemption(android.widget.TextView tvLastOperation, android.widget.TextView tvApiStatus, android.widget.TextView tvCurrentPoints) {
    }
    
    private final void testOtpVerification(android.widget.TextView tvLastOperation, android.widget.TextView tvApiStatus) {
    }
    
    private final void updatePointsDisplay(android.widget.TextView tvCurrentPoints) {
    }
    
    private final void updateApiStatus(android.widget.TextView tvApiStatus) {
    }
    
    private final void updateLastOperation(android.widget.TextView tvLastOperation, java.lang.String message) {
    }
    
    private final void showToast(java.lang.String message) {
    }
    
    private final void handleApiError(java.lang.Throwable error, android.widget.TextView tvLastOperation, java.lang.String operation, kotlin.jvm.functions.Function0<kotlin.Unit> retryAction) {
    }
}