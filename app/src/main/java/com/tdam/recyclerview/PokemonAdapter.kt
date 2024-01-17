package com.tdam.recyclerview

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tdam.recyclerview.databinding.ItemPokemonBinding

class PokemonAdapter (var listapokemon: MutableList<Pokemon>, private val listener: OnClickListener) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){


    //un viewholder es una clase que se encarga de mantener los elementos de la lista en memoria
    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemPokemonBinding.bind(view) //esto es para poder acceder a los elementos del layout

        fun setListener(pokemon: Pokemon) {
            binding.root.setOnLongClickListener {
                listener.onLongClick(pokemon)
                true

            }
        }

    }

    //este metodo se ejecuta cuando se crea el viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //aqui inflamos el layout que hemos creado para cada elemento de la lista
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)

        //aqui creamos el viewholder y le pasamos la vista que acabamos de inflar para luego devolverlo
       return ViewHolder(view)

    }

    //este metodo nos indica cuantos elementos tiene la lista
    override fun getItemCount(): Int = listapokemon.size



    //este metodo se ejecuta cuando se quiere mostrar un elemento de la lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //le daremos valores a los elementos de cada item de la lista
        //en este caso solo tenemos un textview que será el nombre del pokemon
        val pokemon = listapokemon.get(position)

        holder.setListener(pokemon)

        holder.binding.textView.text = pokemon.nombre
        holder.binding.checkBox.isChecked = pokemon.capturado


        holder.binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                pokemon.capturado = true
                listener.onPokemonCaptured(pokemon)
            } else {
//                listener.onPokemonReleased(pokemon)
            }
        }
    }

    fun addPokemon(pokemon: Pokemon) {
        listapokemon.add(pokemon)
        notifyDataSetChanged()
    }

    fun removePokemon(pokemon: Pokemon) {
        listapokemon.remove(pokemon)
        notifyDataSetChanged() //este metodo es para que se actualice la lista
    }
}
