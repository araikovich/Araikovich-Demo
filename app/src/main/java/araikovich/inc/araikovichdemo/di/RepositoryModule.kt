package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.impl.GitHubReposRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        GitHubReposRepositoryImpl(
            get(),
            get(),
            get(named("git_hub_repos_response_to_entity")),
            get(named("git_hub_repos_entity_to_model"))
        ) as GitHubReposRepository
    }
}