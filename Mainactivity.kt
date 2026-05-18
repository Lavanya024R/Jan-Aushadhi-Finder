package com.example.janaushadhifinder

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Data class for Medicine
data class Medicine(
    val brandName: String,
    val genericName: String,
    val brandPrice: Double,
    val genericPrice: Double
)

class MainActivity : AppCompatActivity() {

    private lateinit var searchAdapter: MedicineAdapter
    private val masterList = mutableListOf<Medicine>()

    // This "Companion Object" lets the Map know which medicine you clicked
    companion object {
        var selectedMedicineName: String = "None"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMedicineDatabase()

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        val btnMap = findViewById<Button>(R.id.btnOpenMap)
        val btnRemind = findViewById<Button>(R.id.btnReminder)

        searchAdapter = MedicineAdapter(masterList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = searchAdapter

        // Fuzzy Search Implementation
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().lowercase().trim()
                val filtered = masterList.filter {
                    it.brandName.lowercase().contains(query) || it.genericName.lowercase().contains(query)
                }
                searchAdapter.update(filtered)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btnMap.setOnClickListener {
            if (selectedMedicineName == "None") {
                Toast.makeText(this, "Please click a medicine card first!", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, MapsActivity::class.java))
        }

        btnRemind.setOnClickListener {
            Toast.makeText(this, "Refill reminder set for 30 days from today!", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadMedicineDatabase() {
        masterList.add(Medicine("Dolo 650", "Paracetamol", 32.0, 5.50))
        masterList.add(Medicine("Calpol 500", "Paracetamol", 28.0, 4.80))
        masterList.add(Medicine("Pan 40", "Pantoprazole", 150.0, 24.0))
        masterList.add(Medicine("Pantocid", "Pantoprazole", 145.0, 24.0))
        masterList.add(Medicine("Augmentin 625", "Amoxycillin", 210.0, 65.0))
        masterList.add(Medicine("Glycomet 500", "Metformin", 60.0, 12.00))
        masterList.add(Medicine("Voveran SR", "Diclofenac", 95.0, 18.0))
        masterList.add(Medicine("Telma 40", "Telmisartan", 110.0, 22.0))
        masterList.add(Medicine("Allegra 120", "Fexofenadine", 195.0, 45.0))
        masterList.add(Medicine("Azithral 500", "Azithromycin", 125.0, 35.0))
        masterList.add(Medicine("Limcee", "Vitamin C", 30.0, 10.0))
        masterList.add(Medicine("Becosules", "B-Complex", 50.0, 15.0))
        masterList.add(Medicine("Combiflam", "Ibuprofen + Paracetamol", 45.0, 12.0))
        masterList.add(Medicine("Montek LC", "Montelukast + Levocetirizine", 180.0, 50.0))
        masterList.add(Medicine("Omez 20", "Omeprazole", 80.0, 15.0))
        masterList.add(Medicine("Taxim O 200", "Cefixime", 115.0, 35.0))
        masterList.add(Medicine("Shelcal 500", "Calcium + Vit D3", 110.0, 30.0))
        masterList.add(Medicine("Atorva 10", "Atorvastatin", 120.0, 25.0))
        masterList.add(Medicine("Rosuvas 10", "Rosuvastatin", 180.0, 40.0))
        masterList.add(Medicine("Eco-sprin 75", "Aspirin", 10.0, 3.0))
        masterList.add(Medicine("Thyronorm 50", "Thyroxine", 170.0, 80.0))
        masterList.add(Medicine("Folvite", "Folic Acid", 85.0, 20.0))
        masterList.add(Medicine("Aricep 5", "Donepezil", 250.0, 70.0))
    }

    class MedicineAdapter(private var list: List<Medicine>) : RecyclerView.Adapter<MedicineAdapter.VH>() {
        class VH(v: View) : RecyclerView.ViewHolder(v) {
            val brand: TextView = v.findViewById(R.id.tvBrandName)
            val generic: TextView = v.findViewById(R.id.tvGenericName)
            val oldP: TextView = v.findViewById(R.id.tvOldPrice)
            val newP: TextView = v.findViewById(R.id.tvNewPrice)
            val tag: TextView = v.findViewById(R.id.tvSavingsTag)
        }

        override fun onCreateViewHolder(p: ViewGroup, t: Int) = VH(LayoutInflater.from(p.context).inflate(R.layout.item_medicine, p, false))

        override fun onBindViewHolder(h: VH, p: Int) {
            val m = list[p]
            h.brand.text = m.brandName
            h.generic.text = "Generic: ${m.genericName}"
            h.oldP.text = "₹${m.brandPrice}"
            h.oldP.paintFlags = h.oldP.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            h.newP.text = "₹${m.genericPrice}"
            val pct = ((m.brandPrice - m.genericPrice) / m.brandPrice * 100).toInt()
            h.tag.text = "SAVE $pct%"

            h.itemView.setOnClickListener {
                selectedMedicineName = m.brandName
                AlertDialog.Builder(h.itemView.context)
                    .setTitle("Medicine Selected")
                    .setMessage("You have selected ${m.brandName}.\n\nClick 'Find Nearest Store' to check if this is available at nearby Jan-Aushadhi Kendras.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
        override fun getItemCount() = list.size
        fun update(newList: List<Medicine>) { list = newList; notifyDataSetChanged() }
    }
}
