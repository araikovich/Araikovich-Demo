package araikovich.inc.araikovichdemo.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import araikovich.inc.araikovichdemo.BuildConfig
import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.storage.dao.GitHubRepoDao

@Database(
    entities = [GitHubRepoEntity::class],
    version = BuildConfig.DB_VERSION
)
abstract class GitHubDatabase : RoomDatabase() {

    abstract fun gitHubRepoDao(): GitHubRepoDao
}