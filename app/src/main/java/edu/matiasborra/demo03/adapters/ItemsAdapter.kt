package edu.matiasborra.demo03.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import edu.matiasborra.demo03.MainViewModel
import edu.matiasborra.demo03.databinding.ItemsBinding
import edu.matiasborra.edumatiasborrademo02.model.Items

class ItemsAdapter() : ListAdapter<Items, ItemsAdapter.ViewHolder>(ItemsDiffCallback()) {
    //ViewHolder para la lista de elementos
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemsBinding.bind(view)
        private val viewModel = MainViewModel()
        private val adapter = ItemsAdapter()

        fun bind(item: Items) {
            binding.tvId.text = String.format(item.id.toString())
            binding.tvTitle.text = item.title

            //Compruebo si el item está archivado y oculto el icono de archivado
            if (item.archived) {
                binding.iconArchive.visibility = View.GONE
            } else {
                binding.iconArchive.visibility = View.VISIBLE
            }

            Glide.with(binding.root)
                .load(item.image)
                .transform(FitCenter(), RoundedCorners(16))
                .into(binding.ivItem)

            //Añado listener para cuando pulse el item en la lista para mostrar un mensaje
            binding.root.setOnClickListener {
                MaterialAlertDialogBuilder(binding.root.context)
                    .setTitle(item.title)
                    .setMessage(item.description)
                    .setPositiveButton("Ok", null)
                    .show()
            }

            //Añado listener para cuando pulse el item en la lista para archivarlo
            binding.iconArchive.setOnClickListener {
                viewModel.archiveItem(item, adapter)
            }
        }
    }

    //inflado de la vista y creación del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    //vinculación de datos con la vista y ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

//Comprobación de objetos
class ItemsDiffCallback: DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }

}

