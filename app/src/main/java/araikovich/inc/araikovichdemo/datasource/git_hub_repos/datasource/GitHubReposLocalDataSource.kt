package araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource

import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity

interface GitHubReposLocalDataSource {

    suspend fun saveGitHubRepos(repos: List<GitHubRepoEntity>)

    suspend fun getAllRepos(): List<GitHubRepoEntity>

    suspend fun getRepoById(repoId: Int): GitHubRepoEntity

    suspend fun updateRepo(repoEntity: GitHubRepoEntity)
}