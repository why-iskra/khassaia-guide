package ru.unit.feature_route_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.unit.feature_route_api.models.LocationData
import ru.unit.feature_route_api.models.MoveCameraData
import ru.unit.feature_route_impl.contract.FeatureRouteContractImpl
import ru.unit.feature_route_impl.interactor.FeatureRouteInteractorImpl
import ru.unit.repository_places.mock.PlacesMock
import ru.unit.repository_places.models.PlaceModel
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val placesMock: PlacesMock,
    private val contract: FeatureRouteContractImpl,
    private val interactor: FeatureRouteInteractorImpl
) : ViewModel() {

    private var isInited = false

    val flowLocation = interactor.flowLocation

    private val _flowStart = MutableSharedFlow<Unit>()
    val flowStart: SharedFlow<Unit> get() = _flowStart

    private val _flowTitle = MutableStateFlow("")
    val flowTitle: StateFlow<String> get() = _flowTitle

    private val _flowImageUrl = MutableStateFlow("")
    val flowImageUrl: StateFlow<String> get() = _flowImageUrl

    private val _flowContent = MutableStateFlow<String?>(null)
    val flowContent: StateFlow<String?> get() = _flowContent

    private val _flowStars = MutableStateFlow<Int?>(null)
    val flowStars: StateFlow<Int?> get() = _flowStars

    private val _flowRating = MutableStateFlow<Float?>(null)
    val flowRating: StateFlow<Float?> get() = _flowRating

    private val _flowPrice = MutableStateFlow<Float?>(null)
    val flowPrice: StateFlow<Float?> get() = _flowPrice

    private val _flowLatitude = MutableStateFlow(0.0)
    val flowLatitude: StateFlow<Double> get() = _flowLatitude

    private val _flowLongitude = MutableStateFlow(0.0)
    val flowLongitude: StateFlow<Double> get() = _flowLongitude

    private val _flowPlotRouteButtonEnabled = MutableStateFlow(true)
    val flowPlotRouteButtonEnabled: StateFlow<Boolean>
        get() = _flowPlotRouteButtonEnabled
            .combine(interactor.flowLocation) { value, location ->
                value && location != null
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = false
            )

    val flowTime: StateFlow<String?> = interactor.flowRouteTime
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    fun init(id: Int) {
        val data = getPlace(id)

        viewModelScope.launch(Dispatchers.Main) {
            _flowTitle.emit(data.name)
            _flowImageUrl.emit(data.imgUrl)
            _flowContent.emit(data.description)
            _flowStars.emit(data.stars)
            _flowRating.emit(data.rating)
            _flowPrice.emit(data.price)
            _flowLatitude.emit(data.lat)
            _flowLongitude.emit(data.lon)
        }

        if (!isInited) {
            isInited = true

            moveCamera(data)

            viewModelScope.launch(Dispatchers.Default) {
                delay(700)
                _flowStart.emit(Unit)
            }
        }
    }

    private fun moveCamera(data: PlaceModel) {
        viewModelScope.launch(Dispatchers.Main) {
            contract.moveTo(
                MoveCameraData(
                    data.lat,
                    data.lon,
                    zoom = 14f
                )
            )
        }
    }

    fun notifyStartRoute(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val data = getPlace(id)

            contract.startRoute(
                LocationData(
                    data.lat,
                    data.lon
                )
            )

            val location = interactor.flowLocation.value

            if (location != null) {
                contract.moveTo(
                    MoveCameraData(
                        location.lat,
                        location.lon,
                        zoom = 14f
                    )
                )
            }

            _flowPlotRouteButtonEnabled.emit(false)
        }
    }

    fun notifyFinishRoute() {
        viewModelScope.launch(Dispatchers.Main) {
            contract.finishRoute()
        }
    }

    fun notifyLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            val location = interactor.flowLocation.value

            if (location != null) {
                contract.moveTo(
                    MoveCameraData(
                        location.lat,
                        location.lon,
                        zoom = 14f
                    )
                )
            }
        }
    }

    private fun getPlace(id: Int): PlaceModel = placesMock.list.first { it.id == id }

}