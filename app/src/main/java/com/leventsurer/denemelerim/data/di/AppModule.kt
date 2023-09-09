package com.leventsurer.denemelerim.data.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.leventsurer.denemelerim.data.remote.AuthenticationApi
import com.leventsurer.denemelerim.data.remote.DatabaseApi
import com.leventsurer.denemelerim.data.remote.ExamsLessonsTopicsApi
import com.leventsurer.denemelerim.data.remote.UniversitiesAndDepartmentsApi
import com.leventsurer.denemelerim.data.repository.AuthenticationRepositoryImpl
import com.leventsurer.denemelerim.data.repository.DataStoreRepositoryImpl
import com.leventsurer.denemelerim.data.repository.DatabaseRepositoryImpl
import com.leventsurer.denemelerim.data.repository.GetExamsLessonsTopicApiRepositoryImpl
import com.leventsurer.denemelerim.data.repository.GetUniversitiesAndDepartmentsRepositoryImpl
import com.leventsurer.denemelerim.domain.repository.AuthenticationRepository
import com.leventsurer.denemelerim.domain.repository.DataStoreRepository
import com.leventsurer.denemelerim.domain.repository.DatabaseRepository
import com.leventsurer.denemelerim.domain.repository.GetExamsLessonsTopicApiRepository
import com.leventsurer.denemelerim.domain.repository.GetUniversitiesAndDepartmentsRepository
import com.leventsurer.denemelerim.util.Constants.UNIVERSITIES_AND_DEPARTMENTS_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Provides
    @Singleton
    fun provideGetExamsLessonsTopicApiRepository(examsLessonsTopicsApi: ExamsLessonsTopicsApi): GetExamsLessonsTopicApiRepository {
        return GetExamsLessonsTopicApiRepositoryImpl(examsLessonsTopicsApi = examsLessonsTopicsApi)
    }

    @Provides
    @Singleton
    fun provideUniversitiesAndDepartmentsApi(): UniversitiesAndDepartmentsApi {
        return Retrofit.Builder()
            .baseUrl(UNIVERSITIES_AND_DEPARTMENTS_BASE_URL) // Base URL for your API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversitiesAndDepartmentsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideExamsLessonsTopicsApi(): ExamsLessonsTopicsApi {
        return Retrofit.Builder()
            .baseUrl(UNIVERSITIES_AND_DEPARTMENTS_BASE_URL) // Base URL for your API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExamsLessonsTopicsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetUniversitiesAndDepartmentsRepository(
        universitiesAndDepartmentsApi: UniversitiesAndDepartmentsApi
    ): GetUniversitiesAndDepartmentsRepository {
        return GetUniversitiesAndDepartmentsRepositoryImpl(universitiesAndDepartmentsApi)
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