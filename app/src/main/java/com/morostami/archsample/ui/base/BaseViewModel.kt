package com.roundtableapps.cloudrake.common.base

import androidx.lifecycle.*
//import com.roundtableapps.domain.usecase.*
//import com.roundtableapps.model.error_handler.ErrorManager
//import com.roundtableapps.model.error_handler.ErrorModel
//import com.roundtableapps.model.error_handler.ErrorObject
//import com.roundtableapps.model.livedata.EventLiveData
//import com.roundtableapps.model.livedata.EventMediatorLiveData
//import com.roundtableapps.model.response.ForceUpdateError
//import com.roundtableapps.model.retrofit.Resource
//import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//import org.koin.core.context.GlobalContext

open class BaseViewModel : ViewModel() {

//    private val openSocketUseCase: OpenSocketUseCase = GlobalContext.get().koin.get()
//    private val socketConnectionUseCase: SocketConnectionUseCase = GlobalContext.get().koin.get()
//    protected val accountManagerUseCase: AccountManagerUseCase = GlobalContext.get().koin.get()
//    private val tokenExpireUseCase: TokenExpireUseCase = GlobalContext.get().koin.get()
//
//    val showForceUpdate = EventLiveData<ForceUpdateError>()
    val themeMode: Int get() = 0 //  accountManagerUseCase.getThemeMode()
        fun onThemeStateChanged(): MutableLiveData<Int> {
//        return accountManagerUseCase.getThemeModeObservable()
            return MutableLiveData(0)
    }

    private val jobMaps = mutableMapOf<String,Job>()

//    fun <T> callApi(y: BaseCloudUseCase<T>, liveData: EventLiveData<Resource<T>>) {
//        val job = viewModelScope.launch {
//            y.callApi()
////                .onStart {
////                    emit(Resource.loading())
////                }
//                .catch {
//                    emit(Resource.error(it, null))
//                }
//                .flowOn(Dispatchers.IO)
//                .collect {
//                    when(it.status){
//                        Resource.Status.LOADING -> {
//                            liveData.postValue(it)
//                        }
//                        Resource.Status.SUCCESS -> {
//                            liveData.postValue(it)
//                        }
//                        Resource.Status.ERROR ->{
//                            val errorModel = ErrorManager.handleError(it.error)
//                            if (errorModel.errorCode != ErrorManager.ErrorCodes.ERROR_EMPTY) {
//                                it.apply {
//                                    it.errorObject = ErrorObject(errorModel.errorMessage, jsonError = errorModel.rawJsonResponse)
//                                }
//                                liveData.postValue(it)
//                            }
//
//                        }
//                    }
//                }
//        }
//
//        cancelJob(y)
//        jobMaps[y.hashCode().toString()] = job
//    }
//
//    protected fun <T>cancelJob(y: BaseCloudUseCase<T>){
//        val className = y.hashCode().toString()
//        if (jobMaps.keys.contains(className)){
//            jobMaps[className]?.cancel()
//        }
//    }
//
//    fun getOnTokenExpiredLiveData() = tokenExpireUseCase.expireToken()
//
//
//
//    //region websocket
//    fun openWebSocket() = openSocketUseCase()
//    fun closeWebSocket() = openSocketUseCase.close()
//    fun reconnectWebSocket() = openSocketUseCase()
//    fun getWebSocketConnectionStatus() = socketConnectionUseCase()
//
//    fun <T> handleChannel(
//        source: LiveData<Resource<T>>,
//        success: MediatorLiveData<T>? = null,
//        error: MediatorLiveData<Throwable>? = null,
//        errorData: MediatorLiveData<T>? = null,
//        loading: MediatorLiveData<T>? = null
//    ) {
//        success?.addSource(source) {
//            if (it?.status == Resource.Status.SUCCESS) {
//                success.postValue(it.data)
//            }
//        }
//
//        error?.addSource(source) {
//            if (it?.status == Resource.Status.ERROR) {
//                error.postValue(it.error)
//            }
//        }
//
//        errorData?.addSource(source) {
//            if (it?.status == Resource.Status.ERROR) {
//                errorData.postValue(it.data)
//            }
//        }
//
//        loading?.addSource(source) {
//            if (it?.status == Resource.Status.LOADING) {
//                loading.postValue(it.data)
//            }
//        }
//    }
//
//
//    fun <T> handleChannelAsApiCall(
//        source: LiveData<Resource<T>>,
//        success: MediatorLiveData<T>? = null,
//        error: MediatorLiveData<Throwable>? = null,
//        loading: MediatorLiveData<T>? = null
//    ) {
//        loading?.postValue(null)
//        success?.addSource(source) {
//            if (it?.status == Resource.Status.SUCCESS) {
//                success.postValue(it.data)
//            }
//        }
//
//        error?.addSource(source) {
//            if (it?.status == Resource.Status.ERROR) {
//                error.postValue(it.error)
//            }
//        }
//    }

    //endregion
}