package com.nac.module_mall

import com.nac.export_mall.IMallService
import javax.inject.Inject

class MallService @Inject constructor() : IMallService {
    override fun getMallData() = "Mall Data"
}