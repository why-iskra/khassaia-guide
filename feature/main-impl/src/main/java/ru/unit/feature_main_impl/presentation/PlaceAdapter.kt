package ru.unit.feature_main_impl.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.unit.core_utils.dimen.ktx.dp
import ru.unit.feature_main_impl.R
import ru.unit.feature_main_impl.databinding.ViewholderPlaceBinding
import ru.unit.repository_places.models.PlaceModel

class PlaceAdapter(
    private val onClick: (id: Int) -> Unit
) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<PlaceModel>() {
        override fun areItemsTheSame(oldItem: PlaceModel, newItem: PlaceModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaceModel, newItem: PlaceModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ViewholderPlaceBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_place, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

        val context = holder.binding.root.context

        holder.binding.root.setOnClickListener {
            onClick.invoke(item.id)
        }

        holder.binding.nameTextView.text = item.name

        Glide.with(holder.binding.root)
            .load(item.imgUrl)
            .transform(RoundedCorners(16.dp(context)))
            .into(holder.binding.imageView)

        val stars = item.stars ?: 0
        holder.binding.starsCardView.isVisible = (item.stars != null)
        holder.binding.stars1ImageView.setImageResource(
            if (stars > 0) {
                R.drawable.ic_round_star_24
            } else {
                R.drawable.ic_round_star_outline_24
            }
        )
        holder.binding.stars2ImageView.setImageResource(
            if (stars > 1) {
                R.drawable.ic_round_star_24
            } else {
                R.drawable.ic_round_star_outline_24
            }
        )
        holder.binding.stars3ImageView.setImageResource(
            if (stars > 2) {
                R.drawable.ic_round_star_24
            } else {
                R.drawable.ic_round_star_outline_24
            }
        )
        holder.binding.stars4ImageView.setImageResource(
            if (stars > 3) {
                R.drawable.ic_round_star_24
            } else {
                R.drawable.ic_round_star_outline_24
            }
        )
        holder.binding.stars5ImageView.setImageResource(
            if (stars > 4) {
                R.drawable.ic_round_star_24
            } else {
                R.drawable.ic_round_star_outline_24
            }
        )

        val price =
            holder.binding.root.context.getString(R.string.price_pattern, item.price?.toString())
        holder.binding.priceCardView.isVisible = (item.price != null)
        holder.binding.priceTextView.text = price

        holder.binding.ratingCardView.isVisible = (item.rating != null)
        holder.binding.ratingTextView.text = item.rating?.toString()

        val infoVisible =
            holder.binding.starsCardView.isVisible ||
                    holder.binding.ratingCardView.isVisible ||
                    holder.binding.priceCardView.isVisible

        val infoInnerVisible =
            holder.binding.ratingCardView.isVisible ||
                    holder.binding.priceCardView.isVisible

        holder.binding.infoLayout.isVisible = infoVisible
        holder.binding.infoInnerLayout.isVisible = infoInnerVisible
    }

    override fun getItemCount(): Int = differ.currentList.size
}