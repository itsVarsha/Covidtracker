package com.example.covidtracker

import CustomAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.covidtracker.models.DataModel
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity() {

   lateinit var recyclerView :RecyclerView
    var dataAPI  = ArrayList<DataModel>()

    private  val jsonURL = "https://api.rootnet.in/covid19-in/stats/latest"

    override fun onCreate(savedInstanceState: Bundle?) {
        var dataAPI  = ArrayList<DataModel>()

        Log.e("MSG", "1")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.list_item)

        extractData();
        Log.e("MSG","After extract")



    }

    private fun extractData() {
        var queue:RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, jsonURL, null,
                { response ->
                    try {
                        val dataObj = response.getJSONObject("data")
                        val summaryObj = dataObj.getJSONObject("summary")
                        val regionalArr = dataObj.getJSONArray("regional")
                        for (i in 0..regionalArr.length()-1 )
                        {
                            val regionalObj = regionalArr.getJSONObject(i)
                            val state: String = regionalObj.getString("loc")
                            val active: Int = regionalObj.getInt("totalConfirmed")
                            val recovered: Int = regionalObj.getInt("discharged")
                            val death: Int = regionalObj.getInt("deaths")

                            val data = DataModel(state,active,recovered,death)


                             var covidData :DataModel = DataModel("",0 ,0,0,)

                            covidData.setStateName(state)
                            covidData.setDeaths(death)
                            covidData.setActivecases(active)
                            covidData.setRecovered(recovered)

                            dataAPI.add(data)


                        }
                        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        val customAdapter: CustomAdapter = CustomAdapter(applicationContext,dataAPI)
                        recyclerView.adapter = customAdapter

                    }
                    catch (e: JSONException){
                        e.printStackTrace()
                    }
                },
                { error ->
                    Toast.makeText(this,"Fail to get Data",Toast.LENGTH_LONG).show()
                }
        )

        queue.add(jsonObjectRequest)
    }

}