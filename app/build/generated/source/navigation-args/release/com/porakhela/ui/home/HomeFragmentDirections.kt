package com.porakhela.ui.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.porakhela.R
import kotlin.Int
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeToSubjectCategory(
    public val subject: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_home_to_subject_category

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("subject", this.subject)
        return result
      }
  }

  public companion object {
    public fun actionHomeToSubjectCategory(subject: String): NavDirections =
        ActionHomeToSubjectCategory(subject)
  }
}
