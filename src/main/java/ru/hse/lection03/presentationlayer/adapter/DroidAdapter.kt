package ru.hse.lection03.presentationlayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.hse.lection03.R
import ru.hse.lection03.businesslayer.DroidRepository
import ru.hse.lection03.objects.Droid

class DroidAdapter(private val data: ArrayList<Droid>, private val listener: DroidViewHolder.IListener) :
    RecyclerView.Adapter<DroidViewHolder>() {

    // Инициализируем ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DroidViewHolder {
        // Получаем инфлейтер и создаем нужный layout
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item_droid, parent, false)

        // Создаем ViewHolder
        return DroidViewHolder(layout, listener)
    }

    // Вставляем данные во ViewHolder
    override fun onBindViewHolder(holder: DroidViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
    }

    fun addDigit() {
        DroidRepository.instance.addNum()
        println(DroidRepository.instance.list().last().name)
        println(itemCount)
        // notifyItemInserted()
    }

    // Размер данных
    override fun getItemCount(): Int {
        return data.size
    }
}
