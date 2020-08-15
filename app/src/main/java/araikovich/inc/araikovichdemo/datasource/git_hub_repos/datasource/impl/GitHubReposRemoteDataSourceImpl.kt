package araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.impl

import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.api_call.GitHubReposApi
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposRemoteDataSource
import araikovich.inc.araikovichdemo.datasource.utils.SafeApiHelper
import retrofit2.Retrofit

class GitHubReposRemoteDataSourceImpl(private val retrofit: Retrofit) : GitHubReposRemoteDataSource,
    SafeApiHelper {

    override suspend fun getGitHubReposByName(orgName: String): List<GitHubRepoResponse>? {
        return safeApiCall(
            call = { retrofit.create(GitHubReposApi::class.java).getReposByName(orgName) },
            errorMessage = "get GitHub repos failed"
        )
    }
}