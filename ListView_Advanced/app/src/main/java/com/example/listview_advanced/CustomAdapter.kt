package com.example.listview_advanced

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(val activity: Activity, val list: List<ProgramLanguage>)
    : ArrayAdapter<ProgramLanguage>(activity, R.layout.list_item) {

    // Lớp ViewHolder để lưu trữ reference đến các view
    private class ViewHolder {
        lateinit var image: ImageView
        lateinit var programname: TextView
        lateinit var bird: TextView
    }

    override fun getCount(): Int = list.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: ViewHolder
        val rowView: View

        // Bước 1: Kiểm tra convertView có thể tái sử dụng không
        if (convertView == null) {
            // Nếu không, inflate view mới và tạo ViewHolder
            rowView = activity.layoutInflater.inflate(R.layout.list_item, parent, false)

            holder = ViewHolder().apply {
                image = rowView.findViewById(R.id.imageView)
                programname = rowView.findViewById(R.id.txt_LanguageName)
                bird = rowView.findViewById(R.id.txt_bird)
                rowView.tag = this // Lưu ViewHolder vào tag của view
            }
        } else {
            // Nếu có, lấy lại ViewHolder từ tag
            rowView = convertView
            holder = rowView.tag as ViewHolder
        }

        // Bước 2: Gán dữ liệu từ ViewHolder
        with(list[position]) {
            holder.image.setImageResource(image)
            holder.programname.text = programname
            holder.bird.text = bird
        }

        return rowView
    }
}