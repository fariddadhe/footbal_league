package com.farid.proyekfootballeague.ui.detail.standings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farid.proyekfootballeague.R
import com.farid.proyekfootballeague.data.source.remote.response.TableEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_table.view.*

class StandingsAdapter(private val context: Context, private val table: List<TableEntity>) :
    RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_table, parent, false))

    override fun getItemCount(): Int = table.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTable(table[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindTable(table: TableEntity){
            itemView.tvTeam.text = table.name
            itemView.tvPlay.text = table.played.toString()
            itemView.tvWin.text = table.win.toString()
            itemView.tvDraw.text = table.draw.toString()
            itemView.tvLose.text = table.loss.toString()
            itemView.tvGoalsfor.text = table.goalsfor.toString()
            itemView.tvGoalsagainst.text = table.goalsagainst.toString()
            itemView.tvGoalsdifference.text = table.goalsdifference.toString()
            itemView.tvPts.text = table.total.toString()
        }

    }

}