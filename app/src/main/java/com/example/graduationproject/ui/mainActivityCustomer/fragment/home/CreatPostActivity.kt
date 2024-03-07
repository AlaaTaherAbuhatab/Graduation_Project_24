package com.example.graduationproject.ui.mainActivityCustomer.fragment.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.graduationproject.databinding.ActivityCreatPostBinding
import com.example.graduationproject.ui.mainActivityCustomer.ListComponents.material.MaterialsActivity

class CreatPostActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityCreatPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCreatPostBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        onClickBack()
//        navToMaterial()
    }


    private fun onClickBack() {
        viewBinding.buttonBack.setOnClickListener {
            // Handle the click event, for example, navigate back one step
            onBackPressed()
        }
    }

//    private fun navToMaterial() {
//        viewBinding.selectMaterial.setOnClickListener {
//            val intent = Intent(this, MaterialsActivity::class.java)
//            startActivity(intent)
//        }
//    }
}