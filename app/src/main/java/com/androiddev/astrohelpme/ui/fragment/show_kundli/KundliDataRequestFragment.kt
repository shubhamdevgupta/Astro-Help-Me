package com.androiddev.astrohelpme.ui.fragment.show_kundli

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.data.response.KundliResponse
import com.androiddev.astrohelpme.databinding.FragmentKundliDataBinding
import com.androiddev.astrohelpme.ui.activity.DashboardActivity
import com.androiddev.astrohelpme.utils.extns.makeToast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class KundliDataRequestFragment : Fragment() {

    private var _binding: FragmentKundliDataBinding? = null
    private val binding get() = _binding!!
    private var kundliResponse: KundliResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKundliDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kundliResponse =
            arguments?.getSerializable("kundliresponse") as? KundliResponse

        Log.d("MYTAG", "onViewCreated: kundliResponse-----" + kundliResponse)

        binding.tvAscendantValue.text = kundliResponse?.ascendant
        binding.tvAscendantLordValue.text = kundliResponse?.ascendant_lord
        binding.tvVarnaValue.text = kundliResponse?.Varna
        binding.tvVashyaValue.text = kundliResponse?.Vashya
        binding.tvYoniValue.text = kundliResponse?.Yoni
        binding.tvGanValue.text = kundliResponse?.Gan
        binding.tvNadiValue.text = kundliResponse?.Nadi
        binding.tvSignLordValue.text = kundliResponse?.SignLord
        binding.tvsignValue.text = kundliResponse?.sign
        binding.tvNaksahtraValue.text = kundliResponse?.Naksahtra
        binding.tvNaksahtraLordValue.text = kundliResponse?.NaksahtraLord
        binding.tvCharanValue.text = kundliResponse?.Charan.toString()
        binding.tvYogValue.text = kundliResponse?.Yog
        binding.tvKaranValue.text = kundliResponse?.Karan
        binding.tvTithiValue.text = kundliResponse?.Tithi
        binding.tvnameAlphabetValue.text = kundliResponse?.name_alphabet
        binding.tvpayaValue.text = kundliResponse?.paya
        binding.tatvaValue.text= kundliResponse?.tatva
        binding.icSqaa.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        binding.icDownload.setOnClickListener {
            requestStoragePermission()
            val file = generateKundliPDF(requireContext())
            if (file != null) {
                requireContext().makeToast("Kundli PDF saved at: ${file.absolutePath}")
            } else {
                requireContext().makeToast("Failed to Generate pdf")
            }
        }
    }


    private fun generateKundliPDF(context: Context): File? {
        try {
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            val paint = Paint()
            canvas.drawColor(Color.WHITE)
            paint.color = Color.BLACK
            paint.textSize = 18f
            paint.textSize = 24f
            val title = "Kundli Details"
            val titleWidth = paint.measureText(title)
            canvas.drawText(title, (595f - titleWidth) / 2, 50f, paint)

            // Data to be displayed
            val data = mapOf(
                "Ascendant" to kundliResponse?.ascendant,
                "Ascendant Lord" to  kundliResponse?.ascendant_lord,
                "Varna" to kundliResponse?.Varna,
                "Vashya" to kundliResponse?.Vashya,
                "Yoni" to kundliResponse?.Yoni,
                "Gan" to kundliResponse?.Gan,
                "Nadi" to kundliResponse?.Nadi ,
                "Sign Lord" to kundliResponse?.SignLord,
                "Sign" to kundliResponse?.sign,
                "Nakshatra" to kundliResponse?.Naksahtra,
                "Nakshatra Lord" to  kundliResponse?.NaksahtraLord ,
                "Charan" to kundliResponse?.Charan.toString(),
                "Yog" to kundliResponse?.Yog,
                "Karan" to kundliResponse?.Karan,
                "Tithi" to kundliResponse?.Tithi,
                "Yunja" to  kundliResponse?.name_alphabet,
                "Tatva" to kundliResponse?.tatva,
                "Paya" to kundliResponse?.paya
            )

            // Starting position for the labels and values
            var yPosition = 100f // Position starts below the title
            val leftMargin = 40f  // Margin for the label column
            val rightMargin = 410f // Margin for the value column

            // Loop through the data map and draw labels and corresponding values
            for ((label, value) in data) {
                // Draw label on the left (centered in the left section)
                paint.textSize = 18f
                val labelWidth = paint.measureText("$label:")
                canvas.drawText("$label:", leftMargin, yPosition, paint)

                // Draw value on the right (centered in the right section)
                paint.textSize = 18f
                val valueWidth = paint.measureText(value)
                if (value != null) {
                    canvas.drawText(value, rightMargin, yPosition, paint)
                }

                // Increase the yPosition for the next line
                yPosition += 40f // Spacing between lines
            }

            // Finish the page
            pdfDocument.finishPage(page)


            // Save the file in Downloads directory
            val downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            val timestamp = dateFormat.format(Date())
            val filePath = File(downloadsPath, "Kundli_$timestamp.pdf")

            // Save the document
            pdfDocument.writeTo(FileOutputStream(filePath))
            pdfDocument.close()

            // Inform media scanner to show the file in recent files
            MediaScannerConnection.scanFile(
                context,
                arrayOf(filePath.absolutePath),
                null,
                null
            )

            return filePath

        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        } else {
            // Permission already granted
            generateKundliPDF(requireContext())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, generate PDF
            generateKundliPDF(requireContext())
        } else {
            requireContext().makeToast("Permission denied")
        }
    }

}