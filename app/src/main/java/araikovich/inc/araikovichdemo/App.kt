package araikovich.inc.araikovichdemo

import android.app.Application
import araikovich.inc.araikovichdemo.di.*
import org.koin.android.ext.koin.androidContext

class App : Application() {

    companion object {

        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    restModule,
                    useCaseModule,
                    viewModelModule,
                    repositoryModule,
                    dbModule,
                    dataSourceModule,
                    mapperModule
                )
            )
        }
    }
}