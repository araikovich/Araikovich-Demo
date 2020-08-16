package araikovich.inc.araikovichdemo.data.mappers

import araikovich.inc.araikovichdemo.data.models.entities.GitHubRepoEntity
import araikovich.inc.araikovichdemo.data.models.response.GitHubRepoResponse
import araikovich.inc.araikovichdemo.ui.utils.orZero

class GitHubRepoResponseToEntityMapper : Mapper<GitHubRepoResponse, GitHubRepoEntity> {

    override fun map(source: GitHubRepoResponse) = GitHubRepoEntity(
        source.id.orZero(),
        source.name.orEmpty(),
        source.description.orEmpty(),
        source.owner?.avatarUrl.orEmpty(),
        source.owner?.login.orEmpty(),
        false
    )
}