package com.example.edu.mobileup.ui.coinInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edu.mobileup.data.ResponseState
import com.example.edu.mobileup.data.coinInfoData.CoinInfo
import com.example.edu.mobileup.domain.coinInfoUseCase.CoinInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinInfoViewModel @Inject constructor(
    private val useCase: CoinInfoUseCase
):ViewModel() {

    private val _coin = MutableStateFlow<ResponseState<CoinInfo>>(ResponseState.None())
    val coin = _coin.asStateFlow()

    fun getCoin(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            _coin.update { ResponseState.Loading() }
            runCatching { useCase.getCoin(id = id) }
                .onFailure {
                    it.printStackTrace()
                    _coin.update { ResponseState.Error() }
                }
                .onSuccess {response ->

                    if (response.body() == null)
                        _coin.update { ResponseState.Error() }
                    else
                        response.body()?.let {coin ->
                            if (response.code() in 200 until 300)
                                _coin.update { ResponseState.Success(coin) }
                            else
                                _coin.update { ResponseState.Error() }
                        }

                }
        }
    }
}