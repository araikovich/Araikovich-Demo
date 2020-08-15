package araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.impl

import araikovich.inc.araikovichdemo.data.mappers.Mapper
import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposLocalDataSource
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposRemoteDataSource
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository

class GitHubReposRepositoryImpl(
    private val localDataSource: GitHubReposLocalDataSource,
    private val remoteDataSource: GitHubReposRemoteDataSource,
    private val gitHubRepoResponseToEntityMapper: Mapper<GitHubRepoResponse, GitHubRepoEntity>,
    private val gitHubRepoEntityToModelMapper: Mapper<GitHubRepoEntity, GitHubRepoModel>
) : GitHubReposRepository {

    override suspend fun getGitHubRepoByName(
        name: String,
        refresh: Boolean
    ): List<GitHubRepoModel> {
        val cachedDataEntities = localDataSource.getAllRepos()
        return if (refresh) {
            val remoteDataEntities = gitHubRepoResponseToEntityMapper.list(
                remoteDataSource.getGitHubReposByName(name).orEmpty()
            )
            remoteDataEntities.forEach { remoteItem ->
                val savedItem = cachedDataEntities.firstOrNull { it.id == remoteItem.id }
                savedItem?.also {
                    remoteItem.isFavourite = it.isFavourite
                }
            }
            localDataSource.saveGitHubRepos(remoteDataEntities)
            gitHubRepoEntityToModelMapper.list(remoteDataEntities)
        } else {
            gitHubRepoEntityToModelMapper.list(cachedDataEntities)
        }
    }

    override suspend fun updateGitHubRepoFavouriteStatus(
        repoId: Int,
        updatedFavouriteStatus: Boolean
    ) {
        val repoEntity = localDataSource.getRepoById(repoId)
        repoEntity.isFavourite = updatedFavouriteStatus
        localDataSource.updateRepo(repoEntity)
    }
}