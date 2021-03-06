/*
 * Copyright (C) 2019 by onlymash <im@fiepi.me>, All rights reserved
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package onlymash.flexbooru.ui.viewholder

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import onlymash.flexbooru.App
import onlymash.flexbooru.R
import onlymash.flexbooru.entity.tag.TagDan
import onlymash.flexbooru.entity.tag.TagDanOne
import onlymash.flexbooru.entity.tag.TagMoe

class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): TagViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tag, parent, false)
            return TagViewHolder(view)
        }
        const val GENERAL = 0
        const val ARTIST = 1
        const val COPYRIGHT = 3
        const val CHARACTER = 4
        const val CIRCLE = 5
        const val FAULTS = 6
        const val META = 5
        const val MODEL = 5
        const val PHOTO_SET = 6
    }

    private val tagName: TextView = itemView.findViewById(R.id.tag_name)
    private val tagType: TextView = itemView.findViewById(R.id.tag_type)
    private val count: TextView = itemView.findViewById(R.id.post_count)

    private var tag: Any? = null

    private var itemListener: ItemListener? = null

    fun setItemListener(listener: ItemListener) {
        itemListener = listener
    }

    interface ItemListener {
        fun onClickItem(keyword: String)
    }

    init {
        itemView.setOnClickListener {
            when (tag) {
                is TagDan -> itemListener?.onClickItem((tag as TagDan).name)
                is TagMoe -> itemListener?.onClickItem((tag as TagMoe).name)
                is TagDanOne -> itemListener?.onClickItem((tag as TagDanOne).name)
            }
        }
        itemView.setOnLongClickListener {
            val text = tagName.text
            if (!text.isNullOrBlank()) {
                App.app.clipboard.primaryClip = ClipData.newPlainText("Tag", text)
            }
            true
        }
    }

    fun bind(data: Any?) {
        tag = data
        val res = itemView.resources
        when (data) {
            is TagDan -> {
                tagName.text = data.name
                tagType.text = when (data.category) {
                    GENERAL -> res.getString(R.string.tag_type_general)
                    ARTIST -> res.getString(R.string.tag_type_artist)
                    COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                    CHARACTER -> res.getString(R.string.tag_type_character)
                    META -> res.getString(R.string.tag_type_meta)
                    else -> res.getString(R.string.tag_type_unknown)
                }
                count.text = data.post_count.toString()
            }
            is TagMoe -> {
                tagName.text = data.name
                tagType.text = when (data.type) {
                    GENERAL -> res.getString(R.string.tag_type_general)
                    ARTIST -> res.getString(R.string.tag_type_artist)
                    COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                    CHARACTER -> res.getString(R.string.tag_type_character)
                    CIRCLE -> res.getString(R.string.tag_type_circle)
                    FAULTS -> res.getString(R.string.tag_type_faults)
                    else -> res.getString(R.string.tag_type_unknown)
                }
                count.text = data.count.toString()
            }
            is TagDanOne -> {
                tagName.text = data.name
                tagType.text = when (data.type) {
                    GENERAL -> res.getString(R.string.tag_type_general)
                    ARTIST -> res.getString(R.string.tag_type_artist)
                    COPYRIGHT -> res.getString(R.string.tag_type_copyright)
                    CHARACTER -> res.getString(R.string.tag_type_character)
                    MODEL -> res.getString(R.string.tag_type_model)
                    PHOTO_SET -> res.getString(R.string.tag_type_photo_set)
                    else -> res.getString(R.string.tag_type_unknown)
                }
                count.text = data.count.toString()
            }
        }
    }
}