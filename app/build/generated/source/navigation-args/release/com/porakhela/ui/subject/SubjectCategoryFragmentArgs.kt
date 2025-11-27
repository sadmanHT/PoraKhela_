package com.porakhela.ui.subject

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class SubjectCategoryFragmentArgs(
  public val subject: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("subject", this.subject)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("subject", this.subject)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SubjectCategoryFragmentArgs {
      bundle.setClassLoader(SubjectCategoryFragmentArgs::class.java.classLoader)
      val __subject : String?
      if (bundle.containsKey("subject")) {
        __subject = bundle.getString("subject")
        if (__subject == null) {
          throw IllegalArgumentException("Argument \"subject\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"subject\" is missing and does not have an android:defaultValue")
      }
      return SubjectCategoryFragmentArgs(__subject)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        SubjectCategoryFragmentArgs {
      val __subject : String?
      if (savedStateHandle.contains("subject")) {
        __subject = savedStateHandle["subject"]
        if (__subject == null) {
          throw IllegalArgumentException("Argument \"subject\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"subject\" is missing and does not have an android:defaultValue")
      }
      return SubjectCategoryFragmentArgs(__subject)
    }
  }
}
