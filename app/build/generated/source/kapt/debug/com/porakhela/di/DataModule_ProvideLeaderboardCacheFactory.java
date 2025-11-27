package com.porakhela.di;

import android.content.Context;
import com.porakhela.data.local.LeaderboardCache;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataModule_ProvideLeaderboardCacheFactory implements Factory<LeaderboardCache> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideLeaderboardCacheFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LeaderboardCache get() {
    return provideLeaderboardCache(contextProvider.get());
  }

  public static DataModule_ProvideLeaderboardCacheFactory create(
      Provider<Context> contextProvider) {
    return new DataModule_ProvideLeaderboardCacheFactory(contextProvider);
  }

  public static LeaderboardCache provideLeaderboardCache(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideLeaderboardCache(context));
  }
}
