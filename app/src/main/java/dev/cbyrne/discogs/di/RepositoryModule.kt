package dev.cbyrne.discogs.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.cbyrne.discogs.data.repository.MainRepository
import dev.cbyrne.discogs.data.repository.impl.MainRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getMainRepository(repository: MainRepositoryImpl): MainRepository
}