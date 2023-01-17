package weather.way.ui.favourite

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import weather.way.R
import weather.way.domain.model.CommonInfo

class FavouriteAdapter(
    val listener: OnCityClickListener
) : ListAdapter<CommonInfo, FavouriteViewHolder>(FavouriteDiffCallBack) {

    var myData: List<CommonInfo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_small_card, parent, false)
        return FavouriteViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val weather = myData[position]
        holder.bind(weather)
        holder.itemView.setOnClickListener {
            listener.onCityClick(weather)
        }
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    interface OnCityClickListener {
        fun onCityClick(commonInfo: CommonInfo)
    }
}