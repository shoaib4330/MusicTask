package com.tidal.tidaltask.domain.album.detail.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tidal.tidaltask.R
import com.tidal.tidaltask.domain.album.model.Track
import kotlinx.android.synthetic.main.rv_track_row.view.*

class TracksRecyclerAdapter(
    private val context: Context?,
    private val clickListener: OnClickListener
) : RecyclerView.Adapter<TracksRecyclerAdapter.TrackVH>() {

    private val tracks: MutableList<Track>

    init {
        tracks = ArrayList()
    }

    fun setData(tracks: List<Track>, removeExistingRecords: Boolean = false) {
        if (removeExistingRecords)
            this.tracks.clear()
        this.tracks.addAll(tracks)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.tracks.clear()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        return TrackVH(
            LayoutInflater.from(context)
                .inflate(R.layout.rv_track_row, parent, false)
        )
    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        val track: Track = this.tracks[position]

        holder.trackPosition.text = track.track_position.toString()
        holder.trackTitle.text = track.title
        track.artist?.let { holder.trackArtist.text = track.artist.name }

        holder.itemRoot.setOnClickListener {
            clickListener.onClick(track)
        }
    }

    /* ---------------------------- Classes ------------------------- */
    class TrackVH(view: View) : RecyclerView.ViewHolder(view) {
        val itemRoot: View = view.itemRoot
        val trackPosition: TextView = view.tvTrackPos
        val trackTitle: TextView = view.tvTrackTitle
        val trackArtist: TextView = view.tvArtistName
    }


    interface OnClickListener {
        fun onClick(track: Track)
    }
}