package ru.unit.feature_map_controller_impl.presentation

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycleOwner
import ru.unit.feature_map_controller_impl.R
import ru.unit.feature_map_controller_impl.databinding.FragmentMapControllerBinding

@AndroidEntryPoint
class MapControllerFragment : Fragment(R.layout.fragment_map_controller) {

    private val viewModel: MapControllerViewModel by viewModels()

    private var _binding: FragmentMapControllerBinding? = null
    private val binding get() = _binding!!

    private val valueAnimator: ValueAnimator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMapControllerBinding.bind(view)

        binding.locationButton.setOnClickListener {
            viewModel.myLocation()
        }

        binding.zoomInButton.setOnClickListener {
            viewModel.zoomIn()
        }

        binding.zoomOutButton.setOnClickListener {
            viewModel.zoomOut()
        }

        viewModel.flowLocation.collectOnLifecycleOwner(viewLifecycleOwner) {
            if (binding.locationButton.isGone && it != null) {
                binding.locationButton.run {
                    alpha = 0f
                    isVisible = true
                    animate().alpha(1f).setDuration(300).start()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}