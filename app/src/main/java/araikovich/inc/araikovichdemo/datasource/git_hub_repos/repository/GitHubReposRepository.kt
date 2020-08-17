package araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository

import araikovich.inc.araikovichdemo.ui.main_screen.models.GitHubRepoModel

interface GitHubReposRepository {

    suspend fun getGitHubRepoByName(name: String): List<GitHubRepoModel>

    suspend fun updateGitHubRepoFavouriteStatus(repoId: Int, updatedFavouriteStatus: Boolean)
}