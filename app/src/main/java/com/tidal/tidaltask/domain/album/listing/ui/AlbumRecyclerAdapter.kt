package com.tidal.tidaltask.domain.album.listing.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tidal.tidaltask.R
import com.tidal.tidaltask.domain.album.model.Album
import kotlinx.android.synthetic.main.rv_album_row.view.*

class AlbumRecyclerAdapter(
    private val context: Context?,
    private val clickListener: OnClickListener,
    private val artistName: String?
) : RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumVH>() {

    private val albums: MutableList<Album>

    init {
        this.albums = ArrayList()
    }

    fun setData(albums: List<Album>, removeExistingRecords: Boolean = false) {
        if (removeExistingRecords)
            this.albums.clear()
        this.albums.addAll(albums)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.albums.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        return AlbumVH(
            LayoutInflater.from(context)
                .inflate(R.layout.rv_album_row, parent, false)
        )
    }

    override fun getItemCount(): Int = this.albums.size

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        val album = this.albums[position]

        holder.albumTitle.text = album.title

        this.artistName?.let { holder.artistName.text = it }

        context?.let {
            Glide.with(context)
                .load(album.cover_big)
                .placeholder(R.drawable.intermediate)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.albumCover)
        }

        holder.itemRoot.setOnClickListener {
            clickListener.onClick(album)
        }
    }


    /* ----------------------------- ViewHolder class follows ---------------------- */
    class AlbumVH(view: View) : RecyclerView.ViewHolder(view) {
        val itemRoot: View = view.itemRoot
        val albumCover: ImageView = view.ivAlbumCover
        val albumTitle: TextView = view.tvAlbumName
        val artistName: TextView = view.tvArtistName
    }

    interface OnClickListener {
        fun onClick(album: Album)
    }
}