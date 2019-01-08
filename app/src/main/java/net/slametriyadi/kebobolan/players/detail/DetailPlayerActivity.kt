package net.slametriyadi.kebobolan.players.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.content_example.*
import net.slametriyadi.kebobolan.R
import net.slametriyadi.kebobolan.model.PlayerItem
import net.slametriyadi.kebobolan.repository.players.PlayerDetailRepository
import org.jetbrains.anko.toast

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {

    private lateinit var detailPresenter: DetailPlayerPresenter
    private lateinit var namePlayer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_example)

    }

    override fun onStart() {
        super.onStart()

        namePlayer = intent.getStringExtra("playerName")

        detailPresenter = DetailPlayerPresenter(this, PlayerDetailRepository())
        onAttachView()
    }

    override fun toastError(msg: String) {
        toast(msg)
    }

    override fun showDataPlayers(dataPlayers: List<PlayerItem>) {
        val data = dataPlayers[0]
        weight_detail_player.text = data.strWeight
        height_detail_player.text = data.strHeight
        position_detail_player.text = data.strPosition
        desc_detail_player.text = data.strDescriptionEN

        val options: RequestOptions =
            RequestOptions().placeholder(R.drawable.avatar).error(R.drawable.avatar).diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).priority(Priority.HIGH)

        Glide.with(this).load(data.strThumb).apply(options).into(img_detail_player)
        title = data.strPlayer
    }

    override fun onAttachView() {
        detailPresenter.onAttach(this)
        detailPresenter.getDataPlayer(namePlayer)
    }

    override fun onDettachView() {
        detailPresenter.onDettach()
    }
}