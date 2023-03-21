package com.belajarkotlin.librarymanagement.localRoom


class IpRepository (private val ipDao: IpDao) {

    fun readAllData(): List<IpEntity> {
        return ipDao.readAllData()
    }

    suspend fun addIp(ipEntity: IpEntity) {
        ipDao.addIp(ipEntity)
    }

    suspend fun updateIp(ipEntity: IpEntity) {
        ipDao.updateIp(ipEntity)
    }
}