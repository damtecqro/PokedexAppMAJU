package com.test.pokedex.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.test.pokedex.R

class AdapterMovementsV2: RecyclerView.Adapter<AdapterMovementsV2.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var data: JsonArray

    fun AdapterMovementsV2(context: Context, data: JsonArray){
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMovementsV2.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_grid_movements,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size()
    }

    override fun onBindViewHolder(holder: AdapterMovementsV2.ViewHolder, position: Int) {
        var item: JsonObject = data.get(position).asJsonObject

        holder.bind(item)

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private var nameMovement: TextView = view.findViewById(R.id.movement_name_v2)

        fun bind(item: JsonObject){
            nameMovement.setText(item.get("move").asJsonObject.get("name").asString.toUpperCase())
        }

    }
}