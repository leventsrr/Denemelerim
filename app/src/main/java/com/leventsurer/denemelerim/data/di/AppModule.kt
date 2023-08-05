package com.leventsurer.denemelerim.data.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.leventsurer.denemelerim.data.remote.AddNewExamApi
import com.leventsurer.denemelerim.data.remote.AuthenticationApi
import com.leventsurer.denemelerim.data.remote.DatabaseApi
import com.leventsurer.denemelerim.data.repository.AddExamRepositoryImpl
import com.leventsurer.denemelerim.data.repository.AuthenticationRepositoryImpl
import com.leventsurer.denemelerim.data.repository.DataStoreRepositoryImpl
import com.leventsurer.denemelerim.data.repository.DatabaseRepositoryImpl
import com.leventsurer.denemelerim.domain.repository.AddExamRepository
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

    @Provides
    @Singleton
    fun provideDatabaseRepository(api:DatabaseApi):DatabaseRepository{
        return DatabaseRepositoryImpl(databaseApi = api)
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStore():FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideSetTargetApi(firebaseFirestore: FirebaseFirestore): DatabaseApi {
        return DatabaseApi(firebaseFirestore)
    }



    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) :DataStoreRepository = DataStoreRepositoryImpl(context)
}