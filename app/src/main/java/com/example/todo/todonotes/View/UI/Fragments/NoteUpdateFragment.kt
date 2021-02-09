package com.example.todo.todonotes.View.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todo.R
import com.example.todo.todonotes.View_Model.ItemViewModel
import kotlinx.android.synthetic.main.fragment_note_update.*
import kotlinx.android.synthetic.main.headerfragment.*


class NoteUpdateFragment : Fragment() {

    lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Header.setText("Update Note")

        viewModel = ViewModelProvider(this).get(
            ItemViewModel::class.java)


        updateTitle.setText(arguments?.getString("title", ""))
        updateContent.setText(arguments?.getString("content", ""))


        btnUpdate.setOnClickListener{
            updatesubmit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_update, container, false)
    }


    fun updatesubmit() {
        val uTitle = arguments?.getString("title", "")
        val uContent=arguments?.getString("content", "")
        val id = arguments?.getInt("id", -1)

        val builder = AlertDialog.Builder(context)
        var updateTitle = updateTitle.text.toString()
        var updateContent = updateContent.text.toString()


        if (updateTitle.isNullOrEmpty() || id == -1) {
            builder.setTitle("Update Note Title Cannot be Empty")
            builder.setPositiveButton("OK"){dialogInterface, which ->
                Toast.makeText(getActivity(),"clicked yes", Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

        }
        else{
            if( updateTitle == uTitle)
                Toast.makeText(activity,"Data not Changed", Toast.LENGTH_LONG).show()
            else {
                viewModel.updateItem(id!!, updateTitle, updateContent)
                builder.setTitle("Note Updated Successfully")
                builder.setPositiveButton("OK") { dialogInterface, which ->
                    Toast.makeText(getActivity(), "Item Updated", Toast.LENGTH_LONG).show()
                    activity?.supportFragmentManager?.popBackStack()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }


    }

}