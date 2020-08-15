package araikovich.inc.araikovichdemo.domain

import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository

class GetGitHubReposUseCase(private val repository: GitHubReposRepository) {

    suspend fun execute(repoName: String, refresh: Boolean): List<GitHubRepoModel> {
        return repository.getGitHubRepoByName(repoName, refresh)
    }
}