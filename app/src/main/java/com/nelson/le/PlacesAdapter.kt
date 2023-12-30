package com.nelson.le

//Nelson Le - 301064831
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlacesAdapter(private val places: List<Landmark>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_type, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)

        holder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            listener.onItemClick(position)
            notifyDataSetChanged()
        }

        holder.itemView.isActivated = selectedPosition == position
    }

    override fun getItemCount(): Int = places.size

    fun getSelectedPlace(): Landmark? {
        return if (selectedPosition != RecyclerView.NO_POSITION) {
            places[selectedPosition]
        } else {
            null
        }
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val placeName: TextView = itemView.findViewById(R.id.placeNameTextView)
        private val placeAddress: TextView = itemView.findViewById(R.id.placeAddressTextView)
        private val placeImage: ImageView = itemView.findViewById(R.id.placeImageView)

        fun bind(place: Landmark) {
            placeName.text = place.name
            placeAddress.text = place.address

            // Use Glide to load the image from the URL
            Glide.with(itemView)
                .load(place.imageUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.loading))
                .into(placeImage)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
