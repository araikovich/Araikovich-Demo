package araikovich.inc.araikovichdemo.ui.main_screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import araikovich.inc.araikovichdemo.R
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.ui.loadCircleIcon
import araikovich.inc.araikovichdemo.ui.utils.DiffDefaultCallback
import kotlinx.android.synthetic.main.view_holder_repo_item.view.*

class GitHubReposAdapter(
    private val context: Context,
    private val onFavouriteClick: (repoId: Int, isFavourite: Boolean) -> Unit
) :
    RecyclerView.Adapter<GitHubRepoViewHolder>() {

    private val items: MutableList<GitHubRepoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoViewHolder {
        return GitHubRepoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_holder_repo_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: GitHubRepoViewHolder, position: Int) {
        holder.bindView(items[position], onFavouriteClick)
    }

    fun updateItems(newList: List<GitHubRepoModel>) {
        val diffResult = DiffUtil.calculateDiff(DiffDefaultCallback(newList, items))
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class GitHubRepoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    var model: GitHubRepoModel? = null

    fun bindView(
        model: GitHubRepoModel,
        onFavouriteClick: (repoId: Int, isFavourite: Boolean) -> Unit
    ) {
        this.model = model
        setupListeners(onFavouriteClick)
        view.iv_avatar.loadCircleIcon(model.avatarUrl, null)
        view.tv_name.text = model.name
        if (model.description.isEmpty()) {
            view.tv_description.visibility = View.GONE
        } else {
            view.tv_description.text = model.description
        }
        updateStarState(model.isFavourite)
    }

    private fun setupListeners(onFavouriteClick: (repoId: Int, isFavourite: Boolean) -> Unit) {
        view.iv_favourite.setOnClickListener {
            model?.also { model ->
                onFavouriteClick.invoke(model.id, !model.isFavourite)
                model.isFavourite = !model.isFavourite
                updateStarState(model.isFavourite)
            }
        }
    }

    private fun updateStarState(isFavourite: Boolean) {
        if (isFavourite) {
            view.iv_favourite.setImageResource(R.drawable.ic_star_active)
        } else {
            view.iv_favourite.setImageResource(R.drawable.ic_star_default)
        }
    }
}