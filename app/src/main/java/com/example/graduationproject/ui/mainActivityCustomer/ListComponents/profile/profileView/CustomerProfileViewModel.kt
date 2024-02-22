package com.example.graduationproject.ui.mainActivityCustomer.ListComponents.profile.profileView

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.graduationproject.api.ApiManager
import com.example.graduationproject.api.model.editProfile.EditProfileResponse
import com.example.graduationproject.api.model.profile.Data
import com.example.graduationproject.api.model.profile.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerProfileViewModel : ViewModel() {

    fun viewData(
        accessToken: String,
        onDataLoaded: (Data?) -> Unit,
        onError: (String) -> Unit
    ) {
        ApiManager.getApisToken(accessToken)
            .getUserProfile(accessToken)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {

                    Log.d("CustomerProfileViewMode", "first")

                    if (response.isSuccessful) {
                        val status: Int? = response.body()?.status
                        val message: String? = response.body()?.message
                        val profileResponse = response.body()
                        val data: Data? = profileResponse?.data
                        if (status == 200) {
                            Log.d("CustomerProfileViewMode", "Email1: ${data?.tIN},  200: ${response.message()}")

                            onDataLoaded(data)
                        } else {
                            Log.d("CustomerProfileViewMode", "Email2: ${data?.email}, else of 200: ${response.message()}")
                            onError(message ?: "Unknown error")
                        }
                    } else {

                        val profileResponse = response.body()

                        val data: Data? = profileResponse?.data

                        // Log the email value in data
                        Log.d("CustomerProfileViewMode", "Email3: ${data?.email}, error: ${response.message()}, $accessToken")
                        val errorMessage = "Error ${response.code()}: ${response.message()}"
                        onError(errorMessage)
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    onError(t.message ?: "Unknown error")
                }
            })
    }

}