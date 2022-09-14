package com.rafif.crudbasic.repository

import com.rafif.crudbasic.db.Subreker
import com.rafif.crudbasic.db.SubrekerDAO

class SubrekerRepository(private val dao: SubrekerDAO) {

    val subreker = dao.getAllSubreker()

    suspend fun insert(subreker: Subreker){
        dao.insertSubreker(subreker)
    }
    suspend fun update(subreker: Subreker) {
        dao.updateSubreker(subreker)
    }
    suspend fun delete(subreker: Subreker) {
        dao.deleteSubreker(subreker)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}