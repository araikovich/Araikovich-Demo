package araikovich.inc.araikovichdemo.datasource.git_hub_repos.api_call

import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubReposApi {

    @GET("orgs/{org}/repos")
    suspend fun getReposByName(
        @Path("org") org: String
    ): Response<List<GitHubRepoResponse>>
}