package com.example.ap3.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ap3.R
import de.hdodenhof.circleimageview.CircleImageView

class ProfileAdapter(private val context: Context, private val profiles: List<Profile>) : BaseAdapter() {

    override fun getCount(): Int {
        return profiles.size
    }

    override fun getItem(position: Int): Profile {
        return profiles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false)
            viewHolder = ViewHolder(
                view.findViewById(R.id.profile_image),
                view.findViewById(R.id.profile_name),
                view.findViewById(R.id.profile_nrp)
            )
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val profile = getItem(position)
        viewHolder.profileImage.setImageResource(profile.profileImageId)
        viewHolder.profileName.text = profile.nama
        viewHolder.profileNrp.text = profile.nrp

        return view
    }

    private class ViewHolder(
        val profileImage: CircleImageView,
        val profileName: TextView,
        val profileNrp: TextView
    )
}
