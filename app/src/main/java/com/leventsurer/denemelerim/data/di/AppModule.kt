package com.leventsurer.denemelerim.data.di

import com.google.firebase.auth.FirebaseAuth
import com.leventsurer.denemelerim.data.remote.AddNewExamApi
import com.leventsurer.denemelerim.data.remote.AuthenticationApi
import com.leventsurer.denemelerim.data.repository.AddExamRepositoryImpl
import com.leventsurer.denemelerim.data.repository.AuthenticationRepositoryImpl
import com.leventsurer.denemelerim.domain.repository.AddExamRepository
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication():FirebaseAuth = FirebaseAuth.getInstance()

    //@Singleton
    //@Provides
    //fun provideAuthenticationRepository(impl:AuthenticationRepositoryImpl):AuthenticationRepository = impl

    @Singleton
    @Provides
    fun provideAuthenticationApi(firebaseAuth: FirebaseAuth): AuthenticationApi {
        return AuthenticationApi(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(api:AuthenticationApi): AuthenticationRepository {
        return AuthenticationRepositoryImpl(api = api)
    }




    @Singleton
    @Provides
    fun provideAddNewExamApi(): AddNewExamApi {
        return AddNewExamApi()
    }

    @Provides
    @Singleton
    fun provideAddNewExamRepository(api:AddNewExamApi): AddExamRepository {
        return AddExamRepositoryImpl(addNewExamApi = api )
    }
}