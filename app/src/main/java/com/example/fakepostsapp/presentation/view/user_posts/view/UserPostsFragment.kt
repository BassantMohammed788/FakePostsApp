package com.example.fakepostsapp.presentation.view.user_posts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakepostsapp.R
import com.example.fakepostsapp.databinding.FragmentUserPostsBinding
import com.example.fakepostsapp.presentation.view.user_posts.viewmodel.UserPostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserPostsFragment : Fragment() {

    private lateinit var binding: FragmentUserPostsBinding
    private val postViewModel by viewModels<UserPostsViewModel>()
    private lateinit var postsAdapter: UserPostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserPostsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()

    }
    private fun initViews() {
        postsAdapter = UserPostsAdapter {
            val action=UserPostsFragmentDirections.actionUserPostsFragmentToPostDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.rvUserPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            postViewModel.getUSerPosts()
            postViewModel.postState.collectLatest { userState ->
                if (userState.loading) {
                    showLoadingState()
                } else {
                    populateUi(userState)
                }
            }
            postViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            rvUserPosts.visibility=View.GONE
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(postState: UserPostState.Success) {
        Log.e("PostsFragment", "PostState: $postState")

        binding.progressBar.visibility = View.GONE
        binding.rvUserPosts.visibility=View.VISIBLE
        postsAdapter.submitList(postState.postUiModel)
    }

    private fun showErrorState(errorState: UserPostState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }


}