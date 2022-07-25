package com.takeawaydemo.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takeawaydemo.databinding.FragmentHomeBinding
import com.takeawaydemo.ui.adapter.RestaurantAdapter
import com.takeawaydemo.ui.adapter.RestaurantFilterAdapter
import com.takeawaydemo.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel by viewModels<HomeViewModel>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = activity
        }

        val root: View = binding.root

        binding.adapter = RestaurantAdapter(listOf(), homeViewModel)
        binding.adapterFilter= RestaurantFilterAdapter(listOf(),homeViewModel)

        homeViewModel.showToast.observe(requireActivity()) {
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