package com.porakhela.ui.parent;

import com.porakhela.data.repository.ParentDashboardRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ParentDashboardIntegration_Factory implements Factory<ParentDashboardIntegration> {
  private final Provider<ParentDashboardRepository> repositoryProvider;

  public ParentDashboardIntegration_Factory(
      Provider<ParentDashboardRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ParentDashboardIntegration get() {
    return newInstance(repositoryProvider.get());
  }

  public static ParentDashboardIntegration_Factory create(
      Provider<ParentDashboardRepository> repositoryProvider) {
    return new ParentDashboardIntegration_Factory(repositoryProvider);
  }

  public static ParentDashboardIntegration newInstance(ParentDashboardRepository repository) {
    return new ParentDashboardIntegration(repository);
  }
}
