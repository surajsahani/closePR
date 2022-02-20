package com.example.closepr.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.closepr.databinding.FragmentPullBinding
import com.example.closepr.adapters.PullAdapter
import com.example.closepr.adapters.PullLoadStateAdapter
import com.example.closepr.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PullFragment : Fragment() {

    private lateinit var binding: FragmentPullBinding

    @Inject
    lateinit var pullAdapter: PullAdapter

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPullBinding.inflate(layoutInflater, container, false)

        binding.pullList.adapter = pullAdapter.withLoadStateHeaderAndFooter(
            header = PullLoadStateAdapter { pullAdapter.retry() },
            footer = PullLoadStateAdapter { pullAdapter.retry() }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.getPullList().collectLatest {
                pullAdapter.submitData(it)
            }
        }

        return binding.root
    }

}