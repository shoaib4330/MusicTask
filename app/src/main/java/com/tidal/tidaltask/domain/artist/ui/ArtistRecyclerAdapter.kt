package com.tidal.tidaltask.domain.artist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tidal.tidaltask.R
import com.tidal.tidaltask.domain.artist.model.Artist
import kotlinx.android.synthetic.main.rv_artist_row.view.*

class ArtistRecyclerAdapter : RecyclerView.Adapter<ArtistRecyclerAdapter.ArtistVH> {

    private val context: Context?
    private val clickListener: OnClickListener
    private val artists: MutableList<Artist>

    constructor (context: Context?, clickListener: OnClickListener) {
        this.clickListener = clickListener
        this.context = context
        this.artists = ArrayList()
    }

    fun setData(artists: List<Artist>, removeExistingRecords: Boolean = false) {
        if (removeExistingRecords)
            this.artists.clear()
        this.artists.addAll(artists)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.artists.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistVH {
        return ArtistVH(
            LayoutInflater.from(this.context)
                .inflate(R.layout.rv_artist_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return this.artists.size
    }

    override fun onBindViewHolder(holder: ArtistVH, position: Int) {
        holder.artistName.text = this.artists[position].name

        context?.let {
            Glide.with(context)
                .load(this.artists[position].picture_medium)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.artistImage)
        }

        holder.itemRoot.setOnClickListener {
            clickListener.onClick(this.artists[position])
        }
    }

    /* ----------------------------- ViewHolder class follows ---------------------- */
    class ArtistVH(view: View) : RecyclerView.ViewHolder(view) {
        val itemRoot : View = view.itemRoot
        val artistImage: ImageView = view.ivArtistImage
        val artistName: AppCompatTextView = view.tvArtistName
    }

    interface OnClickListener {
        fun onClick(artist: Artist)
    }
}