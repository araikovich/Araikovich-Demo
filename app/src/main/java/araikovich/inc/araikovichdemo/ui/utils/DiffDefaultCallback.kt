package araikovich.inc.araikovichdemo.ui.utils

import androidx.recyclerview.widget.DiffUtil
import araikovich.inc.araikovichdemo.ui.main_screen.models.GitHubRepoModel

class DiffDefaultCallback : DiffUtil.ItemCallback<GitHubRepoModel>() {

    override fun areItemsTheSame(oldItem: GitHubRepoModel, newItem: GitHubRepoModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: GitHubRepoModel, newItem: GitHubRepoModel) =
        oldItem == newItem
}