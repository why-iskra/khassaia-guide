package ru.unit.feature_map_impl.presentation

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yandex.mapkit.Animation
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map.CameraCallback
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycleOwner
import ru.unit.feature_map_api.models.LocationData
import ru.unit.feature_map_api.models.MoveCameraData
import ru.unit.feature_map_api.models.PlacemarkData
import ru.unit.feature_map_impl.R
import ru.unit.feature_map_impl.databinding.FragmentMapBinding
import timber.log.Timber

@AndroidEntryPoint
class MapFragment : Fragment(R.layout.fragment_map) {

    private val viewModel: MapViewModel by viewModels()

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private val map get() = _binding?.mapView?.map

    private var placeObjects: MapObjectCollection? = null
    private var userObjects: MapObjectCollection? = null
    private var routeObjects: MapObjectCollection? = null

    private var drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
    private var drivingSession: DrivingSession? = null

    private val cameraCallback: CameraCallback = CameraCallback {
        viewModel.notifyCameraMoveStop()
    }

    private val placemarkTapListener = MapObjectTapListener { obj, _ ->
        viewModel.notifyPlacemarkTapped(obj.userData as PlacemarkData)

        true
    }

    private val drivingSessionListener = object : DrivingSession.DrivingRouteListener {
        override fun onDrivingRoutes(list: MutableList<DrivingRoute>) {
            viewModel.notifyDrivingRoutes(list)
        }

        override fun onDrivingRoutesError(error: Error) {
            Timber.e(error.toString())
            viewModel.closeDrivingSession()
        }
    }

