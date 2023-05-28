package com.skillbox.reddit.data.remote

import com.skillbox.reddit.R
import com.skillbox.reddit.data.remote.response.CommonResponse
import com.skillbox.reddit.data.remote.response.Thing
import com.skillbox.reddit.domain.ApiResult
import com.skillbox.reddit.domain.model.AbstractRedditEntity
import com.skillbox.reddit.presentation.UiText
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

interface SafeRemoteRepo {
    
    suspend fun <Data> safeApiCall(apiToBeCalled: suspend () -> Response<Data>): ApiResult<Data>
    
    suspend fun wrapListResponse(apiToBeCalled: suspend () -> Response<CommonResponse>): ApiResult<List<AbstractRedditEntity>>
    
    suspend fun wrapResponse(apiToBeCalled: suspend () -> Response<Thing>): ApiResult<AbstractRedditEntity>
    
    abstract class BaseRemoteRepo : SafeRemoteRepo {
        
        override suspend fun <Data> safeApiCall(apiToBeCalled: suspend () -> Response<Data>): ApiResult<Data> {
            return try {
                val response = apiToBeCalled()
                
                if (response.isSuccessful) {
                    ApiResult.Success(_data = response.body())
                } else {
                    ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
                }
            } catch (e: HttpException) {
                ApiResult.Error(
                    exception = if (e.message != null) UiText.DynamicString(e.message())
                    else UiText.ResourceString(R.string.something_went_wrong)
                )
            } catch (e: IOException) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
            } catch (e: Exception) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            }
        }
    
        override suspend fun wrapListResponse(apiToBeCalled: suspend () -> Response<CommonResponse>): ApiResult<List<AbstractRedditEntity>> {
            return try {
    
                val response = apiToBeCalled()
                if (response.isSuccessful) {
                    ApiResult.Success(_data = response.body()?.commonData?.data?.map { it.data.map() })
                } else {
    
                    ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
                }
            } catch (e: HttpException) {
                ApiResult.Error(
                    exception = if (e.message != null) UiText.DynamicString(e.message())
                    else UiText.ResourceString(R.string.something_went_wrong)
                )
            } catch (e: IOException) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
            } catch (e: Exception) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            }
        }
    
        override suspend fun wrapResponse(apiToBeCalled: suspend () -> Response<Thing>): ApiResult<AbstractRedditEntity> {
            return try {
            
                val response = apiToBeCalled()
                if (response.isSuccessful) {
                    ApiResult.Success(_data = response.body()?.data?.map())
                } else {
                
                    ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
                }
            } catch (e: HttpException) {
                ApiResult.Error(
                    exception = if (e.message != null) UiText.DynamicString(e.message())
                    else UiText.ResourceString(R.string.something_went_wrong)
                )
            } catch (e: IOException) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
            } catch (e: Exception) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            }
        }
    }
}

