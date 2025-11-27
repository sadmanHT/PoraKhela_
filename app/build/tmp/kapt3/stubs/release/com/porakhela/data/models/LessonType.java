package com.porakhela.data.models;

/**
 * Enum for different lesson types
 */
@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0087\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\t"}, d2 = {"Lcom/porakhela/data/models/LessonType;", "", "(Ljava/lang/String;I)V", "INTERACTIVE_GAME", "VIDEO_LESSON", "QUIZ", "STORY", "PRACTICE_EXERCISE", "Companion", "app_release"})
public enum LessonType {
    /*public static final*/ INTERACTIVE_GAME /* = new INTERACTIVE_GAME() */,
    /*public static final*/ VIDEO_LESSON /* = new VIDEO_LESSON() */,
    /*public static final*/ QUIZ /* = new QUIZ() */,
    /*public static final*/ STORY /* = new STORY() */,
    /*public static final*/ PRACTICE_EXERCISE /* = new PRACTICE_EXERCISE() */;
    @org.jetbrains.annotations.NotNull()
    public static final com.porakhela.data.models.LessonType.Companion Companion = null;
    
    LessonType() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.porakhela.data.models.LessonType> getEntries() {
        return null;
    }
    
    /**
     * Enum for different lesson types
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/porakhela/data/models/LessonType$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/porakhela/data/models/LessonType;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.porakhela.data.models.LessonType> serializer() {
            return null;
        }
    }
}