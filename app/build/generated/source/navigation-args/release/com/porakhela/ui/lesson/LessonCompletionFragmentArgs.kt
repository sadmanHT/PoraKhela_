package com.porakhela.ui.lesson

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class LessonCompletionFragmentArgs(
  public val correctAnswers: Int,
  public val totalQuestions: Int,
  public val porapointsEarned: Int,
  public val timeTakenMs: Long,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("correctAnswers", this.correctAnswers)
    result.putInt("totalQuestions", this.totalQuestions)
    result.putInt("porapointsEarned", this.porapointsEarned)
    result.putLong("timeTakenMs", this.timeTakenMs)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("correctAnswers", this.correctAnswers)
    result.set("totalQuestions", this.totalQuestions)
    result.set("porapointsEarned", this.porapointsEarned)
    result.set("timeTakenMs", this.timeTakenMs)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): LessonCompletionFragmentArgs {
      bundle.setClassLoader(LessonCompletionFragmentArgs::class.java.classLoader)
      val __correctAnswers : Int
      if (bundle.containsKey("correctAnswers")) {
        __correctAnswers = bundle.getInt("correctAnswers")
      } else {
        throw IllegalArgumentException("Required argument \"correctAnswers\" is missing and does not have an android:defaultValue")
      }
      val __totalQuestions : Int
      if (bundle.containsKey("totalQuestions")) {
        __totalQuestions = bundle.getInt("totalQuestions")
      } else {
        throw IllegalArgumentException("Required argument \"totalQuestions\" is missing and does not have an android:defaultValue")
      }
      val __porapointsEarned : Int
      if (bundle.containsKey("porapointsEarned")) {
        __porapointsEarned = bundle.getInt("porapointsEarned")
      } else {
        throw IllegalArgumentException("Required argument \"porapointsEarned\" is missing and does not have an android:defaultValue")
      }
      val __timeTakenMs : Long
      if (bundle.containsKey("timeTakenMs")) {
        __timeTakenMs = bundle.getLong("timeTakenMs")
      } else {
        throw IllegalArgumentException("Required argument \"timeTakenMs\" is missing and does not have an android:defaultValue")
      }
      return LessonCompletionFragmentArgs(__correctAnswers, __totalQuestions, __porapointsEarned,
          __timeTakenMs)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        LessonCompletionFragmentArgs {
      val __correctAnswers : Int?
      if (savedStateHandle.contains("correctAnswers")) {
        __correctAnswers = savedStateHandle["correctAnswers"]
        if (__correctAnswers == null) {
          throw IllegalArgumentException("Argument \"correctAnswers\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"correctAnswers\" is missing and does not have an android:defaultValue")
      }
      val __totalQuestions : Int?
      if (savedStateHandle.contains("totalQuestions")) {
        __totalQuestions = savedStateHandle["totalQuestions"]
        if (__totalQuestions == null) {
          throw IllegalArgumentException("Argument \"totalQuestions\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"totalQuestions\" is missing and does not have an android:defaultValue")
      }
      val __porapointsEarned : Int?
      if (savedStateHandle.contains("porapointsEarned")) {
        __porapointsEarned = savedStateHandle["porapointsEarned"]
        if (__porapointsEarned == null) {
          throw IllegalArgumentException("Argument \"porapointsEarned\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"porapointsEarned\" is missing and does not have an android:defaultValue")
      }
      val __timeTakenMs : Long?
      if (savedStateHandle.contains("timeTakenMs")) {
        __timeTakenMs = savedStateHandle["timeTakenMs"]
        if (__timeTakenMs == null) {
          throw IllegalArgumentException("Argument \"timeTakenMs\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"timeTakenMs\" is missing and does not have an android:defaultValue")
      }
      return LessonCompletionFragmentArgs(__correctAnswers, __totalQuestions, __porapointsEarned,
          __timeTakenMs)
    }
  }
}
