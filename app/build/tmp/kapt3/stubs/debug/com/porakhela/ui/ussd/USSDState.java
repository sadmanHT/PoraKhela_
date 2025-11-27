package com.porakhela.ui.ussd;

/**
 * USSD State Machine
 * Represents different states in the USSD flow
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/porakhela/ui/ussd/USSDState;", "", "(Ljava/lang/String;I)V", "PIN_AUTH", "MAIN_MENU", "PORAPOINTS_CHECK", "REWARD_APPROVAL", "LEARNING_SUMMARY", "PIN_CHANGE_OLD", "PIN_CHANGE_NEW", "PIN_CHANGE_CONFIRM", "app_debug"})
public enum USSDState {
    /*public static final*/ PIN_AUTH /* = new PIN_AUTH() */,
    /*public static final*/ MAIN_MENU /* = new MAIN_MENU() */,
    /*public static final*/ PORAPOINTS_CHECK /* = new PORAPOINTS_CHECK() */,
    /*public static final*/ REWARD_APPROVAL /* = new REWARD_APPROVAL() */,
    /*public static final*/ LEARNING_SUMMARY /* = new LEARNING_SUMMARY() */,
    /*public static final*/ PIN_CHANGE_OLD /* = new PIN_CHANGE_OLD() */,
    /*public static final*/ PIN_CHANGE_NEW /* = new PIN_CHANGE_NEW() */,
    /*public static final*/ PIN_CHANGE_CONFIRM /* = new PIN_CHANGE_CONFIRM() */;
    
    USSDState() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.porakhela.ui.ussd.USSDState> getEntries() {
        return null;
    }
}