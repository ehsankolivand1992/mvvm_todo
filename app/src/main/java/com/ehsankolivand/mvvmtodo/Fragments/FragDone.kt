package com.ehsankolivand.mvvmtodo.Fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehsankolivand.mvvmtodo.Adapters.TodoAdapter

import com.ehsankolivand.mvvmtodo.R
import com.ehsankolivand.mvvmtodo.Utility.AdapterEventistinner
import com.ehsankolivand.mvvmtodo.Utility.DialogListinner
import com.ehsankolivand.mvvmtodo.Utility.EditTaskDialog
import com.ehsankolivand.mvvmtodo.ViewModel.MainTaskViewModel
import com.ehsankolivand.mvvmtodo.database.TaskModel
import kotlinx.android.synthetic.main.fragment_frag_done.*
import kotlinx.android.synthetic.main.fragment_frag_to_do.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragDone.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragDone.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragDone : Fragment(), AdapterEventistinner, DialogListinner {
    private lateinit var mainTaskViewModel: MainTaskViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainTaskViewModel = ViewModelProvider(this).get(MainTaskViewModel::class.java)

        InitRcv()

    }

    private fun InitRcv() {
        val adapter = TodoAdapter(FragToDo(),this,2)
        rcv_tasks_done.adapter = adapter
        rcv_tasks_done.layoutManager = LinearLayoutManager(context)

        mainTaskViewModel.workedDoneTask.observe(this, Observer { task ->
            task?.let {
                adapter.setTask(it)
            }
        })


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_done, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragDone.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragDone().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun delete(task: TaskModel) {
        mainTaskViewModel.Delete(task)
    }

    override fun edit(task: TaskModel) {

        val mydialog = EditTaskDialog(task,activity as Activity,FragToDo(),this,2)
        mydialog.ShowTaskDialog()

    }


    override fun done(task: TaskModel) {
        task.done = false
        mainTaskViewModel.Update(task)
    }

    override fun addTaskToDatabase(task: TaskModel) {
        mainTaskViewModel.Update(task)
    }
}
