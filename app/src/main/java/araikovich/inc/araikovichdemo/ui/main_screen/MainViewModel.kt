package araikovich.inc.araikovichdemo.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.domain.GetGitHubReposUseCase
import araikovich.inc.araikovichdemo.domain.UpdateGitHubRepoFavouriteStatusUseCase
import araikovich.inc.araikovichdemo.ui.base.BaseViewModel
import araikovich.inc.araikovichdemo.ui.base.livedata.ActionState
import kotlinx.coroutines.launch

class MainViewModel(
    private val getGitHubReposUseCase: GetGitHubReposUseCase,
    private val updateGitHubRepoFavouriteStatusUseCase: UpdateGitHubRepoFavouriteStatusUseCase
) : BaseViewModel() {

    var gitHubReposLiveData: LiveData<PagedList<GitHubRepoModel>>
    var gitHubReposLoadingState = MutableLiveData<ActionState>()

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(15)
            .build()
        gitHubReposLiveData = initializedPagedListBuilder(config).build()
    }

    fun updateGitHubRepoFavouriteState(repoId: Int, updatedFavouriteStatus: Boolean) {
        viewModelScope.launch {
            updateGitHubRepoFavouriteStatusUseCase.execute(repoId, updatedFavouriteStatus)
        }
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, GitHubRepoModel> {

        val dataSourceFactory = object : DataSource.Factory<String, GitHubRepoModel>() {
            override fun create(): DataSource<String, GitHubRepoModel> {
                return GitHubReposDataSource(getGitHubReposUseCase, gitHubReposLoadingState)
            }
        }
        return LivePagedListBuilder<String, GitHubRepoModel>(dataSourceFactory, config)
    }
}