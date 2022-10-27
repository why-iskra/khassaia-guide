package ru.unit.feature_settings_impl.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycleOwner
import ru.unit.feature_settings_impl.R
import ru.unit.feature_settings_impl.databinding.FragmentSettingsBinding

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        collectFlows()

        binding.syncWithSystemThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.notifyCheckedSyncWithSystemTheme(isChecked)
        }

        binding.nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.notifyCheckedNightMode(isChecked)
        }

        binding.syncMapWithAppThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.notifyCheckedSyncMapWithAppTheme(isChecked)
        }

        binding.mapNightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.notifyCheckedMapNightMode(isChecked)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun collectFlows() {
        viewModel.flowSyncWithSystemTheme.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.syncWithSystemThemeSwitch.isChecked = it
        }
        viewModel.flowNightMode.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.nightModeSwitch.isChecked = it
        }
        viewModel.flowSyncMapWithAppTheme.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.syncMapWithAppThemeSwitch.isChecked = it
        }
        viewModel.flowMapNightMode.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.mapNightModeSwitch.isChecked = it
        }
        viewModel.flowNightModeEnabled.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.nightModeSwitch.isEnabled = it
        }
        viewModel.flowMapNightModeEnabled.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.mapNightModeSwitch.isEnabled = it
        }
    }

}