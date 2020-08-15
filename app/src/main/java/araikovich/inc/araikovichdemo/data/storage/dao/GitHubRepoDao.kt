package araikovich.inc.araikovichdemo.data.storage.dao

import androidx.room.*
import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity

@Dao
interface GitHubRepoDao {

    @Query("SELECT * FROM GitHubRepoEntity")
    suspend fun getAllGitHubRepos(): List<GitHubRepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<GitHubRepoEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: GitHubRepoEntity)

    @Query("SELECT * FROM GitHubRepoEntity WHERE id = :repoId")
    suspend fun getRepoById(repoId: Int): GitHubRepoEntity
}