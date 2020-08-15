package araikovich.inc.araikovichdemo.ui.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import araikovich.inc.araikovichdemo.data.models.ui_models.GitHubRepoModel
import araikovich.inc.araikovichdemo.domain.GetGitHubReposUseCase
import araikovich.inc.araikovichdemo.domain.UpdateGitHubRepoFavouriteStatusUseCase
import araikovich.inc.araikovichdemo.ui.base.BaseViewModel
import araikovich.inc.araikovichdemo.ui.base.livedata.ActionResource
import araikovich.inc.araikovichdemo.ui.base.livedata.setSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getGitHubReposUseCase: GetGitHubReposUseCase,
    private val updateGitHubRepoFavouriteStatusUseCase: UpdateGitHubRepoFavouriteStatusUseCase
) : BaseViewModel() {

    companion object {

        private const val SQUARE_GIT_HUB_NAME = "square"
        private const val HASHICORP_GIT_HUB_NAME = "hashicorp"
    }

    val gitHubReposLiveData = MutableLiveData<ActionResource<List<GitHubRepoModel>>>()

    fun getGitHubRepos() {
        viewModelScope.launch(backgroundScope) {
            prepareListForResult(
                async(Dispatchers.IO) {
                    getGitHubReposUseCase.execute(
                        SQUARE_GIT_HUB_NAME,
                        false
                    )
                }.await(),
                async(Dispatchers.IO) {
                    getGitHubReposUseCase.execute(
                        HASHICORP_GIT_HUB_NAME,
                        false
                    )
                }.await()
            )
            prepareListForResult(
                async(Dispatchers.IO) {
                    getGitHubReposUseCase.execute(
                        SQUARE_GIT_HUB_NAME,
                        true
                    )
                }.await(),
                async(Dispatchers.IO) {
                    getGitHubReposUseCase.execute(
                        HASHICORP_GIT_HUB_NAME,
                        true
                    )
                }.await()
            )
        }
    }

    private suspend fun prepareListForResult(
        squareItems: List<GitHubRepoModel>,
        hashicorpItems: List<GitHubRepoModel>
    ) {
        if (squareItems.isNotEmpty() && hashicorpItems.isNotEmpty()) {
            val resultList = mutableListOf<GitHubRepoModel>()
            resultList.addAll(squareItems)
            resultList.addAll(hashicorpItems)
            withContext(Dispatchers.Main) {
                gitHubReposLiveData.setSuccess(resultList)
            }
        }
    }

    fun updateGitHubRepoFavouriteState(repoId: Int, updatedFavouriteStatus: Boolean) {
        viewModelScope.launch {
            updateGitHubRepoFavouriteStatusUseCase.execute(repoId, updatedFavouriteStatus)
        }
    }
}