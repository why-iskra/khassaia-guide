package ru.unit.feature_route_impl.presentation

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.arguments.ktx.argumentDelegate
import ru.unit.core_utils.dimen.ktx.dp
import ru.unit.core_utils.lifecycle.ktx.collectOnLifecycleOwner
import ru.unit.feature_route_impl.R
import ru.unit.feature_route_impl.databinding.FragmentRouteBinding


@AndroidEntryPoint
class RouteFragment : Fragment(R.layout.fragment_route) {

    private val viewModel: RouteViewModel by viewModels()

    private var _binding: FragmentRouteBinding? = null
    private val binding get() = _binding!!

    private var behavior: BottomSheetBehavior<ConstraintLayout>? = null

    private var valueAnimator: ValueAnimator? = null

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isEnabled) {
                isEnabled = false

                viewModel.notifyFinishRoute()

                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    private val bottomSheetBehaviorCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            fabAlpha(newState, true)
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    private val placeId: Int by argumentDelegate()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRouteBinding.bind(view)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        behavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheetLayout)
        behavior?.addBottomSheetCallback(bottomSheetBehaviorCallback)

        binding.bottomSheet.routeButton.setOnClickListener {
            viewModel.notifyStartRoute(placeId)
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        binding.locationButton.setOnClickListener {
            viewModel.notifyLocation()
        }

        collectFlows()

        viewModel.init(placeId)
    }

    override fun onDestroyView() {
        behavior?.addBottomSheetCallback(bottomSheetBehaviorCallback)
        valueAnimator?.cancel()

        behavior = null
        _binding = null

        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        behavior?.state?.let(this::fabAlpha)
    }

    private fun collectFlows() {
        viewModel.flowLocation.collectOnLifecycleOwner(viewLifecycleOwner) {
            if (binding.locationButton.isGone && it != null) {
                binding.locationButton.run {
                    alpha = 0f
                    isVisible = true
                    animate().alpha(1f).setDuration(300).start()
                }
            }
        }

        viewModel.flowStart.collectOnLifecycleOwner(viewLifecycleOwner) {
            val value = binding.bottomSheet.helper.top

            valueAnimator?.cancel()

            valueAnimator = ValueAnimator.ofInt(0, value).apply {
                duration = 300
                addUpdateListener { animator ->
                    behavior?.peekHeight = animator.animatedValue as Int
                }
            }
            valueAnimator?.start()
        }

        viewModel.flowTitle.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.titleTextView.text = it
        }

        viewModel.flowTime.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.timeTextView.text = it ?: getString(R.string.unknown_time)
        }

        viewModel.flowPlotRouteButtonEnabled.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.routeButton.isEnabled = it
        }

        viewModel.flowImageUrl.collectOnLifecycleOwner(viewLifecycleOwner) {
            val context = binding.root.context
            Glide.with(binding.bottomSheet.appCompatImageView)
                .load(it)
                .transform(RoundedCorners(16.dp(context)))
                .placeholder(R.drawable.placeholder)
                .into(binding.bottomSheet.appCompatImageView)
        }

        viewModel.flowContent.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.contentTextView.isVisible = !it.isNullOrBlank()
            binding.bottomSheet.contentTextView.text = it
        }

        viewModel.flowStars.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.starsCardView.isVisible = (it != null)

            val stars = it ?: 0

            binding.bottomSheet.stars1ImageView.setImageResource(
                if (stars > 0) {
                    R.drawable.ic_round_star_24
                } else {
                    R.drawable.ic_round_star_outline_24
                }
            )
            binding.bottomSheet.stars2ImageView.setImageResource(
                if (stars > 1) {
                    R.drawable.ic_round_star_24
                } else {
                    R.drawable.ic_round_star_outline_24
                }
            )
            binding.bottomSheet.stars3ImageView.setImageResource(
                if (stars > 2) {
                    R.drawable.ic_round_star_24
                } else {
                    R.drawable.ic_round_star_outline_24
                }
            )
            binding.bottomSheet.stars4ImageView.setImageResource(
                if (stars > 3) {
                    R.drawable.ic_round_star_24
                } else {
                    R.drawable.ic_round_star_outline_24
                }
            )
            binding.bottomSheet.stars5ImageView.setImageResource(
                if (stars > 4) {
                    R.drawable.ic_round_star_24
                } else {
                    R.drawable.ic_round_star_outline_24
                }
            )

            updateInfoLayout()
        }

        viewModel.flowRating.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.ratingCardView.isVisible = (it != null)
            binding.bottomSheet.ratingTextView.text = it?.toString()

            updateInfoLayout()
        }

        viewModel.flowPrice.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.priceCardView.isVisible = (it != null)
            binding.bottomSheet.priceTextView.text =
                getString(R.string.price_pattern, it?.toString())

            updateInfoLayout()
        }

        viewModel.flowLatitude.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.coordsLatTextView.text = it.toString()
        }

        viewModel.flowLongitude.collectOnLifecycleOwner(viewLifecycleOwner) {
            binding.bottomSheet.coordsLonTextView.text = it.toString()
        }
    }

    private fun updateInfoLayout() {
        val infoVisible =
            binding.bottomSheet.starsCardView.isVisible ||
                    binding.bottomSheet.ratingCardView.isVisible ||
                    binding.bottomSheet.priceCardView.isVisible

        val infoInnerVisible =
            binding.bottomSheet.ratingCardView.isVisible ||
                    binding.bottomSheet.priceCardView.isVisible

        binding.bottomSheet.infoLayout.isVisible = infoVisible
        binding.bottomSheet.infoInnerLayout.isVisible = infoInnerVisible
    }

    private fun fabAlpha(state: Int, animate: Boolean = false) {
        when (state) {
            BottomSheetBehavior.STATE_DRAGGING -> {
                _binding?.backButton?.run {
                    if (animate) {
                        animate().alpha(0f).setDuration(300).start()
                    } else {
                        alpha = 0f
                    }
                    isClickable = false
                }
                _binding?.locationButton?.run {
                    if (animate) {
                        animate().alpha(0f).setDuration(300).start()
                    } else {
                        alpha = 0f
                    }
                    isClickable = false
                }
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
                _binding?.backButton?.run {
                    if (animate) {
                        animate().alpha(1f).setDuration(300).start()
                    } else {
                        alpha = 1f
                    }
                    isClickable = true
                }
                _binding?.locationButton?.run {
                    if (animate) {
                        animate().alpha(1f).setDuration(300).start()
                    } else {
                        alpha = 1f
                    }
                    isClickable = true
                }
            }
            BottomSheetBehavior.STATE_EXPANDED -> {
                _binding?.backButton?.run {
                    if (animate) {
                        animate().alpha(0f).setDuration(300).start()
                    } else {
                        alpha = 0f
                    }
                    isClickable = false
                }
                _binding?.locationButton?.run {
                    if (animate) {
                        animate().alpha(0f).setDuration(300).start()
                    } else {
                        alpha = 0f
                    }
                    isClickable = false
                }
            }
        }
    }

}