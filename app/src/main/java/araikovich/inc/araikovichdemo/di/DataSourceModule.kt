package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposLocalDataSource
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.GitHubReposRemoteDataSource
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.impl.GitHubReposLocalDataSourceImpl
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.datasource.impl.GitHubReposRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory { GitHubReposRemoteDataSourceImpl(get()) as GitHubReposRemoteDataSource }
    factory { GitHubReposLocalDataSourceImpl(get()) as GitHubReposLocalDataSource }
}