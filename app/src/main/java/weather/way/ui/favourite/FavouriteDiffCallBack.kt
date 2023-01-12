package weather.way.ui.favourite

import androidx.recyclerview.widget.DiffUtil
import weather.way.domain.model.CommonInfo

object FavouriteDiffCallBack: DiffUtil.ItemCallback<CommonInfo>() {
    override fun areItemsTheSame(oldItem: CommonInfo, newItem: CommonInfo): Boolean {
        return oldItem.city.name == newItem.city.name
    }

    override fun areContentsTheSame(oldItem: CommonInfo, newItem: CommonInfo): Boolean {
        return  oldItem == newItem
    }
}