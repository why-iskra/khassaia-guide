package ru.unit.khassaia_guide.presentation

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.directions.DirectionsFactory
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycle
import ru.unit.core_utils.permissions.ktx.hasPermission
import ru.unit.khassaia_guide.BuildConfig
import ru.unit.khassaia_guide.R
import ru.unit.khassaia_guide.location.LocationListener
import ru.unit.khassaia_guide.resolver.FeatureMainContractResolver
import ru.unit.khassaia_guide.resolver.FeatureMapContractResolver
import ru.unit.khassaia_guide.resolver.FeatureMapControllerContractResolver
import ru.unit.khassaia_guide.resolver.FeatureRouteContractResolver
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var featureMapControllerContractResolver: FeatureMapControllerContractResolver

    @Inject
    lateinit var featureMainContractResolver: FeatureMainContractResolver

    @Inject
    lateinit var featureRouteContractResolver: FeatureRouteContractResolver

    @Inject
    lateinit var featureMapContractResolver: FeatureMapContractResolver

    @Inject
    lateinit var locationListener: LocationListener

    private val viewModel: MainViewModel by viewModels()

    private lateinit var preferences: SharedPreferences

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        if (permissions[ACCESS_FINE_LOCATION] == true || permissions[ACCESS_COARSE_LOCATION] == true) {
            viewModel.enableLocation()
            viewModel.startLocation()
        } else {
            showSnackbarSettings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.initialize(this)
        DirectionsFactory.initialize(this)

        super.onCreate(savedInstanceState)

        preferences = getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

        setContentView(R.layout.activity_main)

        if (!hasPermission(ACCESS_FINE_LOCATION) && !hasPermission(ACCESS_COARSE_LOCATION)) {
            viewModel.disableLocation()
            requestPermissions()
        } else {
            viewModel.enableLocation()
        }

        featureMainContractResolver.subscribe(this)
        featureMapControllerContractResolver.subscribe(this)
        featureRouteContractResolver.subscribe(this)
        featureMapContractResolver.subscribe(this)

        collectFlows()

        viewModel.init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startLocation()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopLocation()
    }

    override fun onStart() {
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    private fun collectFlows() {
        viewModel.flowSettingsPreferences.collectOnLifecycle(lifecycle) {
            if (it.syncWithSystemTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            } else {
                AppCompatDelegate.setDefaultNightMode(
                    if (it.nightTheme) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                )
            }
        }
    }

    private fun requestPermissions() {
        val shouldProvideRationaleFine =
            ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)
        val shouldProvideRationaleCoarse =
            ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)

        if (!shouldProvideRationaleFine && !shouldProvideRationaleCoarse) {
            showSnackbarRepeatRequest()
        } else {
            locationPermissionRequest.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
        }
    }

    private fun showSnackbarSettings() {
        Snackbar.make(
            findViewById(R.id.content),
            R.string.permission_denied_explanation,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(
            R.string.settings
        ) {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts(
                "package", BuildConfig.APPLICATION_ID, null
            )
            intent.data = uri
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }.show()
    }

    private fun showSnackbarRepeatRequest() {
        Snackbar.make(
            findViewById(R.id.content),
            R.string.permission_rationale,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.ok) {
            locationPermissionRequest.launch(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
        }.show()
    }
}