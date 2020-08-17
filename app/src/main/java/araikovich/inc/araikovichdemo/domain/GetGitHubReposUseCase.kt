package araikovich.inc.araikovichdemo.domain

import araikovich.inc.araikovichdemo.ui.main_screen.models.GitHubRepoModel
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository

class GetGitHubReposUseCase(private val repository: GitHubReposRepository) {

    companion object {

        private const val GET_ONLY_FIRST_FIFTEEN_ITEMS_NUMBER = 15
    }

    suspend fun execute(repoName: String): List<GitHubRepoModel> {
        return if (repoName.isEmpty()) {
            return emptyList()
        } else {
            repository.getGitHubRepoByName(repoName).take(GET_ONLY_FIRST_FIFTEEN_ITEMS_NUMBER)
        }
    }
}