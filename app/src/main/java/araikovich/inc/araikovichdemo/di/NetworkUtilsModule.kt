package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.datasource.utils.network.NetworkManager
import araikovich.inc.araikovichdemo.datasource.utils.network.NetworkManagerImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkUtilModule = module {
    single { NetworkManagerImpl(androidApplication()) as NetworkManager }
}