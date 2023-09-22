package com.hifit.android.mafit.ui.fragment.exercise

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.databinding.FragmentExerciseBinding
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExerciseAdapterListener
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExercisePageAdapter

class ExerciseFragment : BaseFragment<FragmentExerciseBinding>(R.layout.fragment_exercise),
    ExerciseAdapterListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sampleData = arrayListOf(
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            ),
            arrayListOf(
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                ),
                ExerciseItem(
                    "https://search.pstatic.net/common?type=f&size=210x236&quality=75&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20221230_268%2F1672365993093fNWnA_JPEG%2Faa9117f509dbe92050264c86f9777d06.jpg",
                    "https://www.gnu.ac.kr/agrieng/na/ntt/selectNttInfo.do?mi=5288&nttSn=2198876"
                )
            )
        )

        val adapter = ExercisePageAdapter(this)
        binding.exercisePager.adapter = adapter
        adapter.submitList(sampleData)

        val tabTitles = listOf("전체", "팔", "다리", "가슴", "어깨", "등", "복부", "힙", "전신")

        TabLayoutMediator(binding.exerciseTab, binding.exercisePager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onExerciseClicked(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}