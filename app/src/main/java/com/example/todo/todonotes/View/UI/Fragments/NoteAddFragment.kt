package com.example.todo.todonotes.View.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R
import com.example.todo.todonotes.Model.db.entity.Item
import com.example.todo.todonotes.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.fragment_note_add.*
import kotlinx.android.synthetic.main.headerfragment.*


class  NoteAddFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // Head.setText("Add Note")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Header.setText("Add Note")
        saveNote.setOnClickListener{
            submitData()
        }

        viewModel = ViewModelProvider(this).get(
            ItemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_add, container, false)

    }


    fun submitData() {
        val itemText = addNotetitle.text.toString()
        val itemContent = addNotecontent.text.toString()
        val builder = AlertDialog.Builder(activity)
        if (itemText.isEmpty()) {

            builder.setTitle("Title Cannot be Empty")
            builder.setPositiveButton("OK"){dialogInterface, which -> }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

        }
        else{
            viewModel.insertItem((Item(itemText, itemContent)))
            builder.setTitle("Note Created Successfully")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                makeText(getActivity(),"Item Added", Toast.LENGTH_LONG).show()
                activity?.supportFragmentManager?.popBackStack()
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

    }

}
