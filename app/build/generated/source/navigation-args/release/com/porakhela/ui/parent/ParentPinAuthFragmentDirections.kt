package com.porakhela.ui.parent

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.porakhela.R

public class ParentPinAuthFragmentDirections private constructor() {
  public companion object {
    public fun actionParentPinAuthToParentDashboard(): NavDirections =
        ActionOnlyNavDirections(R.id.action_parentPinAuth_to_parentDashboard)
  }
}
