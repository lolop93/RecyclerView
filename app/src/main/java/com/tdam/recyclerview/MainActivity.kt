package com.tdam.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.tdam.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = mutableListOf(
            Pokemon("Pikachu", 100, "Electrico", 1),
            Pokemon("Charmander", 100, "Fuego", 1),
            Pokemon("Squirtle", 100, "Agua", 1),
            Pokemon("Bulbasaur", 100, "Planta", 1),
            Pokemon("Pidgey", 100, "Volador", 1),
            Pokemon("Rattata", 100, "Normal", 1),
            Pokemon("Geodude", 100, "Roca", 1),
            Pokemon("Gastly", 100, "Fantasma", 1),
            Pokemon("Machop", 100, "Lucha", 1),
            Pokemon("Abra", 100, "Psiquico", 1),
            Pokemon("Eevee", 100, "Normal", 1),
            Pokemon("Magikarp", 100, "Agua", 1),
            Pokemon("Snorlax", 100, "Normal", 1),
            Pokemon("Mew", 100, "Psiquico", 1),
            Pokemon("Mewtwo", 100, "Psiquico", 1),
            Pokemon("Zapdos", 100, "Electrico", 1),
            Pokemon("Articuno", 100, "Hielo", 1),
            Pokemon("Moltres", 100, "Fuego", 1),
            Pokemon("Dragonite", 100, "Dragon", 1),
            Pokemon("Gyarados", 100, "Agua", 1),
            Pokemon("Lapras", 100, "Agua", 1),
            Pokemon("Vaporeon", 100, "Agua", 1),
            Pokemon("Jolteon", 100, "Electrico", 1),
            Pokemon("Flareon", 100, "Fuego", 1),
            Pokemon("Machamp", 100, "Lucha", 1),
            Pokemon("Alakazam", 100, "Psiquico", 1),
            Pokemon("Gengar", 100, "Fantasma", 1),
            Pokemon("Golem", 100, "Roca", 1),
            Pokemon("Rhydon", 100, "Tierra", 1),
            Pokemon("Kangaskhan", 100, "Normal", 1),
        )

        //al adaptador hay que pasarle la lista de datos y el listener
        pokemonAdapter = PokemonAdapter(data, this) //this es el listener

        binding.recyclerView.apply { //aqui le decimos al recyclerview que use el adaptador que hemos creado
            layoutManager = LinearLayoutManager(this@MainActivity) //esto es para que el recyclerview se muestre como una lista
            adapter = pokemonAdapter
         }

        binding.btnAdd.setOnClickListener {
            if(binding.etPokemon.text.toString().isNotEmpty()){
                val pokemon = Pokemon(binding.etPokemon.text.toString(), 100, "Electrico", 1)

                addPokemonAutomatically(pokemon)

                binding.etPokemon.text?.clear()


            }
        }

    }

    private fun addPokemonAutomatically(pokemon: Pokemon) {
        pokemonAdapter.addPokemon(pokemon)
    }

    //sobrecargamos el metodo onLongClick de la interfaz OnClickListener
    override fun onLongClick(pokemon: Pokemon) {
        //aqui se ejecuta el codigo cuando se hace click largo en un elemento de la lista
        removePokemonAutomatically(pokemon)

    }

    private fun removePokemonAutomatically(pokemon: Pokemon) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Pokemon")
        builder.setMessage("¿Estas seguro de que quieres eliminar a ${pokemon.nombre}?")
        builder.setPositiveButton("Si") { _, _ ->
            pokemonAdapter.removePokemon(pokemon)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.show()


    }
}