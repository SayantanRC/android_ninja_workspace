package designstring.androidninja.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import designstring.androidninja.data.Repository
import designstring.androidninja.domain.mappers.NetworkEntityMapper

@InstallIn(ActivityComponent::class)
@Module
class AppModule {

    @Provides
    fun provideNetworkEntityMapper(): NetworkEntityMapper {
        return NetworkEntityMapper()
    }

    @Provides
    fun provideRepository(): Repository {
        return Repository()
    }

}