package ru.unit.feature_main_impl.presentation

import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.unit.feature_main_impl.contract.FeatureMainContractImpl
import ru.unit.repository_places.mock.PlacesMock
import ru.unit.repository_places.models.PlaceModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contract: FeatureMainContractImpl,
    private val places: PlacesMock
) : ViewModel() {

    private val _flowPlaces = MutableStateFlow(places.list)
    val flowPlaces: StateFlow<List<PlaceModel>> get() = _flowPlaces

    private val _flowDrawerIsOpened = MutableStateFlow(false)
    val flowDrawerIsOpened: StateFlow<Boolean> get() = _flowDrawerIsOpened

    fun filter(text: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (text.isNullOrBlank()) {
                _flowPlaces.value = places.list
            } else {
                _flowPlaces.value = places.list.filter {
                    it.name.lowercase().contains(text.lowercase())
                }
            }
        }
    }

    fun onClickPlace(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            contract.navigateToRoute(id)
        }
    }

    fun sendTouchEventToMap(motionEvent: MotionEvent) {
        viewModelScope.launch(Dispatchers.Main) {
            contract.sendTouchEventToMap(motionEvent)
        }
    }

    fun notifyDrawerIsClosed() {
        viewModelScope.launch(Dispatchers.Main) {
            _flowDrawerIsOpened.emit(false)
        }
    }

    fun notifyDrawerIsOpened() {
        viewModelScope.launch(Dispatchers.Main) {
            _flowDrawerIsOpened.emit(true)
        }
    }
}