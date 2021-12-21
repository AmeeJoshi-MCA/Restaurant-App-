package com.takeawaydemo.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takeawaydemo.databinding.FragmentFavoriteBinding
import com.takeawaydemo.ui.adapter.FavoriteRestaurantAdapter
import com.takeawaydemo.ui.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.apply {
            viewModel = favViewModel
            lifecycleOwner = activity
        }

        val root: View = binding.root

        binding.adapter = FavoriteRestaurantAdapter(listOf(), favViewModel)

        favoriteViewModel.showToast.observe(requireActivity()) {
            it.getContentIfNotHandled()?.let { restaurant ->
                val intent = Intent(requireActivity(), RestaurantDetailActivity::class.java)
                intent.putExtra("restaurant",restaurant)
                requireActivity().startActivity(intent)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}