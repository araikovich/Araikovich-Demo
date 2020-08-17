package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.GitHubReposRepository
import araikovich.inc.araikovichdemo.datasource.git_hub_repos.repository.impl.GitHubReposRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val GIT_HUB_REPOS_RESPONSE_TO_ENTITY_NAMED = "git_hub_repos_response_to_entity"
const val GIT_HUB_REPOS_ENTITY_TO_MODEL_NAMED = "git_hub_repos_entity_to_model"

val repositoryModule = module {
    factory {
        GitHubReposRepositoryImpl(
            get(),
            get(),
            get(named(GIT_HUB_REPOS_RESPONSE_TO_ENTITY_NAMED)),
            get(named(GIT_HUB_REPOS_ENTITY_TO_MODEL_NAMED)),
            get()
        ) as GitHubReposRepository
    }
}