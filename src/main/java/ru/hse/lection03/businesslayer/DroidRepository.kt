package ru.hse.lection03.businesslayer

import ru.hse.lection03.objects.Droid

class DroidRepository private constructor() {
    companion object {

        var DATA_SIZE = 100

        // простенький singleton
        val instance by lazy { DroidRepository() }

    }




    // ленивая инициализации для списка Дроидов
    protected var droidList = initializeData()

    // получить список Дроидов
    fun list() = droidList

    // получить дроида по индексу
    fun item(index: Int) = droidList[index]

    public fun addNum(){
        DATA_SIZE += 1
        droidList = initializeData()
        println("addNUM AAAAAAAAAAA")
        println(DATA_SIZE)
        println(droidList.last().name)

    }


    // Функция инициализации списка дроидов
    protected fun initializeData(): List<Droid> {
        val data = mutableListOf<Droid>()

        // Наполняем лист в цикле
        for (position in 1 until DATA_SIZE+1) {

            // Генерим имя дроида
            val name = "$position"

            // Получаем случайное число, и определяем состояние дроида
            val state = when (position%2 == 0) {
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