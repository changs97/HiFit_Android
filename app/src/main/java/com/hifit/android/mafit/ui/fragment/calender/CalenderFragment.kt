package com.hifit.android.mafit.ui.fragment.calender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.home.ExerciseData
import com.hifit.android.mafit.databinding.FragmentCalenderBinding
import com.hifit.android.mafit.viewmodel.MainViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import timber.log.Timber
import java.time.DayOfWeek
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CalenderFragment : BaseFragment<FragmentCalenderBinding>(R.layout.fragment_calender) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.tryGetExercises()

        var dateList: List<ExerciseData>? = null

        viewModel.exercises.observe(viewLifecycleOwner) {
            it?.let { dateList = it }
            binding.calendarView.notifyCalendarChanged()

        }

        binding.calendarView.monthScrollListener = { month ->
            binding.calendarTxtMonth.text = month.yearMonth.displayText()
        }

        binding.calenderImgNext.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.nextMonth)
            }
        }

        binding.calenderImgPrevious.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.previousMonth)
            }
        }


        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.binding.calendarDayTxtDay.text = data.date.dayOfMonth.toString()

                Timber.tag("테스트").i(data.date.toString() + "date")

                val stampDay =
                    dateList?.let { nullSafeDateList -> nullSafeDateList.find { it.workoutDate == data.date.toString() } }

                container.binding.calendarDayImgStamp.isVisible = stampDay != null


                when (data.date.dayOfWeek) {
                    DayOfWeek.SATURDAY -> container.binding.calendarDayTxtDay.setTextColor(
                        requireContext().getColor(R.color.blue3)
                    )

                    DayOfWeek.SUNDAY -> container.binding.calendarDayTxtDay.setTextColor(
                        requireContext().getColor(R.color.red2)
                    )

                    else -> container.binding.calendarDayTxtDay.setTextColor(
                        requireContext().getColor(
                            R.color.gray4
                        )
                    )
                }

                container.binding.calendarDayTxtDay.alpha =
                    if (data.position == DayPosition.MonthDate) 1f else 0.3f

            }
        }

        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)

        val daysOfWeek = daysOfWeek()
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)

        binding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    if (container.titlesContainer.tag == null) {
                        container.titlesContainer.tag = data.yearMonth
                        container.titlesContainer.children.map { it as TextView }
                            .forEachIndexed { index, textView ->
                                val dayOfWeek = daysOfWeek[index]
                                val title =
                                    dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                                textView.text = title

                                when (index) {
                                    0 -> textView.setTextColor(requireContext().getColor(R.color.red2))
                                    6 -> textView.setTextColor(requireContext().getColor(R.color.blue3))
                                    else -> textView.setTextColor(requireContext().getColor(R.color.gray4))
                                }
                            }
                    }
                }
            }


    }

    private fun YearMonth.displayText(short: Boolean = false): String {
        return "${this.year}년 ${this.month.displayText(short = short)}"
    }

    private fun Month.displayText(short: Boolean = true): String {
        val style = if (short) TextStyle.SHORT else TextStyle.FULL
        return getDisplayName(style, Locale.KOREAN)
    }
}