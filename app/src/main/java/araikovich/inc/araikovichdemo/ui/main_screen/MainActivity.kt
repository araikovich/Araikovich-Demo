package araikovich.inc.araikovichdemo.ui.main_screen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import araikovich.inc.araikovichdemo.R
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.ui.base.livedata.ActionState
import araikovich.inc.araikovichdemo.ui.dpToPx
import araikovich.inc.araikovichdemo.ui.main_screen.adapter.GitHubReposAdapter
import araikovich.inc.araikovichdemo.ui.utils.VerticalItemsMarginDecorator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var adapter: GitHubReposAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupReposAdapter()
        observeGitHubRepos()
        viewModel.getGitHubRepos()
    }

    private fun observeGitHubRepos() {
        viewModel.gitHubReposLiveData.observe(this, Observer {
            when (it.state) {
                ActionState.SUCCESS -> {
                    provideGitHubRepos(it.data.orEmpty())
                }
            }
        })
    }

    private fun setupReposAdapter() {
        adapter = GitHubReposAdapter(this) { repoId, isFavourite ->
            viewModel.updateGitHubRepoFavouriteState(repoId, isFavourite)
        }
        rv_items.adapter = adapter
        rv_items.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_items.addItemDecoration(VerticalItemsMarginDecorator(15.dpToPx()))
    }

    private fun provideGitHubRepos(items: List<GitHubRepoModel>) {
        progressBar.visibility = View.GONE
        adapter?.updateItems(items)
    }
}