package araikovich.inc.araikovichdemo.data.models.ui_models

data class GitHubRepoModel(
    val id: Int,
    val name: String,
    val description: String,
    val avatarUrl: String,
    val login: String,
    var isFavourite: Boolean
)