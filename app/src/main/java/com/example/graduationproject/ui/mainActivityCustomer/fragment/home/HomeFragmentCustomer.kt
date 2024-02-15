package com.example.graduationproject.ui.mainActivityCustomer.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.graduationproject.R
import com.example.graduationproject.databinding.CustomerFragmentHomeBinding
import com.example.graduationproject.ui.mainActivityCustomer.ListComponents.CustomerListActivity


class HomeFragmentCustomer : Fragment(R.layout.customer_fragment_home) {

    private lateinit var binding: CustomerFragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize view binding
        binding = CustomerFragmentHomeBinding.bind(view)

        // Set an OnClickListener for the ImageView using view binding
        binding.listBtn.setOnClickListener {
            // Handle the click event, for example, navigate to another activity
            navigateToList()
        }

        createPost()
    }

    private fun navigateToList() {
        // Create an Intent to navigate to another activity
        val intent = Intent(requireContext(), CustomerListActivity::class.java)

        // Optionally, you can add extra data to the intent
        // intent.putExtra("key", "value")

        // Start the activity
        startActivity(intent)
    }

    fun createPost() {
        // Assuming you have a list of posts, replace it with your actual data
        val posts = listOf(
            Post(R.drawable.person1, "John Doe", R.drawable.post_img),
            Post(R.drawable.person1, "Jane Smith", R.drawable.post_img),
            Post(R.drawable.person1, "John Doe", R.drawable.post_img),
            Post(R.drawable.person1, "Jane Smith", R.drawable.post_img),
            Post(R.drawable.person1, "John Doe", R.drawable.post_img),
            Post(R.drawable.person1, "Jane Smith", R.drawable.post_img),
            Post(R.drawable.person1, "John Doe", R.drawable.post_img),
            Post(R.drawable.person1, "Jane Smith", R.drawable.post_img),
            // Add more posts as needed
        )
// Create the adapter
        val adapter = PostAdapter(posts)

// Set the adapter to the RecyclerView
        binding.RVPost.adapter = adapter

    }
}
