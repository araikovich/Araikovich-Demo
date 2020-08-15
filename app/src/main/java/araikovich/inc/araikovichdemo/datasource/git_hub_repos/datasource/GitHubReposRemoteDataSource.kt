package araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource

import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse

interface GitHubReposRemoteDataSource {

    suspend fun getGitHubReposByName(orgName: String): List<GitHubRepoResponse>?
}