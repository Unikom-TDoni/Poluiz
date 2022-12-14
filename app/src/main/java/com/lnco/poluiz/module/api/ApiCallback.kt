package com.lnco.poluiz.module.api

data class ApiCallback<in TSuccess, in TError>(
    val errorCallback: (ApiResult.Error<TSuccess, TError>) -> Unit,
    val successCallback: (ApiResult.Success<TSuccess, TError>) -> Unit,
    val loadingCallback: (ApiResult.Loading<TSuccess, TError>) -> Unit,
)