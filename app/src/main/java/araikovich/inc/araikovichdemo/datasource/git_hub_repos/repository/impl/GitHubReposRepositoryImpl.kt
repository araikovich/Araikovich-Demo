package araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.impl

import araikovich.inc.araikovichdemo.data.mappers.Mapper
import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposLocalDataSource
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposRemoteDataSource
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository
import araikovich.inc.araikovichdemo.datasource.utils.network.NetworkManager

class GitHubReposRepositoryImpl(
    private val localDataSource: GitHubReposLocalDataSource,
    private val remoteDataSource: GitHubReposRemoteDataSource,
    private val gitHubRepoResponseToEntityMapper: Mapper<GitHubRepoResponse, GitHubRepoEntity>,
    private val gitHubRepoEntityToModelMapper: Mapper<GitHubRepoEntity, GitHubRepoModel>,
    private val networkManager: NetworkManager
) : GitHubReposRepository {

    override suspend fun getGitHubRepoByName(
        name: String
    ): List<GitHubRepoModel> {
        val cachedDataEntities = localDataSource.getReposByOrganizationName(name)
        return if (networkManager.isInternetAvailable()) {
            val remoteData = remoteDataSource.getGitHubReposByName(name)
            remoteData?.let {
                val remoteDataEntities = gitHubRepoResponseToEntityMapper.list(
                    remoteData
                )
                remoteDataEntities.forEach { remoteItem ->
                    val savedItem = cachedDataEntities.firstOrNull { it.id == remoteItem.id }
                    savedItem?.also {
                        remoteItem.isFavourite = it.isFavourite
                    }
                }
                localDataSource.saveGitHubRepos(remoteDataEntities)
                gitHubRepoEntityToModelMapper.list(remoteDataEntities)
            } ?: gitHubRepoEntityToModelMapper.list(cachedDataEntities)
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