package araikovich.inc.araikovichdemo.di

import araikovich.inc.araikovichdemo.domain.GetGitHubReposUseCase
import araikovich.inc.araikovichdemo.domain.UpdateGitHubRepoFavouriteStatusUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGitHubReposUseCase(get()) }
    factory { UpdateGitHubRepoFavouriteStatusUseCase(get()) }
}