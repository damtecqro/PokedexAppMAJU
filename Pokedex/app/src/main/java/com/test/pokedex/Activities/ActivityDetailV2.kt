package com.test.pokedex.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import com.test.pokedex.Adapters.AdapterMovementsV1
import com.test.pokedex.Adapters.AdapterMovementsV2
import com.test.pokedex.R
import kotlinx.android.synthetic.main.activity_detail_v1.*

import kotlinx.android.synthetic.main.activity_detail_v2.*

class ActivityDetailV2 : AppCompatActivity() {

    private var url:String = ""

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: AdapterMovementsV2

    private lateinit var data: JsonObject
    private lateinit var dataMovements: JsonArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_v2)
        setSupportActionBar(toolbar_detail_v2)

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
        btn_info.setOnClickListener {
            toggleLayout("INFO")
        }

        btn_movements.setOnClickListener {
            toggleLayout("MOVEMENTS")
        }
    }

    fun toggleLayout(type:String){
        if(type.equals("INFO")){
            btn_info.setTextColor(ContextCompat.getColor(this,R.color.whiteTrans))
            btn_info.setBackgroundResource(R.drawable.gradiente_fondo)

            btn_movements.setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryText))
            btn_movements.setBackgroundColor(ContextCompat.getColor(this,R.color.whiteTrans))

            layout_info.visibility = View.VISIBLE
            card_view_movements.visibility = View.GONE

        }else if(type.equals("MOVEMENTS")){
            btn_info.setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryText))
            btn_info.setBackgroundColor(ContextCompat.getColor(this,R.color.whiteTrans))

            btn_movements.setTextColor(ContextCompat.getColor(this,R.color.whiteTrans))
            btn_movements.setBackgroundResource(R.drawable.gradiente_fondo)

            layout_info.visibility = View.GONE
            card_view_movements.visibility = View.VISIBLE
        }
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

                    //pokemon_name_v2.text = "#" + data.get("id").asInt.toString() + " " + data.get("name").asString.toUpperCase()
                    toolbar_layout.title = "#" + data.get("id").asInt.toString() + " " + data.get("name").asString.toUpperCase()
                    Glide
                        .with(this)
                        .load(data.get("sprites").asJsonObject.get("front_default").asString)
                        .placeholder(R.drawable.pokemon_logo_min)
                        .error(R.drawable.pokemon_logo_min)
                        .into(pokemon_image_v2);

                    if(data.get("types").asJsonArray.size() > 0){
                        if(data.get("types").asJsonArray.size() > 2){
                            Log.i("Salida", data.get("types").asJsonArray.size().toString())
                        }
                        pokemon_type_1_v2.text = data.get("types").asJsonArray.get(0).asJsonObject.get("type").asJsonObject.get("name").asString.toUpperCase()
                        if(data.get("types").asJsonArray.size() >= 2){
                            pokemon_type_2_v2.text = data.get("types").asJsonArray.get(1).asJsonObject.get("type").asJsonObject.get("name").asString.toUpperCase()
                        }
                    }

                    if(data.get("stats").asJsonArray.size() > 0){
                        if(data.get("stats").asJsonArray.size() >= 1){
                            pokemon_stat_1_v2.text = data.get("stats").asJsonArray.get(0).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 2){
                            pokemon_stat_2_v2.text = data.get("stats").asJsonArray.get(1).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 3){
                            pokemon_stat_3_v2.text = data.get("stats").asJsonArray.get(2).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 4){
                            pokemon_stat_4_v2.text = data.get("stats").asJsonArray.get(3).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 5){
                            pokemon_stat_5_v2.text = data.get("stats").asJsonArray.get(4).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                        if(data.get("stats").asJsonArray.size() >= 6){
                            pokemon_stat_6_v2.text = data.get("stats").asJsonArray.get(5).asJsonObject.get("stat").asJsonObject.get("name").asString.toUpperCase() + ": "+ data.get("stats").asJsonArray.get(0).asJsonObject.get("base_stat").asInt
                        }
                    }


                    initializeList()
                }
            }
    }

    fun initializeList(){
        gridLayoutManager = GridLayoutManager(this,3)
        gridLayoutManager.orientation = GridLayoutManager.VERTICAL
        gridLayoutManager.scrollToPosition(0)


        adapter = AdapterMovementsV2()
        adapter.AdapterMovementsV2(this,dataMovements)

        recycler_view_detail_v2.layoutManager = gridLayoutManager
        recycler_view_detail_v2.adapter = adapter
        recycler_view_detail_v2.itemAnimator = DefaultItemAnimator()
    }

}
