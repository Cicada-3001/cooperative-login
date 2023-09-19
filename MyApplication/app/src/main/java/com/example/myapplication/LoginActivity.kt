package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.models.Auth
import com.example.myapplication.repository.Repository

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private val LOGIN_REQUEST_CODE = 1080;
    }

    private lateinit var loginBtn: Button
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var  progressBar: ProgressBar
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_login)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        init()
    }


    private fun init (){
        loginBtn = findViewById(R.id.loginBtn)
        emailEdt = findViewById(R.id.editTextTextEmailAddress)
        passwordEdt = findViewById(R.id.editTextTextPassword)
        progressBar=findViewById(R.id.progress_bar)
        loginBtn.setOnClickListener(this)
    }


    private fun loginUser() {
        // Take the value of two edit texts in Strings
        var username: String = ""
        var password: String = ""
        var response: String= ""

        username = emailEdt.getText().toString()
        password = passwordEdt.getText().toString()

        // validations for input email and password
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(
                applicationContext,
                "Please enter email!!",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(
                applicationContext,
                "Please enter password!!",
                Toast.LENGTH_LONG
            )
                .show()
            return
        }

        val user= Auth(username,password)
        viewModel.loginUser(user)
        viewModel.userResponse.observe(this, Observer{
            if(it.isSuccessful){
                 response = it.body() as String
                Toast.makeText(this, response,Toast.LENGTH_SHORT).show()
                 startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }else{
                Toast.makeText(this,it.errorBody()?.string(),Toast.LENGTH_SHORT).show()
                Log.d("vehicle ADD ERROR", it.errorBody()?.string()!!)
            }
        })

    }



    override fun onClick(v: View?) {
        var i:Intent
        if (v != null) {
            when (v.id) {
                R.id.loginBtn-> {
                    loginUser()
                }
                else -> {
                    print("Error")
                }
            }
        }
    }
}