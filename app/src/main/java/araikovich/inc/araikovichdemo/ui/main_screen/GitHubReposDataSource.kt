package araikovich.inc.araikovichdemo.ui.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import araikovich.inc.araikovichdemo.ui.main_screen.models.GitHubRepoModel
import araikovich.inc.araikovichdemo.domain.GetGitHubReposUseCase
import araikovich.inc.araikovichdemo.ui.base.livedata.ActionState
import kotlinx.coroutines.*

/**
 *  --------------------IMPORTANT----------------------------
 *
 * This class created with hardcoded way to use pagination
 * In prod code i won't ever do this, and i'll
 * use keys, which i get from server response.
 * This DataSource shows only understanding of pagination principals
 *
 * P.S. I know about paging library 3.0, which helps to improve work with DB an Network,
 * but it currently in alpha, and I don't think it is good idea to use it now.
 */
class GitHubReposDataSource(
    private val getGitHubReposUseCase: GetGitHubReposUseCase,
    private val gitHubReposLoadingState: MutableLiveData<ActionState>
) : PageKeyedDataSource<String, GitHubRepoModel>() {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    /**
     * Look class description
     */
    private val pagesList =
        mutableListOf("square", "hashicorp", "apache", "google", "vue", "awesome")

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GitHubRepoModel>
    ) {
        scope.launch {
            prepareInitialListForResult(
                withContext(Dispatchers.IO) {
                    getGitHubReposUseCase.execute(
                        pagesList[0]
                    )
                },
                withContext(Dispatchers.IO) {
                    getGitHubReposUseCase.execute(
                        pagesList[1]
                    )
                },
                callback
            )
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubRepoModel>
    ) {
        scope.launch {
            val nextPage = if (pagesList.size > pagesList.indexOf(params.key) + 1) {
                pagesList[pagesList.indexOf(params.key) + 1]
            } else {
                ""
            }
            val refreshedData = withContext(Dispatchers.IO) {
                getGitHubReposUseCase.execute(
                    params.key
                )
            }
            callback.onResult(refreshedData, nextPage)
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, GitHubRepoModel>
    ) {}

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    private fun prepareInitialListForResult(
        squareItems: List<GitHubRepoModel>,
        hashicorpItems: List<GitHubRepoModel>,
        callback: LoadInitialCallback<String, GitHubRepoModel>
    ) {
        val resultList = mutableListOf<GitHubRepoModel>()
        resultList.addAll(squareItems)
        resultList.addAll(hashicorpItems)
        callback.onResult(resultList, pagesList[0], pagesList[2])
        gitHubReposLoadingState.postValue(ActionState.SUCCESS)
    }
}