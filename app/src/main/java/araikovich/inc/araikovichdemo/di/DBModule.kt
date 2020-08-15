package araikovich.inc.araikovichdemo.di

import android.content.Context
import androidx.room.Room
import araikovich.inc.araikovichdemo.App
import araikovich.inc.araikovichdemo.data.storage.GitHubDatabase
import araikovich.inc.araikovichdemo.data.storage.dao.GitHubRepoDao
import org.koin.dsl.module

private const val GIT_HUB_DATABASE = "git_hub_database"

val dbModule = module {
    single { provideRoom(App.instance) }
    factory { provideGitHubRepoDao(get()) }
}

fun provideRoom(context: Context): GitHubDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        GitHubDatabase::class.java,
        GIT_HUB_DATABASE
    ).fallbackToDestructiveMigration().build()
}

fun provideGitHubRepoDao(database: GitHubDatabase): GitHubRepoDao {
    return database.gitHubRepoDao()
}