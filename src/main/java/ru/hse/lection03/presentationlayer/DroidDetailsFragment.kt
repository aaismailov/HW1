package ru.hse.lection03.presentationlayer

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ru.hse.lection03.R
import ru.hse.lection03.objects.Droid

class DroidDetailsFragment : DialogFragment() {
    companion object {
        const val EXTRAS_DROID = "DROID"

        // helper-метод для создания инстанса фрагмента
        // Это один из подходов в упрощении
        fun newInstance(droid: Droid): DroidDetailsFragment {
            // Создаем данные, которые потом положим в фрагмент
            val extras = Bundle().apply {
                putSerializable(EXTRAS_DROID, droid)
            }

            val fragment = DroidDetailsFragment().apply {
                arguments = extras
            }

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val droid = droid()
        if (droid != null) {
            // Устанавливаем имя

            val stateColor = when (droid.state) {
                Droid.STATE_REMOVED -> "#ff0000"
                Droid.STATE_NEW -> "#3700B3"
                else -> "#000000"
            }

            val stateTitle = droid.name
            view.findViewById<TextView>(R.id.state).apply {
                // Устанавливаем название состояния
                text = stateTitle

                // Красим подложку в цвет, ассоциированный с состоянием
                setTextColor(Color.parseColor(stateColor))
            }
            view.findViewById<Button>(R.id.back).setOnClickListener(View.OnClickListener { dismiss() })

            // view.findViewById<TextView>(R.id.state).setTextColor(stateColor)
        }
    }

    // Метод для доставания объекта Droid из аргументов фрагмента
    fun droid(): Droid? {
        return arguments?.getSerializable(EXTRAS_DROID) as? Droid
    }
}
