package araikovich.inc.araikovichdemo.ui.main_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import araikovich.inc.araikovichdemo.R
import araikovich.inc.araikovichdemo.ui.main_screen.adapter.GitHubReposAdapter
import araikovich.inc.araikovichdemo.ui.main_screen.dialog.AraikovichInfoDialogBottomSheet
import araikovich.inc.araikovichdemo.ui.utils.VerticalItemsMarginDecorator
import araikovich.inc.araikovichdemo.ui.utils.dpToPx
import araikovich.inc.araikovichdemo.ui.utils.loadCircleIconRes
import araikovich.inc.araikovichdemo.ui.utils.scale
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {

        private const val ITEMS_MARGIN_TOP = 15
    }

    private val viewModel: MainViewModel by viewModel()

    private var adapter: GitHubReposAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupReposAdapter()
        setupListeners()
        setupCollapsingAnim()
        observeGitHubRepos()
    }

    private fun observeGitHubRepos() {
        viewModel.gitHubReposLiveData.observe(this, Observer {
            adapter?.submitList(it)
        })
        viewModel.gitHubReposLoadingState.observe(this, Observer {
            if (progressBar.isVisible) {
                progressBar.isVisible = false
            }
        })
    }

    private fun setupReposAdapter() {
        adapter = GitHubReposAdapter(this) { repoId, isFavourite ->
            viewModel.updateGitHubRepoFavouriteState(repoId, isFavourite)
        }
        rv_items.adapter = adapter
        rv_items.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_items.addItemDecoration(VerticalItemsMarginDecorator(ITEMS_MARGIN_TOP.dpToPx()))
    }

    private fun setupCollapsingAnim() {
        iv_araikovich.loadCircleIconRes(R.drawable.my_photo)
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val index =
                1f - Math.abs(verticalOffset.toFloat())
                    .div(appBarLayout.totalScrollRange.toFloat()) * 1.6f
            val changeValue = if (index < 0) 0f else index
            iv_araikovich.alpha = changeValue * 2
            if (iv_araikovich.scaleX > 0.4f && changeValue > 0.4f) {
                iv_araikovich.scale(changeValue)
            }
        })
    }

    private fun setupListeners() {
        info.setOnClickListener {
            supportFragmentManager.beginTransaction().add(AraikovichInfoDialogBottomSheet(), "TAG")
                .commitAllowingStateLoss()
        }
    }
}