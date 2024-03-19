package com.example.fakepostsapp.presentation.view.post_details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fakepostsapp.R
import com.example.fakepostsapp.databinding.FragmentPostDetailsBinding
import com.example.fakepostsapp.databinding.FragmentUserPostsBinding
import com.example.fakepostsapp.presentation.view.post_details.viewmodel.PostDetailsViewModel
import com.example.fakepostsapp.presentation.view.user_posts.view.UserPostsAdapter
import com.example.fakepostsapp.presentation.view.user_posts.viewmodel.UserPostsViewModel
import com.example.fakepostsapp.utilities.checkNetworkConnectivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostDetailsFragment : Fragment() {


    private lateinit var binding: FragmentPostDetailsBinding
    private val postDetailsViewModel by viewModels<PostDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = PostDetailsFragmentArgs.fromBundle(requireArguments()).postId
        if (checkNetworkConnectivity(requireContext())) {
            postDetailsViewModel.getPostDetailsById(id)
            initObservers()
        }else{
            Toast.makeText(requireContext(),"No Internet Connection", Toast.LENGTH_SHORT).show()
        }



    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {

            postDetailsViewModel.postDetailsState.collectLatest { postItemState ->
                if (postItemState.loading) {
                    showLoadingState()
                } else {
                    populateUi(postItemState)
                }
            }
            postDetailsViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            tvItemUserId.visibility = View.GONE
            tvItemTitle.visibility = View.GONE
            tvItemBody.visibility = View.GONE
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(postDetailsState: PostDetailsState.Success) {
        binding.apply {
            progressBar.visibility = View.GONE
            tvItemUserId.visibility = View.VISIBLE
            tvItemTitle.visibility = View.VISIBLE
            tvItemBody.visibility = View.VISIBLE
            tvItemUserId.text = postDetailsState.postEntityUi.userId.toString()
            tvItemTitle.text = postDetailsState.postEntityUi.title
            tvItemBody.text = postDetailsState.postEntityUi.body
        }
    }

    private fun showErrorState(errorState: PostDetailsState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}