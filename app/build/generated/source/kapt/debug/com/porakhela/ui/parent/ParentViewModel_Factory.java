package com.porakhela.ui.parent;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ParentViewModel_Factory implements Factory<ParentViewModel> {
  @Override
  public ParentViewModel get() {
    return newInstance();
  }

  public static ParentViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ParentViewModel newInstance() {
    return new ParentViewModel();
  }

  private static final class InstanceHolder {
    private static final ParentViewModel_Factory INSTANCE = new ParentViewModel_Factory();
  }
}
