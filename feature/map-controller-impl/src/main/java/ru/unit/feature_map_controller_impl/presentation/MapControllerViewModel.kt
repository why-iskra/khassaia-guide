package ru.unit.feature_map_controller_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.unit.feature_map_controller_impl.contract.FeatureMapControllerContractImpl
import ru.unit.feature_map_controller_impl.interactor.FeatureMapControllerInteractorImpl
import javax.inject.Inject

@HiltViewModel
class MapControllerViewModel @Inject constructor(
    interactor: FeatureMapControllerInteractorImpl,
    private val contract: FeatureMapControllerContractImpl
) : ViewModel() {

    val flowLocation = interactor.flowLocation

    fun zoomIn() {
        viewModelScope.launch(Dispatchers.Main) {
            contract.zoomIn()
        }
    }

    fun zoomOut() {
        viewModelScope.launch(Dispatchers.Main) {
            contract.zoomOut()
        }
    }

    fun myLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            val value = flowLocation.value

            if (value != null) {
                contract.moveTo(value)
            }
        }
    }

}