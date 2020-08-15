package araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository

import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel

interface GitHubReposRepository {

    suspend fun getGitHubRepoByName(name: String, refresh: Boolean): List<GitHubRepoModel>

    suspend fun updateGitHubRepoFavouriteStatus(repoId: Int, updatedFavouriteStatus: Boolean)
}