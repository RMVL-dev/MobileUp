package com.example.edu.mobileup.ui.coinsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edu.mobileup.data.ResponseState
import com.example.edu.mobileup.data.coinListData.CoinsListItem
import com.example.edu.mobileup.domain.usecases.CoinsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val useCase: CoinsListUseCase
):ViewModel() {

    private val _coinsList = MutableStateFlow<ResponseState<List<CoinsListItem>>>(ResponseState.None())
    val coinsLIst = _coinsList.asStateFlow()


    fun getCoinsList(currency:String){
        viewModelScope.launch(Dispatchers.IO) {

            runCatching { useCase.getCoinsList(currency = currency) }
                .onFailure {
                    it.printStackTrace()
                    _coinsList.update { ResponseState.Error() }
                }
                .onSuccess {response ->

                    if (response.body().isNullOrEmpty())
                        _coinsList.update { ResponseState.Error() }
                    else
                        response.body()?.let {list ->
                            if (response.code() in 200 until 300)
                                _coinsList.update { ResponseState.Success(list) }
                            else
                                _coinsList.update { ResponseState.Error() }
                        }

                }

        }
    }

}