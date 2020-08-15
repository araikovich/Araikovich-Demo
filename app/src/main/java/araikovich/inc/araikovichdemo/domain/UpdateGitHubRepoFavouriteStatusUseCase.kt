package araikovich.inc.araikovichdemo.domain

import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository

class UpdateGitHubRepoFavouriteStatusUseCase(private val repository: GitHubReposRepository) {

    suspend fun execute(repoId: Int, updatedFavouriteStatus: Boolean) {
        repository.updateGitHubRepoFavouriteStatus(repoId, updatedFavouriteStatus)
    }
}