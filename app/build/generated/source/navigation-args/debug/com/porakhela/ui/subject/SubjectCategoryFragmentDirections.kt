package com.porakhela.ui.subject

import android.os.Bundle
import androidx.navigation.NavDirections
import com.porakhela.R
import kotlin.Int
import kotlin.String

public class SubjectCategoryFragmentDirections private constructor() {
  private data class ActionSubjectCategoryToLessonPlayer(
    public val lessonId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_subject_category_to_lesson_player

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("lessonId", this.lessonId)
        return result
      }
  }

  public companion object {
    public fun actionSubjectCategoryToLessonPlayer(lessonId: String): NavDirections =
        ActionSubjectCategoryToLessonPlayer(lessonId)
  }
}
