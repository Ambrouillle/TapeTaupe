package me.dcal.tapelapin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val data: List<Score>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewScore: TextView = itemView.findViewById(R.id.score)
        val textViewName: TextView = itemView.findViewById(R.id.name)
        val textViewLevel: TextView = itemView.findViewById(R.id.level)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = data[position]
        holder.textViewName.text = " ${currentData.name}"+ "    "
        holder.textViewScore.text = "Score : " + "${currentData.score}"+ "    "
        holder.textViewLevel.text = "Level : "+" ${currentData.level}"
        Log.w("     VALEUR ADAPTER", currentData.toString() + ' ' + data.size.toString())

    }override fun getItemCount(): Int {
        return data.size
    }
}
