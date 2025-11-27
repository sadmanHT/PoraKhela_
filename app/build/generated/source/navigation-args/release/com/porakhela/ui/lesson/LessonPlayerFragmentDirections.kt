package com.porakhela.ui.lesson

import android.os.Bundle
import androidx.navigation.NavDirections
import com.porakhela.R
import kotlin.Int
import kotlin.Long

public class LessonPlayerFragmentDirections private constructor() {
  private data class ActionLessonPlayerToCompletion(
    public val correctAnswers: Int,
    public val totalQuestions: Int,
    public val porapointsEarned: Int,
    public val timeTakenMs: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_lesson_player_to_completion

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("correctAnswers", this.correctAnswers)
        result.putInt("totalQuestions", this.totalQuestions)
        result.putInt("porapointsEarned", this.porapointsEarned)
        result.putLong("timeTakenMs", this.timeTakenMs)
        return result
      }
  }

  public companion object {
    public fun actionLessonPlayerToCompletion(
      correctAnswers: Int,
      totalQuestions: Int,
      porapointsEarned: Int,
      timeTakenMs: Long,
    ): NavDirections = ActionLessonPlayerToCompletion(correctAnswers, totalQuestions,
        porapointsEarned, timeTakenMs)
  }
}
