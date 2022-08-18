package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pesquisaCEP.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            val call = RetrofitFactory().retrofitService().getCEP(cep.text.toString())
            call.enqueue(object : Callback<CEP> {
                override fun onResponse(call: Call<CEP>, response: Response<CEP>) {
                    response.body()?.let {
                        Log.i("CEP", it.toString())
                        Toast.makeText(
                            this@MainActivity, it.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        progress_bar.visibility = View.INVISIBLE
                    } ?: Toast.makeText(
                        this@MainActivity, "CEP não Localizado",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<CEP>?, t: Throwable?) {
                    Log.e("Erro", t?.message.toString())
                    progress_bar.visibility = View.INVISIBLE
                }
            })
        }

        pesquisaRCE.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            val call = RetrofitFactory().retrofitService().getRCE(
                uf.text.toString(),
                cidade.text.toString(),
                rua.text.toString()
            )
            call.enqueue(object : Callback<List<CEP>> {
                override fun onResponse(call: Call<List<CEP>>?, response: Response<List<CEP>>?) {
                    response?.body()?.let {
                        Log.i("CEP", it.toString())
                        Toast.makeText(
                            this@MainActivity, it.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        progress_bar.visibility = View.INVISIBLE
                    } ?: Toast.makeText(
                        this@MainActivity, "CEP não Localizado",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<List<CEP>>?, t: Throwable?) {
                    Log.e("Erro", t?.message.toString())
                    progress_bar.visibility = View.INVISIBLE
                }
            })
        }
    }
}