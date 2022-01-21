package designstring.androidninja.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import designstring.androidninja.R
import designstring.androidninja.databinding.RecyclerViewItemBinding
import designstring.androidninja.domain.models.DomainEntity

class RecyclerViewAdapter(private val items: List<DomainEntity>): RecyclerView.Adapter<RecyclerViewAdapter.AdapterItem>() {

    inner class AdapterItem(item: View): RecyclerView.ViewHolder(item){
        private val binding = RecyclerViewItemBinding.bind(item)
        val rootView = binding.root
        val avatar = binding.itemAvatar
        val firstName = binding.itemFirstName
        val email = binding.itemEmail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItem {
        val view = View.inflate(parent.context, R.layout.recycler_view_item, null)
        view.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return AdapterItem(view)
    }

    override fun onBindViewHolder(holder: AdapterItem, position: Int) {
        val item = items[position]

        // load image with glide
        Glide.with(holder.rootView)
            .load(item.avatarUrl)
            .fallback(R.drawable.ic_avatar_default)
            .circleCrop()
            .into(holder.avatar)

        // set the first name
        holder.firstName.text = item.firstName

        // set the email
        holder.email.text = item.email
    }

    override fun getItemCount() = items.size
}