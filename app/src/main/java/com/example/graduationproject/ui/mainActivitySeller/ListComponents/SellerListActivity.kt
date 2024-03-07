package com.example.graduationproject.ui.mainActivitySeller.ListComponents

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.graduationproject.R
import com.example.graduationproject.databinding.ActivityListSellerBinding
import com.example.graduationproject.databinding.DialogLogoutBinding
import com.example.graduationproject.databinding.DialogOptionsBinding
import com.example.graduationproject.ui.login.LoginActivity
import com.example.graduationproject.ui.logOut.LogOutViewModel
import com.example.graduationproject.ui.login.TokenManager
import com.example.graduationproject.ui.mainActivityCustomer.ListComponents.CustomerListViewModel
import com.example.graduationproject.ui.mainActivityCustomer.ListComponents.material.MaterialsActivity
import com.example.graduationproject.ui.mainActivitySeller.ListComponents.profile.profileView.SellerProfileActivity

class SellerListActivity : AppCompatActivity() {
    private lateinit var viewModel: SellerListViewModel
    private lateinit var binding: ActivityListSellerBinding
    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSellerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SellerListViewModel::class.java)

        tokenManager = TokenManager(this)

        // Set an OnClickListener for the ImageView using view binding
        onClickBack()
        //onClickLogOut()
        showDialogOnClickLogout()
        onClickProfile()
        navToMaterial()
        loadData()
    }

    private fun showDialogOnClickLogout() {
        binding.logoutBtn.setOnClickListener {
            val dialogBinding = DialogLogoutBinding.inflate(layoutInflater)
            val alertDialogBuilder = AlertDialog.Builder(this)
                .setTitle("")
                .setView(dialogBinding.root)

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()

            dialogBinding.option1Button.setOnClickListener {
                alertDialog.dismiss() // Dismiss the dialog if needed
                // Handle Option 1 click
                performLogout()

            }

            dialogBinding.option2Button.setOnClickListener {
                alertDialog.dismiss() // Dismiss the dialog if needed
            }


        }
    }

    private fun loadData() {
        val accessToken = tokenManager.getToken()
        if (accessToken != null) {
            viewModel.viewData(accessToken,
                onDataLoaded = { data ->
                    data?.let {
                        binding.nameUser.text = it.name

                        val requestOptions = RequestOptions().transform(CircleCrop())

                        Glide.with(this)
                            .load(data.image)
                            .apply(requestOptions)
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.error)
                            .into(binding.imageProfile)
                    }
                },
                onError = { errorMessage ->
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(this, "Access token is null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickBack() {
        binding.buttonBack.setOnClickListener {
            // Handle the click event, for example, navigate back one step
            onBackPressed()
        }
    }

    private fun onClickProfile() {
        binding.profileBtn.setOnClickListener {
            val intent = Intent(this, SellerProfileActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun onClickLogOut() {
//        binding.logoutBtn.setOnClickListener {
//            performLogout()
//        }
//    }


    private fun navToLogin() {
        startActivity(Intent(this@SellerListActivity, LoginActivity::class.java))
    }

    private fun navToMaterial() {
        binding.materialBtn.setOnClickListener {
            val intent = Intent(this, MaterialsActivity::class.java)
            startActivity(intent)
        }
    }


    private fun performLogout() {
        val accessToken = tokenManager.getToken()
        if (accessToken != null) {
            val logOutViewModel =
                LogOutViewModel(tokenManager) // Initialize LogOutViewModel with tokenManager
            logOutViewModel.performLogOut(accessToken) { isSuccess, message ->
                if (isSuccess) {
                    // Clear token and finish activity
                    tokenManager.clearToken()
                    finishAffinity() // Finish all activities in the task associated with this activity
                    navToLogin()
                } else {
                    Toast.makeText(this@SellerListActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            // Handle case where access token is null
            Toast.makeText(this@SellerListActivity, "Access token is null", Toast.LENGTH_SHORT)
                .show()
        }
    }

}