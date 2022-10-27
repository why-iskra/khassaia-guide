package ru.unit.feature_main_impl.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycleOwner
import ru.unit.feature_main_impl.NavGraphMainNestedDirections
import ru.unit.feature_main_impl.R
import ru.unit.feature_main_impl.databinding.FragmentMainBinding
import ru.unit.feature_main_impl.databinding.RailHeaderBinding

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val drawerOnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            _binding?.drawerLayout?.close()
        }
    }

    private val drawerListener = object : DrawerLayout.DrawerListener {
        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

        override fun onDrawerOpened(drawerView: View) {
            _binding?.drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            drawerOnBackPressedCallback.isEnabled = true

            viewModel.notifyDrawerIsOpened()
        }

        override fun onDrawerClosed(drawerView: View) {
            _binding?.drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            drawerOnBackPressedCallback.isEnabled = false
            addDrawerInterceptorMotionEvent()

            runCatching {
                val imm =
                    context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view?.windowToken, 0)
            }

            viewModel.notifyDrawerIsClosed()
        }

        override fun onDrawerStateChanged(newState: Int) {}
    }

    private val adapter: PlaceAdapter by lazy {
        PlaceAdapter(viewModel::onClickPlace)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        val bindingRailHeader = binding.navRailView.headerView?.let { RailHeaderBinding.bind(it) }

        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainFragment.adapter
        }

        binding.navRailView.setOnItemSelectedListener {
            val action = when (it.itemId) {
                R.id.map -> NavGraphMainNestedDirections.actionGlobalNavGraphMapController()
                R.id.settings -> NavGraphMainNestedDirections.actionGlobalNavGraphSettings()
                R.id.app_info -> NavGraphMainNestedDirections.actionGlobalNavGraphAppInfo()
                else -> {
                    return@setOnItemSelectedListener false
                }
            }

            navController().navigate(action)

            true
        }

        binding.searchEditText.addTextChangedListener {
            viewModel.filter(it?.toString())
        }

        bindingRailHeader?.listButton?.setOnClickListener {
            binding.navRailView.selectedItemId = R.id.map
            binding.drawerLayout.open()
        }

        binding.drawerLayout.addDrawerListener(drawerListener)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            drawerOnBackPressedCallback
        )

        viewModel.flowPlaces.collectOnLifecycleOwner(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

        viewModel.flowDrawerIsOpened.collectOnLifecycleOwner(viewLifecycleOwner) {
            if (it) {
                removeDrawerInterceptorMotionEvent()
            } else {
                addDrawerInterceptorMotionEvent()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun navController(): NavController {
        val container =
            childFragmentManager.findFragmentById(R.id.nestedNavigation) as NavHostFragment
        return container.navController
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun addDrawerInterceptorMotionEvent() {
        _binding?.drawerLayout?.setOnTouchListener { _, motionEvent ->
            motionEvent?.let {
                viewModel.sendTouchEventToMap(it)
            }

            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun removeDrawerInterceptorMotionEvent() {
        _binding?.drawerLayout?.setOnTouchListener(null)
    }
}