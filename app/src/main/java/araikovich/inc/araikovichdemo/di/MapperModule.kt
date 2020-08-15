package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.data.mappers.GitHubRepoEntityToModelMapper
import araikovich.inc.araikovichdemo.data.mappers.GitHubRepoResponseToEntityMapper
import araikovich.inc.araikovichdemo.data.mappers.Mapper
import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mapperModule = module {
    factory<Mapper<GitHubRepoResponse, GitHubRepoEntity>>(named("git_hub_repos_response_to_entity")) { GitHubRepoResponseToEntityMapper() }
    factory<Mapper<GitHubRepoEntity, GitHubRepoModel>>(named("git_hub_repos_entity_to_model")) { GitHubRepoEntityToModelMapper() }
}