package com.test.pokedex.Activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import com.test.pokedex.Adapters.AdapterMovementsV1
import com.test.pokedex.R

import kotlinx.android.synthetic.main.activity_detail_v1.*
import kotlinx.android.synthetic.main.activity_detail_v1.toolbar

class ActivityDetailV1 : AppCompatActivity() {
    private var url:String = ""

    private lateinit var linearLayoutManager:LinearLayoutManager
    private lateinit var adapter:AdapterMovementsV1

    private lateinit var data: JsonObject
    private lateinit var dataMovements: JsonArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_v1)
        setSupportActionBar(toolbar)

        manageIntent()

        initializeComponents()
        initializeListeners()
        initializeData()

    }

    fun manageIntent(){
        if(intent != null){
            url = intent.getStringExtra("URL")
        }
    }

    fun initializeComponents(){

    }

    fun initializeListeners(){

    }

    fun initializeData(){
        Ion.with(this)
            .load(url)
            .asJsonObject()
            .done { e, result:JsonObject ->
                if(e == null){
                    Log.i("Salida", result.get("name").asString)
                    data = result
                    dataMovements = data.get("moves").asJsonArray

                    pokemon_name_v1.text = "#" + data.get("id").asInt.toString() + " " + data.get("name").asString.toUpperCase()

                    Glide
                        .with(this)
                        .load(data.get("sprites").asJsonObject.get("front_default").asString)
                        .placeholder(R.drawable.pokemon_logo_min)
                        .error(R.drawable.pokemon_logo_min)
                        .into(pokemon_image_v1);

                    if(data.get("types").asJsonArray.size() > 0){
                        if(data.get("types").asJsonArray.size() > 2){
                            Log.i("Salida", data.get("types").asJsonArray.size().toString())
                        }
                        pokemon_type_1_v1.text = data.get("types").asJsonArray.get(0).asJsonObject.get("type").asJsonObject.get("name").asString.toUpperCase()
                        if(data.get("types").asJsonArray.size() >= 2){
                            pokemon_type_2_v1.text = data.get("types").asJsonArray.get(1).asJsonObject.get("type").asJsonObject.get("name").asString.toUpperCase()
                        }
                    }

                    if(data.get("stats").asJsonArray.size() > 0){
                        if(data.get("stats").asJsonArray.size() >= 1){
                            pokemon_stat_1_v1.text = data.get("stats").asJsonArray.get(0).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 2){
                            pokemon_stat_2_v1.text = data.get("stats").asJsonArray.get(1).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 3){
                            pokemon_stat_3_v1.text = data.get("stats").asJsonArray.get(2).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 4){
                            pokemon_stat_4_v1.text = data.get("stats").asJsonArray.get(3).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 5){
                            pokemon_stat_5_v1.text = data.get("stats").asJsonArray.get(4).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 6){
                            pokemon_stat_6_v1.text = data.get("stats").asJsonArray.get(5).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                    }


                    initializeList()
                }
            }
    }

    fun initializeList(){
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.scrollToPosition(0)


        adapter = AdapterMovementsV1()
        adapter.AdapterMovements(this,dataMovements)

        recycler_view_detail_v1.layoutManager = linearLayoutManager
        recycler_view_detail_v1.adapter = adapter
        recycler_view_detail_v1.itemAnimator = DefaultItemAnimator()
    }

}
