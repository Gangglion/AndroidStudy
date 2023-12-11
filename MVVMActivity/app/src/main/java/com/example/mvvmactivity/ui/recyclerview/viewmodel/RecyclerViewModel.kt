package com.example.mvvmactivity.ui.recyclerview.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.mvvmactivity.R
import com.example.mvvmactivity.data.local.repository.RealmRepository
import com.example.mvvmactivity.ui.base.BaseAndroidViewModel
import com.example.mvvmactivity.ui.recyclerview.model.TempData

class RecyclerViewModel(
    private val repository: RealmRepository,
    private val application: Application
) : BaseAndroidViewModel(application) {
    private val _tempList: MutableLiveData<List<TempData>> = MutableLiveData()

    val tempList: LiveData<List<TempData>> = _tempList

    fun getItemList(){
        val tempArray = application.resources.getStringArray(R.array.temp_list)
        val list: MutableList<TempData> = mutableListOf()
        for(i in tempArray.indices) list.add(TempData(title = tempArray[i]))
        _tempList.value = list
    }

    fun initController(controller: NavController){
        setNavController(controller)
    }

    // 화면 이동 - RealmFragment로
    fun goRealmFragment(){
        mNavController.navigate(R.id.action_recyclerViewFragment_to_realmFragment)
    }
}