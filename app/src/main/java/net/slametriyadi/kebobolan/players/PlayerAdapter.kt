package net.slametriyadi.kebobolan.players

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.content_players.view.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.PlayerItem
import net.slametriyadi.kebobolan.players.detail.DetailPlayerActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailPlayerAdapter(var dataPlayers: List<PlayerItem>, var context: Context?) :
    RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        val v: View = LayoutInflater.from(p0.context).inflate(R.layout.content_players, p0, false)
        return PlayerViewHolder(v)
    }

    override fun getItemCount(): Int = dataPlayers.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bind(dataPlayers[p1], context)
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgPlayers: ImageView = view.imgPlayer
    private val namePlayer: TextView = view.namePlayer
    private val positionPlayer: TextView = view.positionPlayer

    fun bind(playerItem: PlayerItem, context: Context?) {
        namePlayer.text = playerItem.strPlayer
        positionPlayer.text = playerItem.strPosition
        if (context != null) {
            val options: RequestOptions =
                RequestOptions().placeholder(R.drawable.avatar).error(R.drawable.avatar).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                ).priority(Priority.HIGH)
            Glide.with(context).load(playerItem.strCutout).apply(options).into(imgPlayers)
        }

        val onClick = itemView.setOnClickListener {
            context?.startActivity<DetailPlayerActivity>(
                "playerName" to playerItem.strPlayer
            )
        }
    }

}
