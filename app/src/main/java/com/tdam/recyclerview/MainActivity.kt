package com.tdam.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.tdam.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var capturadosAdapter : PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //al adaptador hay que pasarle la lista de datos y el listener
        pokemonAdapter = PokemonAdapter(mutableListOf(), this) //this es el listener
        capturadosAdapter = PokemonAdapter(mutableListOf(), this)

        binding.recyclerView.apply { //aqui le decimos al recyclerview que use el adaptador que hemos creado
            layoutManager = LinearLayoutManager(this@MainActivity) //esto es para que el recyclerview se muestre como una lista
            adapter = pokemonAdapter
         }

        //añadimo es nuevo adapter
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = capturadosAdapter
        }

        binding.btnAdd.setOnClickListener {
            if(binding.etPokemon.text.toString().isNotEmpty()){
                val pokemon = Pokemon(binding.etPokemon.text.toString(), 100, "Electrico", 1)

                addPokemonAutomatically(pokemon)

                binding.etPokemon.text?.clear()


            }
        }

    }

    private fun getData(){
        val data = mutableListOf(
            Pokemon("Pikachu", 100, "Electrico", 1, true),
            Pokemon("Charmander", 100, "Fuego", 1),
            Pokemon("Squirtle", 100, "Agua", 1, true),
            Pokemon("Bulbasaur", 100, "Planta", 1),
            Pokemon("Pidgey", 100, "Volador", 1),
            Pokemon("Rattata", 100, "Normal", 1),

            )

        data.forEach {pokemon ->
           addPokemonAutomatically(pokemon)
        }

    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun addPokemonAutomatically(pokemon: Pokemon) {

        //si por ejemplo el nombre del pokemon es pikachu o tiene la 'a', lo añadimos al segundo adaptador
        if (pokemon.capturado == true) {
            capturadosAdapter.addPokemon(pokemon)
        }else{
            pokemonAdapter.addPokemon(pokemon)
        }

    }

    override fun onPokemonCaptured(pokemon: Pokemon) {
        pokemonAdapter.removePokemon(pokemon)
        capturadosAdapter.addPokemon(pokemon)
    }
//    override fun onPokemonReleased(pokemon: Pokemon) {
//        capturadosAdapter.removePokemon(pokemon)
//        pokemonAdapter.addPokemon(pokemon)
//    }

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