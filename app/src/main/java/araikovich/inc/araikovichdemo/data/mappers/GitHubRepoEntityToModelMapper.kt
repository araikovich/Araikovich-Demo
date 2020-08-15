package araikovich.inc.araikovichdemo.data.mappers

import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel

class GitHubRepoEntityToModelMapper : Mapper<GitHubRepoEntity, GitHubRepoModel> {

    override fun map(source: GitHubRepoEntity) = GitHubRepoModel(
        source.id,
        source.name,
        source.description,
        source.avatarUrl,
        source.login,
        source.isFavourite
    )
}