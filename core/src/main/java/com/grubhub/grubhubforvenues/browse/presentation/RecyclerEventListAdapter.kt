package com.grubhub.grubhubforvenues.browse.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.grubhub.grubhubforvenues.R
import com.grubhub.grubhubforvenues.di.module.GlideApp
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RecyclerEventListAdapter(var mContext: Context) :
    RecyclerView.Adapter<RecyclerEventListAdapter.ViewHolder>() {

    private var mEvents: List<EventModel> = ArrayList()

    private val onClickSubject : PublishSubject<EventModel> = PublishSubject.create()

    class ViewHolder(mRow: View) : RecyclerView.ViewHolder(mRow) {
        var mName: TextView = mRow.findViewById(R.id.name) as TextView
        var mDate: TextView = mRow.findViewById(R.id.date) as TextView
        var mThumbnail: ImageView = mRow.findViewById(R.id.thumbnail) as ImageView
    }

    fun setEvents(events: List<EventModel>?) {
        mEvents = ArrayList<EventModel>(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerEventListAdapter.ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_event, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val e = mEvents[position]

        holder.mName.text = e.name
        holder.mDate.text = e.date

        GlideApp.with(mContext)
            .load(e.imageUrl)
            .error(R.drawable.default_image)
            .into(holder.mThumbnail)

        holder.itemView.setOnClickListener { onClickSubject.onNext(e) }
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    fun getPositionClicks(): Observable<EventModel> {
        return onClickSubject
    }
}