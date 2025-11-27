package com.porakhela.ui.subject;

import com.porakhela.data.repository.SubjectRepository;
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
public final class SubjectCategoryViewModel_Factory implements Factory<SubjectCategoryViewModel> {
  private final Provider<SubjectRepository> subjectRepositoryProvider;

  public SubjectCategoryViewModel_Factory(Provider<SubjectRepository> subjectRepositoryProvider) {
    this.subjectRepositoryProvider = subjectRepositoryProvider;
  }

  @Override
  public SubjectCategoryViewModel get() {
    return newInstance(subjectRepositoryProvider.get());
  }

  public static SubjectCategoryViewModel_Factory create(
      Provider<SubjectRepository> subjectRepositoryProvider) {
    return new SubjectCategoryViewModel_Factory(subjectRepositoryProvider);
  }

  public static SubjectCategoryViewModel newInstance(SubjectRepository subjectRepository) {
    return new SubjectCategoryViewModel(subjectRepository);
  }
}
