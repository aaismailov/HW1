package ru.hse.lection03.presentationlayer

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.hse.lection03.R
import ru.hse.lection03.objects.Droid

class MainActivity :
    AppCompatActivity(), DroidListFragment.IListener {
    companion object {
        const val TAG_DETAILS = "DETAILS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener(
            View.OnClickListener {
                val currFr = supportFragmentManager.findFragmentById(R.id.data) as? DroidListFragment
                if (currFr != null) {
                    currFr.adapter.addDigit()
                    currFr.adapter.notifyDataSetChanged()
                }
                // DroidRepository.instance.addNum()
            }
        )

        // Проверяем что эта Activity не имеет сохраненного стейта и вставляем свой фрагмент
        // Если стейт есть, тогда фрагмент будет восстановлен без нашего участия
        if (savedInstanceState != null) {
            // Произошло восстановление Activity savedInstanceState
            // Убедимся, что у нас в текущей конфигурации нет лишних объектов
            checkDetails()
        }
    }

    override fun onDroidClicked(droid: Droid) {
        showDetails(droid)
    }

    private fun showDetails(droid: Droid?) {
        if (droid == null) {
            // просто проверка. Непонятно как сюда попали. По хорошему надо залогировать
            return
        }

        val detailsFragment = DroidDetailsFragment.newInstance(droid)

        // Отображаем детали Дроида
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, detailsFragment, TAG_DETAILS) // заменить фрагмент
            .commit()
    }

    private fun checkDetails() {
        val details = supportFragmentManager.findFragmentByTag(TAG_DETAILS) as? DroidDetailsFragment
        if (details != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(details) // удалим фрагмент
                .commitAllowingStateLoss()

            val droid = details.droid()
            showDetails(droid)
        }
    }
}
