package ru.hse.lection03.businesslayer

import ru.hse.lection03.objects.Droid
import kotlin.collections.ArrayList

open class DroidRepository private constructor() {
    companion object {
        var DATA_SIZE = 100
        val instance = DroidRepository()
    }

    private var droidList = initializeData()

    // получить список дроидов
    fun list() = droidList

    // получить дроида по индексу
    fun item(index: Int) = droidList[index]

    fun addNum() {
        DATA_SIZE += 1
        droidList.add(
            Droid(
                "$DATA_SIZE",
                when (DATA_SIZE % 2 == 0) {
                    true -> Droid.STATE_REMOVED
                    false -> Droid.STATE_NEW
                }
            )
        )
        println(droidList.size)
        println(droidList.last().name)
    }

    // Функция инициализации списка дроидов
    private fun initializeData(): ArrayList<Droid> {
        val data = ArrayList<Droid>()
        // Наполняем лист в цикле
        for (position in 1 until DATA_SIZE + 1) {
            // Генерим имя дроида
            val name = "$position"

            // Получаем случайное число, и определяем состояние дроида
            val state = when (position % 2 == 0) {
                true -> Droid.STATE_REMOVED
                false -> Droid.STATE_NEW
            }

            // Создаем дроида и добавляем его в список
            val droid = Droid(name, state)
            data.add(droid)
        }

        return data
    }
}