    private val cameraListener =
        CameraListener { _, cameraPosition, _, _ ->
            viewModel.notifyCamera(cameraPosition)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMapBinding.bind(view)

        setupMap()
        collectFlows()

        val position = viewModel.flowCamera.value
        if (position != null) {
            map?.move(position)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onStart() {
        binding.mapView.onStart()
        super.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        super.onStop()
    }

    private fun setupMap() {
        map?.run {
            if (placeObjects == null) {
                placeObjects = mapObjects.addCollection()
            }
            if (userObjects == null) {
                userObjects = mapObjects.addCollection()
            }
            if (routeObjects == null) {
                routeObjects = mapObjects.addCollection()
            }

            addCameraListener(cameraListener)
            isRotateGesturesEnabled = false
        }
    }

    private fun collectFlows() {
        viewModel.flowZoomIn.collectOnLifecycleOwner(viewLifecycleOwner) {
            zoomIn()
        }
        viewModel.flowZoomOut.collectOnLifecycleOwner(viewLifecycleOwner) {
            zoomOut()
        }
        viewModel.flowMoveTo.collectOnLifecycleOwner(viewLifecycleOwner) {
            if (it != null) {
                moveTo(it)
            }
        }
        viewModel.flowAddPlacemark.collectOnLifecycleOwner(viewLifecycleOwner) {
            addPlacemark(it)
        }
        viewModel.flowRemoveAllPlacemarks.collectOnLifecycleOwner(viewLifecycleOwner) {
            removeAllPlacemarks()
        }
        viewModel.flowLocation.collectOnLifecycleOwner(viewLifecycleOwner) {
            removeAllUsermark()
            if (it != null) {
                addUsermark(it)
            }
        }
        viewModel.flowStartRoute.collectOnLifecycleOwner(viewLifecycleOwner) {
            val location = viewModel.flowLocation.value

            closeDrivingSession()

            if (location != null && it != null) {
                startRoute(location, it)
            }
        }
        viewModel.flowDrivingRoute.collectOnLifecycleOwner(viewLifecycleOwner) {
            drivingRoute(it)
        }
        viewModel.flowSettings.collectOnLifecycleOwner(viewLifecycleOwner) {
            if (it.mapSyncWithAppTheme) {
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_NO -> {
                        map?.isNightModeEnabled = false
                    }
                    Configuration.UI_MODE_NIGHT_YES -> {
                        map?.isNightModeEnabled = true
                    }
                }
            } else {
                map?.isNightModeEnabled = it.mapNightTheme
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (viewModel.flowSettings.value.mapSyncWithAppTheme) {
            when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    map?.isNightModeEnabled = false
                }
                Configuration.UI_MODE_NIGHT_YES -> {
                    map?.isNightModeEnabled = true
                }
            }
        }
    }

    private fun startRoute(startLocationData: LocationData, stopLocationData: LocationData) {
        val drivingOptions = DrivingOptions().apply {
            routesCount = 3
        }
        val vehicleOptions = VehicleOptions()
        val requestPoints = ArrayList<RequestPoint>()
        requestPoints.add(
            RequestPoint(
                Point(startLocationData.lat, startLocationData.lon),
                RequestPointType.WAYPOINT,
                null
            )
        )
        requestPoints.add(
            RequestPoint(
                Point(stopLocationData.lat, stopLocationData.lon),
                RequestPointType.WAYPOINT,
                null
            )
        )

        drivingSession = drivingRouter.requestRoutes(
            requestPoints,
            drivingOptions,
            vehicleOptions,
            drivingSessionListener
        )
    }

    private fun drivingRoute(route: DrivingRoute) {
        val colorId = if (map?.isNightModeEnabled == true) {
            androidx.appcompat.R.color.background_material_light
        } else {
            androidx.appcompat.R.color.background_material_dark
        }

        val color = context?.let { ContextCompat.getColor(it, colorId) }

        routeObjects?.addPolyline(route.geometry)?.apply {
            color?.let { setStrokeColor(it) }
        }
    }

    private fun closeDrivingSession() {
        drivingSession?.cancel()
        drivingSession = null
        routeObjects?.clear()

        viewModel.notifyDrivingSessionClosed()
    }

    private fun zoomIn() {
        map?.run {
            move(
                CameraPosition(
                    cameraPosition.target,
                    cameraPosition.zoom + 1,
                    cameraPosition.azimuth,
                    cameraPosition.tilt
                ),
                Animation(Animation.Type.SMOOTH, 0.5f),
                cameraCallback
            )
        }
    }

    private fun zoomOut() {
        map?.run {
            move(
                CameraPosition(
                    cameraPosition.target,
                    cameraPosition.zoom - 1,
                    cameraPosition.azimuth,
                    cameraPosition.tilt
                ),
                Animation(Animation.Type.SMOOTH, 0.5f),
                cameraCallback
            )
        }
    }

    private fun moveTo(data: MoveCameraData) {
        map?.run {
            val animation = data.animation?.let {
                Animation(Animation.Type.SMOOTH, it)
            } ?: Animation(Animation.Type.LINEAR, 0f)

            move(
                CameraPosition(
                    Point(data.lat, data.lon),
                    data.zoom ?: cameraPosition.zoom,
                    cameraPosition.azimuth,
                    cameraPosition.tilt
                ),
                animation,
                cameraCallback
            )
        }
    }

    private fun addPlacemark(data: PlacemarkData) {
        map?.let { map ->
            placeObjects?.addPlacemark(
                Point(data.lat, data.lon)
            )?.apply {
                context?.let { ctx ->
                    val bitmap = getPinDrawable(
                        ctx,
                        R.drawable.ic_round_place_36,
                        map.isNightModeEnabled
                    )?.toBitmap()

                    bitmap?.let {
                        setIcon(ImageProvider.fromBitmap(it))
                    }
                }
                userData = data
                addTapListener(placemarkTapListener)
            }
        }
    }

    private fun removeAllPlacemarks() {
        placeObjects?.clear()
    }

    private fun addUsermark(data: LocationData) {
        map?.let { map ->
            userObjects?.addPlacemark(
                Point(data.lat, data.lon)
            )?.apply {
                context?.let { ctx ->
                    val bitmap = getPinDrawable(
                        ctx,
                        R.drawable.ic_round_trip_origin_24,
                        map.isNightModeEnabled
                    )?.toBitmap()

                    bitmap?.let {
                        setIcon(ImageProvider.fromBitmap(it))
                    }
                }
            }
        }
    }

    private fun removeAllUsermark() {
        userObjects?.clear()
    }

    private fun getPinDrawable(context: Context, @DrawableRes id: Int, isDark: Boolean): Drawable? {
        val drawable =
            ContextCompat.getDrawable(context, id) ?: return null

        val wrapped = DrawableCompat.wrap(drawable)

        val color = if (isDark) {
            androidx.appcompat.R.color.background_material_light
        } else {
            androidx.appcompat.R.color.background_material_dark
        }

        DrawableCompat.setTint(
            wrapped,
            ContextCompat.getColor(context, color)
        )

        return wrapped
    }
}