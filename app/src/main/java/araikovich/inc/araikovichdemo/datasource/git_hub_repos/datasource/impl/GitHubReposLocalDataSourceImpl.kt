package araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.impl

import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.storage.dao.GitHubRepoDao
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposLocalDataSource

class GitHubReposLocalDataSourceImpl(private val dao: GitHubRepoDao) : GitHubReposLocalDataSource {

    override suspend fun saveGitHubRepos(repos: List<GitHubRepoEntity>) {
        dao.insert(repos)
    }

    override suspend fun getAllRepos(): List<GitHubRepoEntity> {
        return dao.getAllGitHubRepos()
    }

    override suspend fun getRepoById(repoId: Int): GitHubRepoEntity {
        return dao.getRepoById(repoId)
    }

    override suspend fun updateRepo(repoEntity: GitHubRepoEntity) {
        dao.update(repoEntity)
    }

    override suspend fun getReposByOrganizationName(name: String): List<GitHubRepoEntity> {
        return dao.getReposByOrganizationName(name)
    }
}