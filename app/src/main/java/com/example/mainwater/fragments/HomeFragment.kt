package com.example.mainwater.fragments

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mainwater.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var _binding: FragmentHomeBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var linelist: ArrayList<Entry>
    lateinit var lineDataset: LineDataSet
    lateinit var lineData: LineData

    var entryMap = mutableMapOf<LocalDate,Float>(LocalDate.of(2022,6,1) to 35.2F,
        LocalDate.of(2022,5,31) to 42.6F,)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.d("ITM","TEST HomeFragment")

        /*entryMap[LocalDate.of(2022,5,30)]=45.0F
        entryMap[LocalDate.of(2022,5,29)]=22.4F
        entryMap[LocalDate.of(2022,5,28)]=3.0F
        entryMap[LocalDate.of(2022,5,27)]=1.2F*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        linelist = ArrayList()
        Log.d("ITM","TEST DATE")
        for (entry in entryMap) {
            linelist.add(Entry(dateToFloat(entry.key),entry.value))
        }
        lineDataset = LineDataSet(linelist, "consommation d'eau (L)")
        Log.d("ITM","test1")
        lineData = LineData(lineDataset)
        Log.d("ITM","test1.1")
        binding.lineChart.data=lineData
        Log.d("ITM","test 2")


        lineDataset.setColors(Color.DKGRAY)
        lineDataset.valueTextColor = Color.BLUE
        lineDataset.valueTextSize =12f
        Log.d("ITM","test 3")

        val xAxis = binding.lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        //val dateFormatter = SimpleDateFormat("yy/MM/dd")
        var lastDate = ""
        val formatter = object :  ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val date = floatToDateString(value)
                if (date == lastDate){
                    return ""
                }
                lastDate = date
                return date
            }
        }
        xAxis.valueFormatter = formatter
        Log.d("ITM","test4")
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun dateToString(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }
    private fun dateStringToFloat(dateString: String): Float{
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
        return date.time.toFloat()

    }
    private fun dateToFloat(date: LocalDate): Float {
        return dateStringToFloat(dateToString(date))
    }
    private fun floatToDateString(miliSec: Float): String{
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(miliSec)
    }
}