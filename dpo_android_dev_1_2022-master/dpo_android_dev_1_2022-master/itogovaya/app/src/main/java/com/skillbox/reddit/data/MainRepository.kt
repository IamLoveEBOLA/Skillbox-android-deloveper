package com.skillbox.reddit.data

import androidx.paging.*
import com.skillbox.reddit.data.locale.DataStoreManager
import com.skillbox.reddit.data.locale.db.CachedSubredditsDao
import com.skillbox.reddit.domain.model.AbstractRedditEntity
import com.skillbox.reddit.domain.model.SubredditEntity
import com.skillbox.reddit.data.remote.RedditApi
import com.skillbox.reddit.data.remote.SafeRemoteRepo
import com.skillbox.reddit.data.remote.response.CommonResponse
import com.skillbox.reddit.data.remote.response.posts.PostDto
import com.skillbox.reddit.data.remote.response.subreddits.SubredditDto
import com.skillbox.reddit.data.remote.response.user.UserDto
import com.skillbox.reddit.domain.model.ImagePostEntity
import com.skillbox.reddit.domain.model.PostEntity
import com.skillbox.reddit.domain.model.TextPostEntity
import com.skillbox.reddit.domain.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager.Base ,
    private val redditApi: RedditApi ,
    private val cachedSubredditsDao: CachedSubredditsDao
) : SafeRemoteRepo.BaseRemoteRepo() {
    
    fun getPagedSubredditsFlow(query: String): Flow<PagingData<SubredditEntity>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadSubreddits(query, afterKey)
            }
        }
    ).flow.transform { pagingData -> emit(pagingData.map { (it.data as SubredditDto).map() }) }
    
    fun getPagedPostsFlow(subredditName: String): Flow<PagingData<PostEntity>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadSubredditPosts(subredditName, afterKey)
            }
        }
    ).flow.transform { pagingData ->
        emit(pagingData.map { (it.data as PostDto).map() })
    }.transform { pagingData ->
        emit(pagingData.filter { it is ImagePostEntity || it is TextPostEntity })
    }
    
    fun getPagedFavoriteSubreddits(): Flow<PagingData<SubredditEntity>> = Pager(
        config = PagingConfig(25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadFavoriteSubreddits(afterKey)
            }
        }
    ).flow.transform { pagingData -> emit(pagingData.map { (it.data as SubredditDto).map() }) }
    
    fun getPagedSearchedSubredditsFlow(query: String): Flow<PagingData<SubredditEntity>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.searchSubreddits(query, afterKey)
            }
        }
    ).flow.transform { pagingData -> emit(pagingData.map { (it.data as SubredditDto).map() }) }
    
    fun getPagedFavoritePosts(): Flow<PagingData<PostEntity>> = Pager(
        config = PagingConfig(25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadFavoritePosts(getUserName(), afterKey)
            }
        }
    ).flow.transform { pagingData -> emit(pagingData.map { (it.data as PostDto).map() }) }
    
    suspend fun getComments(postId: String): Response<List<CommonResponse>> {
        return redditApi.getComments(postId)
    }
    
    suspend fun saveCurrentSubreddit(subreddit: SubredditEntity) =
        cachedSubredditsDao.saveSubreddit(subreddit)
    
    fun getCurrentSubreddit(subredditName: String): Flow<SubredditEntity> {
        return cachedSubredditsDao.getSubreddit(subredditName)
    }
    
    suspend fun saveAccessToken(token: String) {
        dataStoreManager.saveToken(token)
    }
    
    suspend fun checkAccessToken(): Boolean {
        return dataStoreManager.checkToken()
    }
    
    suspend fun vote(direction: Int, postId: String): ApiResult<Unit> {
        return safeApiCall { redditApi.vote(direction, postId) }
    }
    
    suspend fun subscribeUnsubscribe(action: String, displayName: String): ApiResult<Unit> {
        return safeApiCall { redditApi.subscribeUnsubscribe(action, displayName) }
    }
    
    suspend fun getMyProfile(): ApiResult<UserDto> {
        return safeApiCall { redditApi.getMyProfile() }
    }
    
    suspend fun getUserInfo(userName: String): ApiResult<AbstractRedditEntity> {
        return wrapResponse { redditApi.getUserInfo(userName) }
    }
    
    suspend fun getUserName(): String {
        return redditApi.getMyProfile().body()?.name ?: ""
    }
    
    suspend fun logOut() {
        cachedSubredditsDao.clearAll()
        dataStoreManager.clearDataStore()
    }
}
