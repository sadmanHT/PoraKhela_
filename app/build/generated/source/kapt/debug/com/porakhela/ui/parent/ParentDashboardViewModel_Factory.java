package com.porakhela.ui.parent;

import com.porakhela.data.repository.ParentDashboardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ParentDashboardViewModel_Factory implements Factory<ParentDashboardViewModel> {
  private final Provider<ParentDashboardRepository> repositoryProvider;

  public ParentDashboardViewModel_Factory(Provider<ParentDashboardRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ParentDashboardViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ParentDashboardViewModel_Factory create(
      Provider<ParentDashboardRepository> repositoryProvider) {
    return new ParentDashboardViewModel_Factory(repositoryProvider);
  }

  public static ParentDashboardViewModel newInstance(ParentDashboardRepository repository) {
    return new ParentDashboardViewModel(repository);
  }
}
