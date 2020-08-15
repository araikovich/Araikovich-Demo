package araikovich.inc.araikovichdemo.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitHubRepoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val avatarUrl: String,
    val login: String,
    var isFavourite: Boolean
)
