package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.data.mappers.GitHubRepoEntityToModelMapper
import araikovich.inc.araikovichdemo.data.mappers.GitHubRepoResponseToEntityMapper
import araikovich.inc.araikovichdemo.data.mappers.Mapper
import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import araikovich.inc.araikovichdemo.ui.main_screen.models.GitHubRepoModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mapperModule = module {
    factory<Mapper<GitHubRepoResponse, GitHubRepoEntity>>(named(GIT_HUB_REPOS_RESPONSE_TO_ENTITY_NAMED)) { GitHubRepoResponseToEntityMapper() }
    factory<Mapper<GitHubRepoEntity, GitHubRepoModel>>(named(GIT_HUB_REPOS_ENTITY_TO_MODEL_NAMED)) { GitHubRepoEntityToModelMapper() }
}