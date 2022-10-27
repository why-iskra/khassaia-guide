package ru.unit.feature_app_info_impl.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import ru.unit.core_utils.dimen.ktx.dp
import ru.unit.feature_app_info_impl.R
import ru.unit.feature_app_info_impl.databinding.FragmentAppInfoBinding

@AndroidEntryPoint
class AppInfoFragment : Fragment(R.layout.fragment_app_info) {

    private var _binding: FragmentAppInfoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAppInfoBinding.bind(view)

        context?.let { context ->
            Glide
                .with(this)
                .load(R.mipmap.ic_launcher)
                .transform(RoundedCorners(24.dp(context)))
                .into(binding.imageView)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}