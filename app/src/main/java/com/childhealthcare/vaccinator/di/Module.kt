package com.childhealthcare.vaccinator.di


import com.childhealthcare.vaccinator.data.Api
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.data.PrefRepository
import com.childhealthcare.vaccinator.ui.account.LoginViewModel
import com.childhealthcare.vaccinator.ui.account.ProfileViewModel
import com.childhealthcare.vaccinator.ui.common.ChildViewModel
import com.childhealthcare.vaccinator.ui.common.ChildrenListViewModel
import com.childhealthcare.vaccinator.ui.home.VacinatorDashboardViewModel
import com.childhealthcare.vaccinator.ui.schedule.TasksListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://vaccinsystem.gearhostpreview.com/"

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { VacinatorDashboardViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel {(isVaccine: Boolean) ->
        ChildrenListViewModel(get(), get(), isVaccine)
    }
    viewModel { (childId: Int) ->
        ChildViewModel(get(), get<PrefRepository>().getUser()!!, childId)
    }
    viewModel { TasksListViewModel(get(), get<PrefRepository>().getUser()?.Id ?: 0) }
}

val repositoryModule = module {
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun createCustomerApi(factory: GsonConverterFactory, client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
            .create(Api::class.java)

    single { PrefRepository(androidApplication()) }
    single { ApiRepository(get()) }
    single { provideHttpClient() }
    single { GsonConverterFactory.create() }
    single { createCustomerApi(get(), get()) }
}
